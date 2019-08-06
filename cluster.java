
import java.util.ArrayList;
import java.util.List;

public class Cluster {
	
	public List<Point> points;
	private Point centroid;
	public double area;
	private int id;
	public int count = 0;
	public static int clusterCount =0;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList<Point>();
		this.centroid = null;
		this.area = 0;
		clusterCount++;
	}
		
	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
		this.area += point.getArea();
		this.count++;
	}
	
	public void removePoint(int index) {
		this.area -= points.get(index).getArea();
		this.points.remove(index);	
		this.count--;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public int getId() {
		return id;
	}
	
	public void clear() {
		this.points.clear();
		this.area = 0;
	}
	
}
