package gui;

import javax.swing.*;
import java.awt.*;

public class Gui_choice extends JFrame {
    private JTextField text_choice;
    public Gui_choice () {
        JPanel panel_choice = new JPanel();
        panel_choice.setLayout(new GridLayout(1, 2));
        JLabel label_choice = new JLabel("Please select the data you want to plot");
        text_choice = new JTextField(15);
        JButton button_choice = new JButton("OK");
        panel_choice.add(label_choice);
        panel_choice.add(text_choice);
        panel_choice.add(button_choice);
        add(panel_choice);
        button_choice.addActionListener(new Gui_project(this));
        pack();
        setVisible(true);


    }
    public String getFileName(){
        return text_choice.getText();
    }

    public static void main(String[] args) {
        Gui_choice frame = new Gui_choice();

    }
}
