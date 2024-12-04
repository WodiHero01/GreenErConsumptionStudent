/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *  class managing the click on the button 
 * @author ramdaneb
 */
public class DateExampleButton implements ActionListener{

    String minDate;
    String maxDate;
    JTextField date;
    /**
     * 
     * @param minDate : initial date
     * @param maxDate : final date
     * @param date : the date entered in the text field
     */
    public DateExampleButton(String minDate, String maxDate,JTextField date){
        this.minDate=minDate;
        this.maxDate=maxDate;
        this.date=date;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date min = format.parse(this.minDate);
            Date max = format.parse(this.maxDate);
            Date dateText = format.parse(this.date.getText());
                if (dateText.after(min) && dateText.before(max)) {
                    System.out.println( " YES!! the indicated date is included between the two dates");
                } else{
                    System.err.println( " NO!! the indicated date is not included between the two dates");
                }


        } catch (ParseException ex) {
            System.err.println(" Date format ERROR!! Could you re-enter the date in the correct format? ");
        }
    }
    
}
