package test;

import graphe.Graph;

import java.io.IOException;
import java.text.ParseException;

public class graph_test {
    public static void main(String[] args) throws IOException, ParseException {
        Graph graph = new Graph("GreenEr_data.csv");
        //graph.g_diff("hour","09-09-2022 00:00:00", "09-11-2022 00:00:00");
        //graph.g_diff("day","09-09-2022", "10-11-2022");
    }

}