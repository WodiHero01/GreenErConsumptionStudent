package test;

import principale.Graph_green;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class graph_test {
    public static void main(String[] args) throws IOException, ParseException {
        Graph_green graph = new Graph_green("GreenEr_data.csv");
        graph.g_diff("hour","09-09-2022 00:00:00", "09-11-2022 00:00:00");
        graph.g_diff("day","09-09-2022", "09-11-2022");
    }

}