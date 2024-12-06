package test;

import data.DataContainer;

import java.io.IOException;
import java.util.Arrays;


public class MainTest {
    public static void main(String[] args) throws IOException {
//        DataContainer dcg = new DataContainer("GreenEr_data.csv");
//        System.out.println(dcg.toString());
        //System.out.println(Arrays.toString(dcg.getData("Outdoor Temperature")));
        DataContainer dc = new DataContainer("GreenEr_data.csv");
        System.out.println(dc.toString());
        System.out.println(dc.getNumberOfSamples());

        String[] availableVariables = dc.getAvailableVariables();

        Double[] dataTest = dc.getData(availableVariables[1]);
        System.out.println("name : "+dataTest[1]);
        System.out.println(Arrays.toString(dataTest));


//        try {
//            System.out.println("date"+ Arrays.toString(dca.getDailyDates()));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            System.out.println("date"+ Arrays.toString(dca.getDates()));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Date[] date = dca.getDates();
//        }
//        catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }
}
