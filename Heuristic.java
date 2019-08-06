import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Heuristic {

	private static List<Cluster> processing;
	private static List<Cluster> assigned;
	private static List<Cluster> unassigned;
	private static List<Cluster> assignable;
	public static List<Groups> gruplar;
	
	public static int gunSayisi ;
	public static int ekipSayisi ;
	
	
	public static double birimTaranan = 0.000009 ;		// 9 / 1000.000 iha tiplerine göre değişecek 9 dk / 1km2
	public static double setup = 5.0; 					// Targeme göre değişebilir dakika
	public static double yerEkipHizi = 50000.0; 		// yer ekibinin saatteki hızıdır m/saat 50 km/saat
	private static double guneslenmeSuresi = 240.0; 	// güneşlenme süresi 4 saat alınmıştır.
	
	public static void ekipAta(){
		// metot tamamlanmadı, kontrol, kullanılmadı
		
		ekipSayisi = 0;
		int gunlukEkip = gruplar.size()/gunSayisi;
		System.out.println("gunlukEkip :"+gunlukEkip);
		List<Groups> liste1 = new ArrayList<Groups>();
		List<Groups> liste2 = new ArrayList<Groups>();
		liste1.addAll(gruplar);
		while(!liste1.isEmpty()){
			
		liste2.clear();
		double merkez = 0.0;
		double maxMerkez = 0.0;
		int mm = -1;
		for(int i=0;i<liste1.size();i++){
			merkez = liste1.get(i).getMerkez().getX() + liste1.get(i).getMerkez().getY() ;
			if(maxMerkez < merkez){
			maxMerkez = merkez;
			mm = i;
			}
		}
		if(liste1.size()>=gunSayisi){	
			liste2.add(liste1.get(mm));
			liste1.remove(mm);
			ekipSayisi++;
			liste2.get(0).ekipNo = ekipSayisi;
			liste2.get(0).gunNo = 1;
			for(int k=1; k<gunSayisi;k++){	
				double mesafe2 =0.0;
				double min2 = 999999.99;
				int enyakin = -1;
				for(int j=0; j<liste1.size(); j++){
					mesafe2 = Point.distance(liste2.get(0).getMerkez(), liste1.get(j).getMerkez());
					if(mesafe2 < min2){
						min2 = mesafe2;	
						enyakin = j;
					}
				}
				liste1.get(enyakin).ekipNo = ekipSayisi;
				liste1.get(enyakin).gunNo = k+1;
				liste2.add(liste1.get(enyakin));
				liste1.remove(enyakin);
			}
		}else if(liste1.size()<gunSayisi && liste1.size() > 0){
			ekipSayisi++;
			liste2.addAll(liste1);
			liste1.removeAll(liste2);
			for(int i=0; i<liste2.size();i++){
				liste2.get(i).ekipNo = ekipSayisi;
				liste2.get(i).gunNo = i+1;
			}
		}
	}
	}

	public static void grouping() throws IOException {
		
		processing = new ArrayList<Cluster>();
		assigned = new ArrayList<Cluster>();
		unassigned = new ArrayList<Cluster>();
		assignable = new ArrayList<Cluster>();
		gruplar = new ArrayList<Groups>();
		List<Cluster> liste = new ArrayList<Cluster>();
		
		assignable.addAll(KMeans.clusters);
		int grupNo = 1;
		while(!assignable.isEmpty()){
			liste.clear();
			liste.add(assignable.get(0));
			processing.addAll(assignable);
			processing.remove(assignable.get(0)); // Sorgulanmalı ? get(0) ya da get(random)
			double mesafe = 0.0;
			double min = 0.0;
			double max = Double.MAX_VALUE;			
			int atama =0;
			while(!processing.isEmpty()){	
				min = max;
				int enyakin = -1;
				for(int j=0; j<processing.size(); j++){
					mesafe = Point.distance(assignable.get(0).getCentroid(), processing.get(j).getCentroid());
					if(mesafe < min  && mesafe !=0){
						min = mesafe;	
						enyakin = j;
					}
				}
				if((Groups.calculateIslemSuresi(liste) + Groups.calculateIslemSuresi(processing.get(enyakin)) + ((Point.distance(liste.get(liste.size()-1).getCentroid(), processing.get(enyakin).getCentroid())*60)/yerEkipHizi) ) <= guneslenmeSuresi){ // min * 60 / yerekiphizi 
					liste.add(processing.get(enyakin));
					atama++;
				}
				processing.remove(processing.get(enyakin));
			}
			if(atama>0){
				Groups grup = new Groups(grupNo);
				grup.addKume(liste);
				gruplar.add(grup);
				assigned.addAll(liste);
				assignable.removeAll(liste);
				grupNo++;
			}else{
				unassigned.addAll(liste);
				assignable.removeAll(liste);
			}
		}
		if(unassigned.size()>0){
			List<Cluster> liste2 = new ArrayList<Cluster>();
			for(int i =0; i<liste2.size();i++){
			liste2.clear();
			Groups grup = new Groups(grupNo);
			liste2.add(unassigned.get(i));
			grup.addKume(liste2);
			assigned.addAll(liste2);
			grupNo++;
			}
		}
		System.out.println();
		System.out.println(KMeans.clusters.size()+" kümeden " + assigned.size()+" 'ı gruplandırıldı.");
		System.out.println("Toplam grup sayısı: " + gruplar.size());
		ekipAta();
		Heuristic.grupYazdir();
}
	
	
	public static void grupYazdir() throws IOException { 

		XSSFWorkbook wb = new XSSFWorkbook();
		ekipGrup(wb);
		grupKumeNokta(wb);

		FileOutputStream fileOut = new FileOutputStream("C:/Users/Asus/workspace/KMeans/Sonuclar.xlsx");
		wb.write(fileOut);
		fileOut.close();
		wb.close();
		System.out.println("...");
		System.out.println("Writing Group info is done.");
	}
	public static void ekipGrup(XSSFWorkbook wb){
		
		XSSFSheet sheet = wb.createSheet("Ekipler");
		XSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("Ekip No");
		rowhead.createCell(1).setCellValue("Gün");
		rowhead.createCell(2).setCellValue("Grup ID");
		rowhead.createCell(3).setCellValue("Grup İşlem Süresi");
		rowhead.createCell(4).setCellValue("Grup Merkez X");
		rowhead.createCell(5).setCellValue("Grup Merkez Y");
		
		int row=1;
		for(int i = 0; i<gruplar.size(); i++) {	
					sheet.createRow(row).createCell(0).setCellValue(String.valueOf(gruplar.get(i).ekipNo));
					sheet.getRow(row).createCell(1).setCellValue(String.valueOf(gruplar.get(i).gunNo));
					sheet.getRow(row).createCell(2).setCellValue(String.valueOf(gruplar.get(i).getGrupNo()));
					sheet.getRow(row).createCell(3).setCellValue(String.valueOf(gruplar.get(i).getIslemSuresi()));
					sheet.getRow(row).createCell(4).setCellValue(String.valueOf(gruplar.get(i).getMerkez().getX()));
					sheet.getRow(row).createCell(5).setCellValue(String.valueOf(gruplar.get(i).getMerkez().getY()));
					row++;
		}
		
	}
	
	
	public static void grupKumeNokta(XSSFWorkbook wb){
		
		XSSFSheet sheet = wb.createSheet("Gruplar");

		XSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("Grup ID");
		rowhead.createCell(1).setCellValue("Cluster ID");
		rowhead.createCell(2).setCellValue("Cluster X");
		rowhead.createCell(3).setCellValue("Cluster Y");
		rowhead.createCell(4).setCellValue("Cluster Alan");
		rowhead.createCell(5).setCellValue("Cluster İşlem Süresi");
		rowhead.createCell(6).setCellValue("Point ID");
		rowhead.createCell(7).setCellValue("Point X");
		rowhead.createCell(8).setCellValue("Point Y");
		rowhead.createCell(9).setCellValue("Point Alan");
		
		rowhead.createCell(11).setCellValue("Grup Alan");
		rowhead.createCell(12).setCellValue("Grup İşlem Süresi");
		rowhead.createCell(13).setCellValue("Grup Ulaşım Süresi");
		rowhead.createCell(14).setCellValue("Grup Setup Süresi");
		rowhead.createCell(15).setCellValue("Toplam Süre");
		rowhead.createCell(16).setCellValue("Analiz / Toplam ");
		
		int row=1;
		for(int i = 0; i<gruplar.size(); i++) {	
			for (int j =0; j<gruplar.get(i).getClusterSayisi(); j++){		
				for (int k =0; k<gruplar.get(i).getKumeler().get(j).points.size(); k++){
					sheet.createRow(row).createCell(0).setCellValue(String.valueOf(gruplar.get(i).getGrupNo()));
					sheet.getRow(row).createCell(1).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getId()));
					sheet.getRow(row).createCell(2).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getCentroid().getX()));
					sheet.getRow(row).createCell(3).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getCentroid().getY()));
					sheet.getRow(row).createCell(4).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getArea()));
					sheet.getRow(row).createCell(5).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getArea()*Heuristic.birimTaranan));
					sheet.getRow(row).createCell(6).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getPoints().get(k).getId()));
					sheet.getRow(row).createCell(7).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getPoints().get(k).getX()));
					sheet.getRow(row).createCell(8).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getPoints().get(k).getY()));
					sheet.getRow(row).createCell(9).setCellValue(String.valueOf(gruplar.get(i).getKumeler().get(j).getPoints().get(k).getArea()));
					
					sheet.getRow(row).createCell(11).setCellValue(String.valueOf(gruplar.get(i).getAlan()));
					sheet.getRow(row).createCell(12).setCellValue(String.valueOf(gruplar.get(i).getAlan()* Heuristic.birimTaranan));
					sheet.getRow(row).createCell(13).setCellValue(String.valueOf((Groups.mesafeHesaplama(gruplar.get(i).getKumeler()))*60 / yerEkipHizi ));
					sheet.getRow(row).createCell(14).setCellValue(String.valueOf(gruplar.get(i).getClusterSayisi()*setup));
					sheet.getRow(row).createCell(15).setCellValue(String.valueOf(gruplar.get(i).getIslemSuresi()));
					sheet.getRow(row).createCell(16).setCellValue(String.valueOf(((gruplar.get(i).getAlan()*Heuristic.birimTaranan)/gruplar.get(i).getIslemSuresi())*100));
					row++;
				}
			}
		}
	}
	 
}
