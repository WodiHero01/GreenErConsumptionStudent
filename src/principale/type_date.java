package principale;

import org.jfree.data.time.TimeSeries;

import java.text.ParseException;

public interface type_date {
    public TimeSeries ts_hour (String variableName,String start_date, String end_date) throws ParseException;
    public TimeSeries ts_day (String variableName,String start_date, String end_date) throws ParseException;
    public TimeSeries ts_month (String variableName,String start_date, String end_date) throws ParseException;
}
