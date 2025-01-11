package graphe;

import data.DataContainer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Tseries extends DataContainer {
    /**
     * @param csvFileName fichier de données à lire
     *
     * @throws IOException
     */
    private DataContainer dataContainer;
    public Tseries(String csvFileName) throws IOException {
        super(csvFileName);
        this.dataContainer = new DataContainer(csvFileName);
    }

    public TimeSeries ts_hour(String variableName, Date s_start, Date s_end) throws ParseException {
        TimeSeries timeSeries = new TimeSeries(variableName);
        Date[] dates = dataContainer.getDates();
        Double[] data = dataContainer.getData(variableName);
        for (int i = 0; i < getNumberOfSamples(); i++)
            if(dates[i].compareTo(s_start) >= 0 && dates[i].compareTo(s_end) <= 0)
                timeSeries.add(new Hour(dates[i]), data[i]);
        return timeSeries;
    }


    public TimeSeries ts_day(String variableName, Date s_start, Date s_end) throws ParseException {
        TimeSeries timeSeries = new TimeSeries(variableName);
        Date[] dates = getDailyDates();
        Double[] data = getData(variableName);
        double sum = 0;
        Date current_day;
        for (int i = 0; i < dates.length; i++) {
            if (dates[i].compareTo(s_start) >= 0 && dates[i].compareTo(s_end) <= 0) {
                current_day = dates[i];
                for (int j = 0; j < 24; j++) {
                    sum += data[i+j];
                }
                timeSeries.add(new Day(current_day), sum);
                sum = 0;
            }


        }
        return timeSeries;
    }
}
