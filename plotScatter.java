import java.awt.RenderingHints;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo of the fast scatter plot.
 *
 */
public class plotScatter extends ApplicationFrame {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float[][] data = new float[2][Heuristic.gruplar.size()];

    
    public plotScatter(String title) {

        super(title);
        populateData();
        NumberAxis domainAxis = new NumberAxis("X");
        domainAxis.setAutoRangeIncludesZero(false);
        NumberAxis rangeAxis = new NumberAxis("Y");
        rangeAxis.setAutoRangeIncludesZero(false);
        FastScatterPlot plot = new FastScatterPlot(data, domainAxis, rangeAxis);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

       // Paint pt = new GradientPaint(0,0,color.RED,100, 0,color.WHITE);
        
        JFreeChart chart = new JFreeChart("Fast Scatter Plot", plot);
        
  
        //chart.setLegend(null);

        // force aliasing of the rendered content..
        chart.getRenderingHints().put
            (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ChartPanel panel = new ChartPanel(chart, true);
        panel.setPreferredSize(new java.awt.Dimension(900, 670));
        //panel.setHorizontalZoom(true);
        //panel.setVerticalZoom(true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(20);
        panel.setMaximumDrawWidth(2000);
        
        setContentPane(panel);

    }

    private void populateData() {
    	
    	
    	
        for (int i = 0; i < Heuristic.gruplar.size(); i++) {
        	
            this.data[0][i] = (float)Heuristic.gruplar.get(i).getMerkez().getX();
            this.data[1][i] = (float)Heuristic.gruplar.get(i).getMerkez().getY();
        }

    }

    public static void grupCiz() {

    	plotScatter demo = new plotScatter("Grup Merkezleri");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

