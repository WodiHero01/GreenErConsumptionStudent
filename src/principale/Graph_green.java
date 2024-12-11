package principale;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import data.DataContainer;

import javax.swing.*;

public class Graph_green extends DataContainer implements type_date{
    private String[] availableVariables = getAvailableVariables();
    public Graph_green(String csvFileName) throws IOException {
        super(csvFileName);
    }

    @Override
    public TimeSeries ts_hour(String variableName, String s_start, String s_end) throws ParseException {
        TimeSeries timeSeries = new TimeSeries(variableName);
        Date[] dates = getDates();
        Double[] data = getData(variableName);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date d_start = format.parse(s_start);
        Date d_end = format.parse(s_end);
        for (int i = 0; i < getNumberOfSamples(); i++)
            if(dates[i].compareTo(d_start) >= 0 && dates[i].compareTo(d_end) <= 0)
                timeSeries.add(new Hour(dates[i]), data[i]);
        return timeSeries;
    }
    @Override
    public TimeSeries ts_day(String variableName, String s_start, String s_end) throws ParseException {
        TimeSeries timeSeries = new TimeSeries(variableName);
        Date[] dates = getDates();
        Double[] data = getData(variableName);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date d_start = format.parse(s_start);
        Date d_end = format.parse(s_end);
        double sum = 0;
        for (int i = 0; i < getNumberOfSamples(); i+=24) {
            if (dates[i].compareTo(d_start) >= 0 && dates[i].compareTo(d_end) <= 0) {
                for (int j = 0; j < 24; j++) {
                    sum += data[i+j];
                }

            }
            timeSeries.add(new Day(dates[i]), sum);
            sum = 0;
        }
        return timeSeries;

    }

    @Override
    public TimeSeries ts_month(String variableName,String start_date, String end_date) throws ParseException {
        return null;
    }


    public void g_diff( String s_date_type,String s_start, String s_end) throws ParseException {
        TimeSeries prod = new TimeSeries("Production");
        TimeSeries cons = new TimeSeries("Consommation");
        if (s_date_type=="hour") {
            prod = ts_hour(availableVariables[1], s_start, s_end);
            cons = ts_hour(availableVariables[0], s_start, s_end);
        }
        else{
            prod = ts_day(availableVariables[1], s_start, s_end);
            cons = ts_day(availableVariables[0], s_start, s_end);
        }
        TimeSeries diff = new TimeSeries("Difference");

        for (int i =0; i < prod.getItemCount();i++) diff.add(prod.getTimePeriod(i), cons.getValue(i).doubleValue() - prod.getValue(i).doubleValue());
        TimeSeriesCollection ts_diff = new TimeSeriesCollection();
        ts_diff.addSeries(diff);
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart(availableVariables[1],"time (h)","Différence (kW)",ts_diff,true, true, false));
        JFrame frame = new JFrame("Différence entre la consommation et la production");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

