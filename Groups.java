import java.util.ArrayList;
import java.util.List;

public class Groups {
	
	private int grupNo = 0;
	private double alan;
	private double islemSuresi;
	private int clusterSayisi;
	public List<Cluster> kumeler;
	private Point merkez;
	public int ekipNo;
	public int gunNo;
	
	public Groups(int gNo) {
		this.grupNo = gNo;
		this.alan = 0.0;
		this.islemSuresi = 0.0;
		this.clusterSayisi =0;
		this.kumeler = new ArrayList<Cluster>();
		this.merkez = new Point(0,0,0,0);	
	}
		
	public int getGrupNo() {
		return grupNo;
	}
	
	public void addKume(List<Cluster> cl){
		double tX=0, tY=0;
		for(int i =0; i<cl.size();i++){
			this.kumeler.add(cl.get(i));
			this.alan += cl.get(i).getArea();
			this.clusterSayisi++;
			tX += cl.get(i).getCentroid().getX();
			tY += cl.get(i).getCentroid().getY();
		}
		this.islemSuresi = calculateIslemSuresi(cl);
		this.merkez.setX(tX/this.clusterSayisi);
		this.merkez.setY(tY/this.clusterSayisi);
	}
	
	public static double calculateIslemSuresi(List<Cluster> g){
		double toplamMesafe = 0.0;
		double sure =0.0;
		double toplamAlan =0.0;
		for(int j=0;j<g.size();j++){
			toplamAlan += g.get(j).getArea();
		}
		for(int i=0;i<(g.size()-1);i++){
				toplamMesafe += Point.distance(g.get(i).getCentroid(), g.get(i+1).getCentroid());
		}
		sure += toplamAlan*Heuristic.birimTaranan;
		sure += g.size()*Heuristic.setup;
		sure += (toplamMesafe * 60 ) / Heuristic.yerEkipHizi;		
		return sure;
	}
	public static double mesafeHesaplama(List<Cluster> g){
		double toplamMesafe = 0;
		for(int i=0;i<(g.size()-1);i++){
			toplamMesafe += Point.distance(g.get(i).getCentroid(), g.get(i+1).getCentroid());
	}
		return toplamMesafe;
	}
	public static double calculateIslemSuresi(Groups g){
		double toplamMesafe = 0;
		double sure =0;
		for(int i=0;i<(g.kumeler.size()-1);i++){
				toplamMesafe += Point.distance(g.kumeler.get(i).getCentroid(), g.kumeler.get(i+1).getCentroid());
		}
		sure += g.alan*Heuristic.birimTaranan;
		sure += g.kumeler.size()*Heuristic.setup;
		sure += (toplamMesafe * 60 ) / Heuristic.yerEkipHizi;		
		return sure;
	}
	public static double calculateIslemSuresi(Cluster c){
		double sure =0;
		sure += c.getArea()*Heuristic.birimTaranan;	
		sure += Heuristic.setup;
		return sure;
	}
	public void setGrupNo(int grupNo) {
		this.grupNo = grupNo;
	}

	public double getAlan() {
		return this.alan;
	}

	public void setAlan(double alan) {
		this.alan = alan;
	}

	public double getIslemSuresi() {
		return this.islemSuresi;
	}

	public void setIslemSuresi(double islemSuresi) {
		this.islemSuresi = islemSuresi;
	}

	public int getClusterSayisi() {
		return this.clusterSayisi;
	}

	public void setClusterSayisi(int clusterSayisi) {
		this.clusterSayisi = clusterSayisi;
	}

	public List<Cluster> getKumeler() {
		return this.kumeler;
	}

	public void setKumeler(List<Cluster> kumeler) {
		this.kumeler = kumeler;
	}

	public Point getMerkez() {
		return this.merkez;
	}

	public void setMerkez(Point merkez) {
		this.merkez = merkez;
	}
	
	
}
