import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class drawingComponent extends JComponent {

	private static final long serialVersionUID = 1L;


	
	public void paintComponent(Graphics g)
	{
		List<Point> points = new ArrayList<Point>();
		points.addAll(Point.createReadedPoints());
		
		Graphics2D g2 = (Graphics2D) g;
		
		int margin = 5;
		double minX, maxX, minY, maxY, scaleX, scaleY;
		int recW = 800, recH = 550;
		int dX=0, dY=0;
		minX= Point.minimumBul(points).getY();
		minY= Point.minimumBul(points).getX();
		maxX= Point.maximumBul(points).getY();
		maxY= Point.maximumBul(points).getX();
		scaleX = recW / (maxX - minX);
		scaleY = -recH / (maxY - minY);
		
		g2.setColor(Color.WHITE);
		g2.fillRect(125, 3, 815, 565);
		g2.setColor(Color.BLACK);
		g2.drawRect(125, 3, 815, 565);		
		g2.drawString(String.valueOf(minX), 110, 585);
		g2.drawString(String.valueOf(maxX), 920, 585);
		g2.drawString(String.valueOf(minY), 65, 555);
		g2.drawString(String.valueOf(maxY), 65, 20);
		g2.setFont(new Font("Tahoma", Font.BOLD, 22));
		g2.drawString("X Axis" , 485, 605);
		g2.drawString("Y Axis", 27, 290);
		int xLine = 20;
		for(int i =0; i<=recW/xLine;i++){
			for(int j =0;j<142;j++){
			g2.drawLine((125+i*(xLine)), 3+4*j, (125+i*(xLine)), 4+4*j);
			}
		}
		for(int i =0; i<=recH/xLine;i++){
			for(int j=0;j<204;j++){
			g2.drawLine(125+4*j, (3+i*(xLine)), 126+4*j, (3+i*(xLine)));
			}
		}
		
		for(int i =0; i<Heuristic.gruplar.size();i++){
			Color c = new Color((int)(Math.random()*400000000));
			g2.setPaint(c);
			for(int j=0;j<Heuristic.gruplar.get(i).getClusterSayisi();j++){
				dX = 130 + (int)(((Heuristic.gruplar.get(i).getKumeler().get(j).getCentroid().getY() - minX) * scaleX) + margin - 2.5); 
				dY = 558 + (int)(((Heuristic.gruplar.get(i).getKumeler().get(j).getCentroid().getX() - minY) * scaleY) + margin - 2.5);
				g2.fillRect((int)dX, (int)dY, 5, 5);
				for(int k=0; k<Heuristic.gruplar.get(i).getKumeler().get(j).points.size();k++){
					dX = 130 + (int)(((Heuristic.gruplar.get(i).getKumeler().get(j).getPoints().get(k).getY() - minX) * scaleX) + margin - 1.5); 
					dY = 558 + (int)(((Heuristic.gruplar.get(i).getKumeler().get(j).getPoints().get(k).getX() - minY) * scaleY) + margin - 1.5);
					g2.fillOval((int)dX, (int)dY, 3, 3);
				}
			}
			dX = 130 + (int)((Heuristic.gruplar.get(i).getMerkez().getY() - minX) * scaleX) + margin - 6; 
			dY = 558 + ((int)((Heuristic.gruplar.get(i).getMerkez().getX() - minY) * scaleY) + margin - 6);
			g2.setPaint(Color.BLACK);
			g2.drawOval((int)dX, (int)dY, 12, 12);
			g2.drawOval((int)dX+1, (int)dY+1, 10, 10);
			g2.setPaint(Color.RED);
			g2.drawString(String.valueOf(Heuristic.gruplar.get(i).ekipNo), (int)dX+2, (int)dY+2);
			g2.fillOval((int)dX+2, (int)dY+2, 8, 8);
		}
	}
}
