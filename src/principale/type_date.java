package principale;

import org.jfree.data.time.TimeSeries;

import java.text.ParseException;
import java.util.Date;

public interface type_date {
    public TimeSeries ts_hour (String variableName, Date start_date, Date end_date) throws ParseException;
    public TimeSeries ts_day (String variableName, Date start_date, Date end_date) throws ParseException;
    public TimeSeries ts_month (String variableName,String start_date, String end_date) throws ParseException;
}
