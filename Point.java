
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Point {

    private double x = 0;
    private double y = 0;
    private double a = 0;
    private int id = 0;
	public static String filename = "C:/Users/Asus/workspace/KMeans/fields.xlsx";

    public Point(double x, double y, double a, int i) // nesne kurucu
    {
        this.setX(x);
        this.setY(y);
        this.setA(a);
        this.setId(i);
    }
    public void setId(int idd){
    	this.id = idd;
    }
    public int getId(){
    	return this.id;
    }
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setA(double a) {
        this.a = a;
    }
    
    public double getArea() {
        return this.a;
    }
       
    //Calculates the distance between two points. Haversine formula
    protected static double distance(Point p1, Point p2) {
    	final double RADIUS_EARTH = 6371000; // metre cinsinden
		
	    double dLat = getRad(p2.getX() - p1.getX());
	    double dLong = getRad(p2.getY() - p1.getY());

	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(getRad(p1.getX())) * Math.cos(getRad(p2.getX())) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return (RADIUS_EARTH * c) ;
    }
    
    private static double getRad(Double x) {
	    return x * Math.PI / 180;
	    }
    
    //chooses random points from the data
    protected static Point choosingRandomPoint(List<Point> poList){

    	int p = (int)((Math.random()*Math.random()) * poList.size());
    	Point po = poList.get(p);
    	return new Point(po.getX(), po.getY(), 0, 0);
    	
    }
  
    
    //Creates random point
    protected static Point createRandomPoint(Point minP, Point maxP) {  	
    	Random r = new Random();
    	double x = minP.getX() + (maxP.getX() - minP.getX()) * r.nextDouble();
    	double y = minP.getY() + (maxP.getY() - minP.getY()) * r.nextDouble();
    	return new Point(x,y,0,0);
    }
    public static Point minimumBul(List<Point> pList){
       	double minX=999.9, minY=999.9;
    	double xx=0, yy=0;
    	for(int i=0;i<pList.size();i++){ 		
    		xx = pList.get(i).getX();
    		yy = pList.get(i).getY();
    		if(xx<minX){ minX = xx;}
    		if(yy<minY){ minY = yy;}
    	}
    	return new Point(minX, minY, 0,0);
    }
    public static Point maximumBul(List<Point> pList){
       	double maxX=0.0, maxY=0.0;
    	double xx=0, yy=0;
    	for(int i=0;i<pList.size();i++){ 		
    		xx = pList.get(i).getX();
    		yy = pList.get(i).getY();
    		if(maxX<xx){ maxX=xx;}
    		if(maxY<yy){ maxY=yy;}
    	}
    	return new Point(maxX,maxY,0,0);
    }
    //Create point
    protected static Point createReadedPoint(double x, double y, double a, int id) {
    	return new Point(x,y,a,id);
    }
    
    //Read points Yeni
    protected static List<Point> createReadedPoints() {
    	System.out.println("Reading File... Please Wait...");
    	List<Point> points = new ArrayList<Point>();
    	
    	try {
    		FileInputStream fis = new FileInputStream(new File(filename)); // dosyayı aç
			XSSFWorkbook wb = new XSSFWorkbook(fis); // excel i al
			XSSFSheet sheet = wb.getSheetAt(0); // // ilk sheeti al
			XSSFRow row;
			XSSFCell cell;
			
			Iterator<?> rows = sheet.rowIterator();
			int num = 1;
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				Iterator<?> cells = row.cellIterator();
				
				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();
					
					if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					{
						double x = cell.getNumericCellValue();
						cell = (XSSFCell) cells.next();
						double y = cell.getNumericCellValue();
						cell = (XSSFCell) cells.next();
						double a = cell.getNumericCellValue();
						points.add(createReadedPoint(x,y,a,num));
						num++;
					}					
				}
			}	wb.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Reading File is done. Please Wait...");
    	return points;
    }
    
    public String toString() {
    	return "("+x+","+y+")";
    }
}
