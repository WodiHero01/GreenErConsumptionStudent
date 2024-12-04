package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Class managing dates
 *
 * @author ramdaneb
 */
public class DateExample extends JFrame {

    String minDate;
    String maxDate;

    /**
     *
     * @param minDate : initial date
     * @param maxDate : final date
     */
    public DateExample(String minDate, String maxDate) {
        super("Date example");
        this.maxDate = maxDate;
        this.minDate = minDate;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setLocation(100, 100);
        super.setSize(new Dimension(800, 1000));

        // GridBag layout definition and association to the Content Pane of the JFrame
        Container contentPane = getContentPane();
        GridBagLayout bagGrid = new GridBagLayout();
        contentPane.setLayout(bagGrid);

        // Definition of the constraint object associated to the GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);  //top padding

        // creation of the first label 
        JLabel label2 = new JLabel(" Is the date bellow between " + this.minDate + " and " + this.maxDate + " ?");

        // Position of the component in the JFrame
        c.gridx = 0;
        // add the component to the JFrame 
        contentPane.add(label2, c);

        // Creation and add a new Label to the frame
        JTextField date = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 150; // set the width of the JTextField
        contentPane.add(date, c);

        // creation of the plot Button
        JButton plotButton = new JButton(" Display answer on the console ");
        // Add the ActionListner to the button
        plotButton.addActionListener(new DateExampleButton(this.minDate, this.maxDate, date));
        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(plotButton, c);

        super.pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public DateExample() {
        this("2023-10-01 00:00", "2023-10-31 00:00");
    }

    public static void main(String[] args) throws IOException, ParseException {
        new DateExample();
    }

}
