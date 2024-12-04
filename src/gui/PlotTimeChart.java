package gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * 
 * Class to manage data plotting using Jfree Chart
 * 
 */
public class PlotTimeChart {

	public static void main(String[] args) { // be careful: this is not object oriented
		DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
		String[] stringDates = {"2015-04-01 00:00:00", "2015-04-01 01:00:00", "2015-04-01 02:00:00"};
		double[] value1 = {3,1,2};
		double[] value2 = {-1,2,1};

		Date[] dates = new Date[3];
		try {
			for(int i=0; i<3; i++)
				dates[i] = format.parse(stringDates[i]);
			
			TimeSeries timeSeries1 = new TimeSeries("value1");
			for(int i=0; i<3; i++)
				timeSeries1.add(new Hour(dates[i]),value1[i]);
			
			TimeSeries timeSeries2 = new TimeSeries("value2");
			for(int i=0; i<3; i++)
				timeSeries2.add(new Hour(dates[i]),value2[i]);
			
			TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
			timeSeriesCollection.addSeries(timeSeries1);
			timeSeriesCollection.addSeries(timeSeries2);
			
			JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("Toto","x (m)","y (m)",timeSeriesCollection,true, true, false));
			JFrame frame = new JFrame("Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(chartPanel);
			frame.pack();
			frame.setVisible(true);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
