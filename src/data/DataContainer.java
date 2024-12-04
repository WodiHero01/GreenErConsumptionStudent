package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class DataContainer {

    // liste
    ArrayList<String> timeStrings;
    ArrayList<String> orderedVariableNames;
    TreeMap<String, ArrayList<Double>> data;
    TreeMap<String, ArrayList<String>> dataTimesString;
    int numberOfSamples = 0;

    /**
     *
     * @param csvFileName fichier de données à lire
     * @throws IOException
     */
    public DataContainer(String csvFileName) throws IOException {
        orderedVariableNames = new ArrayList<String>();
        data = new TreeMap<String, ArrayList<Double>>();
        dataTimesString = new TreeMap<String, ArrayList<String>>();
        int numberOfVariables = 0;

        timeStrings = new ArrayList<String>();
        ArrayList<String> orderedVariableNamesInFile = new ArrayList<>();
        int numberOfVariablesInFile = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFileName));

        String line;
        line = bufferedReader.readLine();
        String[] tokens = line.split(",");

        for (int i = 1; i < tokens.length; i++) {
            orderedVariableNames.add(tokens[i]);
            orderedVariableNamesInFile.add(tokens[i]);
            data.put(tokens[i], new ArrayList<>());
            dataTimesString.put(tokens[i], new ArrayList<>());
            numberOfVariables++;
            numberOfVariablesInFile++;
        }
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length != 1) {
                for (int i = 0; i < numberOfVariablesInFile + 1; i++) {
                    if (i == 0) {
                        timeStrings.add(values[i]);
                        dataTimesString.get(orderedVariableNamesInFile.get(i)).add(values[i]);
                    } else {
                        data.get(orderedVariableNamesInFile.get(i - 1)).add(Double.parseDouble(values[i]));
                    }
                }
                count++;
            }
        }
        bufferedReader.close();

        numberOfSamples = timeStrings.size();
    }

    /**
     * retourne le nombre de ...
     *
     * @return number of samples
     */
    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public int getNumberOfVariables() {
        return data.size();
    }

    public String[] getTimeStrings() {
        return timeStrings.toArray(new String[numberOfSamples]);
    }

    public Date[] getDates() throws ParseException {
        String val = "";
        Date[] dates = new Date[numberOfSamples];
        DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        for (int i = 0; i < numberOfSamples; i++) {
            val = timeStrings.get(i);

            dates[i] = format.parse(val);
        }
        return dates;
    }

    public String[] getAvailableVariables() {
        return orderedVariableNames.toArray(new String[getNumberOfVariables()]);
    }

    public Double[] getData(String variableName) {
        return data.get(variableName).toArray(new Double[data.get(variableName).size()]);
    }

    public void addData(String variableName, double[] values) {
        if (values.length != getNumberOfSamples()) {
            throw new RuntimeException(variableName + " has " + values.length + " samples instead of " + getNumberOfSamples());
        }
        if (data.containsKey(variableName)) {
            throw new RuntimeException(variableName + " already exists");
        }
        orderedVariableNames.add(variableName);
        ArrayList<Double> newValues = new ArrayList<>();
        for (double value : values) {
            newValues.add(value);
        }
        data.put(variableName, newValues);
    }

    public void addData(String variableName, Double[] values) {
        double[] primitiveValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            primitiveValues[i] = values[i];
        }
        addData(variableName, primitiveValues);
    }

    @Override
    public String toString() {
        String string = getNumberOfVariables() + " variables: ";
        String firstRow = "[";
        String lastRow = "[";
        for (String variableName : getAvailableVariables()) {
            string += variableName + ", ";
            Double[] values = getData(variableName);
            firstRow += values[0] + ", ";
            lastRow += values[numberOfSamples - 1] + ", ";
        }
        string += "\nnumber of data: " + numberOfSamples + "\n";
        string += getTimeStrings()[0] + ": " + firstRow + "]\n...\n" + getTimeStrings()[numberOfSamples - 1] + ": " + lastRow + "]\n";
        return string;
    }

    public Date[] getDailyDates() throws ParseException {
        ArrayList<String> dailyTimesString = new ArrayList<>();
        int j = 0;
        while ((j < this.numberOfSamples) && ((j + 24) < this.numberOfSamples)) {
            dailyTimesString.add(this.timeStrings.get(j));
            j+=24;
        }

        Date[] dates = new Date[dailyTimesString.size()];
        DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        for (int i = 0; i < dailyTimesString.size(); i++) {
            dates[i] = format.parse(dailyTimesString.get(i));
        }
        return dates;
    }

    public static void main(String[] args) {
        try {
            DataContainer dataContainerGreenEr = new DataContainer("GreenEr_data.csv");
            DataContainer dataContainerA020 = new DataContainer("classRoom_4A020_data.csv");
            System.out.println(dataContainerGreenEr);
            //System.out.println(dataContainerA020);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
