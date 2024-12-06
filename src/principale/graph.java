package principale;

import data.DataContainer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class graph {
    public static void main(String[] args) throws IOException {
        DataContainer dca = new DataContainer("GreenEr_data.csv");
        double sizedata = dca.getNumberOfSamples();
        Date[] dateA;
        try {
            dateA = dca.getDates();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String[] availableVariables = dca.getAvailableVariables();

        Double[] dataA = dca.getData(availableVariables[0]);
        TimeSeries timeSeries1 = new TimeSeries("Consumption");
        for (int i = 0; i < sizedata; i++)
            timeSeries1.add(new Hour(dateA[i]), dataA[i]);
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(timeSeries1);
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart(availableVariables[0],"time (h)","Consumption (kW)",timeSeriesCollection,true, true, false));
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
