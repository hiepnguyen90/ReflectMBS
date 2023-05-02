package com.example.Reflect.Soul;

import javax.swing.*;
import java.awt.*;

public class Kindness extends JFrame {


    public Kindness() {
    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);

        JOptionPane.showMessageDialog(frame, "<html><div style='text-align:center;'>" + RandomKindness(), "Random Kindness Prompt", JOptionPane.DEFAULT_OPTION);

    }

    public int random() {
        int rand = (int) (Math.random() *2) +1;

        return rand;
    }

    public String RandomKindness(){
        String output = "";
        switch(random()) {
            case 1:
                output += "<b><center>The Power of Hello: </b><br>" +
                        "Today greet everyone that you encounter with a Hello and a Smile, and see how people's faces light up. <br>" +
                        "Let people know that you know they exist, every human is deserves to be acknowledged</center></html>";
                break;

                case 2:
                    output += "<b><center>Use your Phone as a Vehicle of Love: </b><br>"
                            + "Today call somebody you love, and let them know you love them, and listen to them smile through the phone. <br>"
                            + "Such a simple gesture could make their day!</center></html>";
                    break;
            default: output += "test3";
        }
        return output;
    }
}
