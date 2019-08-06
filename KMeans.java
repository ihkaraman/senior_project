import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KMeans {

	//parameters
	public static int numCluster;   
	public static int paramCNumber;
	public static int maxDistance;
	public static int maxArea;
	public static int usedCluster;

	public int clusterCount;
	public int totalPoints;
	private static int prevRestSize;

	public static List<Point> points = new ArrayList<Point>();
	private static List<Point> atanacak = new ArrayList<Point>(); // atanmayan noktalar
	private static List<Point> atanan = new ArrayList<Point>(); // atanan noktalar
	private static List<Point> atanamayan = new ArrayList<Point>();   // atanamayan noktalar
	public static List<Cluster> clusters = new ArrayList<Cluster>();

	public KMeans() {
		/*
		// degiskenler
		numCluster = 1000; 
		maxDistance = 2000;
		maxArea = 12000000;
		clusterCount = 0;
		paramCNumber = 1000;
		*/
		KMeans.points = new ArrayList<Point>();
		KMeans.atanacak = new ArrayList<Point>();
		KMeans.atanan = new ArrayList<Point>();
		KMeans.atanamayan = new ArrayList<Point>();
		KMeans.clusters = new ArrayList<Cluster>();

	}
	
	public static void main(String[] args) {
		
		calistir();
		
	}
	public static void parametreYazdir(){
		System.out.println("cluster sayısı: "+paramCNumber);
		System.out.println("max distance: "+maxDistance);
		System.out.println("max area: "+maxArea);
		System.out.println("gun sayisi: "+Heuristic.gunSayisi);
	}
	public static void calistir(){
		
		//System.out.println("Enter Parameters Please: ");
		//kmeans.parameters();
		System.out.println("");
		System.out.println("Initializing... Please Wait...");
		parametreYazdir();
		numCluster = paramCNumber ;
		initialization();
		System.out.println("Initializing is done.");
		System.out.println();
		System.out.println("Calculating... Please Wait...");
		algorithm();
		System.out.println("Calculating is done.");

		try {
			System.out.println();
			System.out.println("Writing Outputs to 'output.xlsx' file ...");
			finalization();
			//printResults();
			// kmeans.distanceMatrix();
			System.out.println();
			System.out.println("Belirlenen cluster sayısı: " + KMeans.paramCNumber);
			System.out.println("Oluşturulan cluster sayısı: " + (KMeans.numCluster - KMeans.paramCNumber));
			System.out.println("Toplam cluster sayısı : " + KMeans.numCluster);
			System.out.println("Atanan toplam nokta sayısı : " + KMeans.points.size() +" noktada "+KMeans.atanan.size() + "'ı atandı.");
			Heuristic.grouping();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parameters() { //Parametrelerin kullanıcından alındığı metot

		Scanner scan = new Scanner(System.in);

		//System.out.println("Enter the file name (Ex: fields)");
		//Point.filename = scan.nextLine()+".xlsx";
		System.out.println("Enter the max number of cluster:");
		paramCNumber = scan.nextInt();
		numCluster = paramCNumber ; // 
		System.out.println("Enter the max distance between clusters and points (metre):");
		maxDistance = scan.nextInt();
		System.out.println("Enter the max area of clusters (square metre) :");
		maxArea = scan.nextInt();
		scan.close();

	}

	public static void initialization() { 
		// excelden verileri okur. 
		// Cluster sınıfından numCluster kadar yeni nesne oluşturup merkezlerine random seçilen noktaları atar.

		//Read Points from excel
		points.addAll(Point.createReadedPoints());
		System.out.println("Okunan nokta sayısı: "+points.size());
		for (int i = 1; i <= numCluster; i++) {
			Cluster cluster = new Cluster(i);
			Point centroid = Point.choosingRandomPoint(points); // Forgy method
			//Point centroid = Point.createRandomPoint(Point.minimumBul(points), Point.maximumBul(points)); // Random initial metodu
			cluster.setCentroid(centroid);
			clusters.add(cluster);
		}
		System.out.println("Oluşturulan cluster sayısı: "+clusters.size());
	}
	
	public static void finalization(){
		
		System.out.println("Atanan nokta sayısı: " + atanan.size());
		System.out.println("Atanamayan nokta sayısı: " + atanamayan.size());
		
		for(Point p: atanamayan){
		numCluster++;
		Cluster cluster = new Cluster(numCluster);
		clusters.add(cluster);
		cluster.setCentroid(p);
		cluster.addPoint(p);
		atanan.add(p);
		}
		atanamayan.clear();
	}
	
	public static void assignCluster(){
		
		atanan.clear();
		atanamayan.clear();
		atanacak.addAll(points);

		  int cluster = 0; 
		  double max = Double.MAX_VALUE; 
		  double min = max;    
		  double distance = 0.0;
		  for(Point point : points) {
			min = max;
			for(int i = 0; i < clusters.size(); i++) {
				if((clusters.get(i).getArea() + point.getArea()) <= maxArea){
					distance = Point.distance(point, clusters.get(i).getCentroid());
					if(distance < min) {
						min = distance;
						cluster = i;   
					}
				}
			}	
		    if ((min <= maxDistance)){
		    	 clusters.get(cluster).addPoint(point);
		    	 atanan.add(point);
		    	 atanacak.remove(point);
		     }else{
		    	 atanamayan.add(point);
		    	 atanacak.remove(point);
		     }    
		  }
	}


	public static void calculateCentroids() {

		for(Cluster cluster : clusters) {
			double sumX = 0;
			double sumY = 0;

			List<Point> list = cluster.getPoints();
			int n_points = list.size();
			for(Point point : list) {
				sumX += point.getX();
				sumY += point.getY();
			}
			Point centroid = cluster.getCentroid();
			if(n_points != 0) {
				double newX = sumX / n_points;
				double newY = sumY / n_points;
				centroid.setX(newX);
				centroid.setY(newY);
				cluster.setCentroid(centroid);
			}
		}
	}

	public static List<Point> getCentroids() {
		
		List<Point> centroids = new ArrayList<Point>(numCluster);
		for(Cluster cluster : clusters) {
			Point aux = cluster.getCentroid();
			Point point = new Point(aux.getX(),aux.getY(), aux.getArea(), aux.getId());
			centroids.add(point);
		}
		return centroids;
	}
	
	// düzeltilmeli
	public static void optimizeCluster(){ 
		
		int numInRest = atanamayan.size();
		double clusterOrani = ((double)points.size()) / ((double)paramCNumber);
		int yeniClusterSayisi = numInRest / (int)clusterOrani;
		
		for (int i = 0; i < yeniClusterSayisi; i++) {
			numCluster++;
			Cluster cluster = new Cluster(numCluster);
			Point centroid = Point.choosingRandomPoint(atanamayan); // Forgy method
			cluster.setCentroid(centroid);
			clusters.add(cluster);
		}
       
		/*
		int numElementsInRest = atanamayan.size();
        Set<Point> alreadyProcessed = new HashSet<>();
        
        while(!atanamayan.isEmpty()){
        	liste.clear();
        	int rastgele = (int)Math.random()*atanamayan.size();
        	liste.add(atanamayan.get(rastgele));
        	
        }
        
        for(int i = 0; i<numElementsInRest;i++){
			for(int j= 0; j<numElementsInRest;j++){
                Point pi = atanamayan.get(i);
                Point pj = atanamayan.get(j);
                if(i == j || alreadyProcessed.contains(pi) || alreadyProcessed.contains(pj)) // zaten işlem görmüş noktaları işleme dahil etmez
                    continue;
				if (Point.distance(atanamayan.get(i), atanamayan.get(j)) <= maxDistance){
					numCluster++;
					Cluster cluster = new Cluster(numCluster);
					clusters.add(cluster);
					cluster.setCentroid(pi);
					cluster.addPoint(pi);
					cluster.addPoint(pj);
					atanan.add(pi);
					atanan.add(pj);
                    alreadyProcessed.add(pi); 
                    alreadyProcessed.add(pj);
				}
			}
		}

        for(Point p : alreadyProcessed) {
            atanamayan.remove(p);
        }
        */
	}

	public static void algorithm(){

		boolean end = false;
		int times = 0;
		while(!end) {
			times++;
			prevRestSize = atanamayan.size();
			boolean finish = false;
			int iteration = 0;
			// Add in new data, one at a time, recalculating centroids with each new one. 
			while(!finish) {
				//Clear cluster state
				clearClusters();
				List<Point> lastCentroids = getCentroids();
				//Assign points to the closest cluster while checking the constraints
				assignCluster();
				//Calculate new centroids.
				calculateCentroids();//Calculates total distance between new and old Centroids
				iteration++;
				List<Point> currentCentroids = getCentroids();
				double distance = 0;
				for(int i = 0; i < lastCentroids.size(); i++) {
					distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
				}
				System.out.println("#################");
				System.out.println("Iteration: " + times+ "-" +iteration);
				System.out.println("Centroid distances: " + distance);
				if(distance == 0) {
					finish = true; 
				}
			}
			System.out.println("*************************");
			
			optimizeCluster();
			
			System.out.println("*****************");
			System.out.println("Number of Optimization 	: " + times);
			System.out.println("Assigned point : " + atanan.size());
			System.out.println("Unassigned point :" + atanamayan.size());	
			System.out.println("Number of cluster :"+numCluster);
			
			if ( points.size() == atanan.size() || prevRestSize == atanamayan.size()) {
				end = true;
			} 
		}
	}
	
	public static void clearClusters() {
		for(Cluster cluster : clusters) {
			cluster.clear();
			cluster.area = 0;
		}
		System.out.println("---");
	}
	
	
	public static void printResults() throws IOException {

		
		String filename1 = "C:/Users/Asus/workspace/KMeans/output.xlsx" ;
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("points");

		sheet.createRow(0).createCell(0).setCellValue("Arazi Numarası");
		sheet.getRow(0).createCell(1).setCellValue("X Koordinatı");
		sheet.getRow(0).createCell(2).setCellValue("Y Koordinatı");
		sheet.getRow(0).createCell(3).setCellValue("Arazi Alanı");
		sheet.getRow(0).createCell(4).setCellValue("Cluster ID");
		sheet.getRow(0).createCell(5).setCellValue("Cluster's X");
		sheet.getRow(0).createCell(6).setCellValue("Cluster's Y");
		sheet.getRow(0).createCell(7).setCellValue("Cluster Alanı");
		sheet.getRow(0).createCell(8).setCellValue("Cluster İşlem Süresi");
		
		int rownum = 1;
		for(Cluster c : clusters){
			for(Point p : c.getPoints()){
				sheet.createRow(rownum);	
				sheet.getRow(rownum).createCell(0).setCellValue(String.valueOf(rownum));
				sheet.getRow(rownum).createCell(1).setCellValue(String.valueOf(p.getX()));
				sheet.getRow(rownum).createCell(2).setCellValue(String.valueOf(p.getY()));
				sheet.getRow(rownum).createCell(3).setCellValue(String.valueOf(p.getArea()));
				sheet.getRow(rownum).createCell(4).setCellValue(String.valueOf(c.getId()));
				sheet.getRow(rownum).createCell(5).setCellValue(String.valueOf(c.getCentroid().getX()));
				sheet.getRow(rownum).createCell(6).setCellValue(String.valueOf(c.getCentroid().getY()));
				sheet.getRow(rownum).createCell(7).setCellValue(String.valueOf(c.getArea()));
				sheet.getRow(rownum).createCell(8).setCellValue(String.valueOf((c.getArea())*Heuristic.birimTaranan));
				rownum++;
			}
		}
		
		FileOutputStream fileOut = new FileOutputStream(filename1);
		wb.write(fileOut);
		fileOut.close();
		wb.close();
		
		/* kontrol??
		FileInputStream fis1 = new FileInputStream(new File(Point.filename));
		XSSFWorkbook wb1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = wb1.getSheetAt(0);

		sheet1.getRow(0).createCell(6).setCellValue("X coord");
		sheet1.getRow(0).createCell(7).setCellValue("Y coord");
		sheet1.getRow(0).createCell(8).setCellValue("Cluster ID");
		sheet1.getRow(0).createCell(9).setCellValue("Cluster's X");
		sheet1.getRow(0).createCell(10).setCellValue("Cluster's Y");
		List<Point> ps = new ArrayList<Point>();
		for(Point pp : atanan) {
			if(pp.getCluster() != -1 && !ps.contains(pp)) {
				ps.add(pp);
			}
		}
		usedCluster = ps.size();
		// aşağısı incelenecek
		int temp = 1;
		int cCount = numCluster;
		int cIndex = 1;
		int prevI = 0;
		for(int i = 0; i < cCount; i++) {
			for(int j = 0; j < ps.size(); j++) {
				if(ps.get(j).getCluster() == i) {
					if(prevI != i) {
						prevI = i;
						cIndex++;
					}
					sheet1.getRow(temp).createCell(6).setCellValue(String.valueOf(ps.get(j).getX()));
					sheet1.getRow(temp).createCell(7).setCellValue(String.valueOf(ps.get(j).getY()));
					sheet1.getRow(temp).createCell(8).setCellValue("Cluster " + cIndex);
					sheet1.getRow(temp).createCell(9).setCellValue(String.valueOf(clusters.get(ps.get(j).getCluster()).getCentroid().getX()));
					sheet1.getRow(temp).createCell(10).setCellValue(String.valueOf(clusters.get(ps.get(j).getCluster()).getCentroid().getY()));
					sheet1.getRow(temp).createCell(11).setCellValue(String.valueOf(clusters.get(ps.get(j).getCluster()).getArea()));
					temp++;
				}
			}
		}

		if(atanamayan.size() > 0) {

			for(Point pp : atanamayan) {
				cIndex++;
				sheet1.getRow(temp).createCell(6).setCellValue(String.valueOf(pp.getX()));
				sheet1.getRow(temp).createCell(7).setCellValue(String.valueOf(pp.getY()));
				sheet1.getRow(temp).createCell(8).setCellValue("Cluster " + (cIndex));
				sheet1.getRow(temp).createCell(9).setCellValue("-1");
				sheet1.getRow(temp).createCell(10).setCellValue("-1");
				sheet1.getRow(temp).createCell(11).setCellValue(String.valueOf(pp.getArea()));
				temp++;
			}
		}

		wb1.write(new FileOutputStream("output222.xlsx"));
		wb1.close();
		*/
		System.out.println();
		System.out.println("Writing Outputs is done.");
		System.out.println();
		

	}

	public void distanceMatrix() throws IOException { // kontrol edilecek // dolu cluster ve eklenenler yazdırılacak

		String filename2 = "C:/Users/Asus/workspace/KMeans/distances.xlsx" ;
		
		//finalization();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Distances");
		double dist  = 0;

		XSSFRow rowhead = sheet.createRow((short)0);
		for(int i=0; i<clusters.size();i++){
			rowhead.createCell(i+1).setCellValue("Cluster " + (i+1));	
		}
		for(int i=0; i<clusters.size();i++){
			XSSFRow row = sheet.createRow((short)(i+1));
			row.createCell(0).setCellValue("Cluster " + (i+1));	
		}
		for(int i = 0; i<clusters.size(); i++) {
			for (int j =0; j<clusters.size(); j++){		
				dist = Point.distance(clusters.get(i).getCentroid(),clusters.get(j).getCentroid());
				sheet.getRow(j+1).createCell(i+1).setCellValue(dist);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(filename2);
		wb.write(fileOut);
		fileOut.close();
		wb.close();
		System.out.println("...");
		System.out.println("Writing Distance Matrix is done.");
	}


}
