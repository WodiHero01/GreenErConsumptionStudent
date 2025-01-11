package graphe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;

public class Graph extends Tseries{
    //private String[] availableVariables = getAvailableVariables();
    private Tseries ts;
    private String csvFileName;
    public Graph(String csvFileName) throws IOException {
        super(csvFileName);
        this.ts = new Tseries(csvFileName);
        this.csvFileName = csvFileName;

    }


    public void g_diff(String s_date_type, Date s_start, Date s_end) throws ParseException {
        TimeSeries prod;
        TimeSeries cons;
        if (s_date_type == "hour") {
            prod = ts_hour("Green_Er_production_kW", s_start, s_end);
            cons = ts_hour("Green_Er_Consumption_kW", s_start, s_end);
        }
        else{
            prod = ts_day("Green_Er_production_kW", s_start, s_end);
            cons = ts_day("Green_Er_Consumption_kW", s_start, s_end);
        }
        TimeSeries diff = new TimeSeries("Difference");

        for (int i =0; i < prod.getItemCount();i++) diff.add(prod.getTimePeriod(i), cons.getValue(i).doubleValue() - prod.getValue(i).doubleValue());
        TimeSeriesCollection ts_diff = new TimeSeriesCollection();
        ts_diff.addSeries(diff);
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("Différence entre production et consomattion du batiment","time (d)","Différence (kW)",ts_diff,true, true, false));
        JFrame frame = new JFrame("Différence entre la consommation et la production");
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }


    public void g_simple(String graph_type,String s_date_type,Date s_start, Date s_end) throws ParseException, IOException {
        TimeSeries pdata = new TimeSeries("Production");

        if (s_date_type == "hour") {
            pdata = ts.ts_hour(graph_type, s_start, s_end);
        }
        else{
            pdata = ts.ts_day(graph_type, s_start, s_end);
        }
        TimeSeriesCollection tscol = new TimeSeriesCollection();
        tscol.addSeries(pdata);
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart(graph_type,"time (d)",graph_type+" (kW)",tscol,true, true, false));
        JFrame frame = new JFrame(graph_type);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);


    }
}

