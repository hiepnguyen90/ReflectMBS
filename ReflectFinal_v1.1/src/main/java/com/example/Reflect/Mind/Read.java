package com.example.Reflect.Mind;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.example.Reflect.models.ReflectModel;




public class Read extends JFrame {
JLabel label;
JButton button;
String stringAnswer;

    
    public void run(){
        
        
        JFrame frame = new JFrame();
        JTextField text = new JTextField();
        

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        button = new JButton("Search");
        button.setPreferredSize(new Dimension(90, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stringAnswer = text.getText();
                    Reflect reflect = new Reflect();

                    ArrayList<ReflectModel> reflections = reflect.getReflectsByDate(stringAnswer);
                    if (reflections.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Cannot find your search, try another date.");
                    } else {
                        
                    
                
                    // Get date
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
                    String date2 = dateFormat.format(date);


                    String[] questions = {
                        "Lets talk about what you are Grateful for today",
                        "What are you Excited about?",
                        "Tell me something nice about your self",
                        "Did you Exercise or Stretch at all?",
                        "Tell me about your day!",
                        "How are we going to improve",
                        "Tell me about your emotions today",
                        "Tell me about your thoughts",
                        "How did you love yourself today?",
                        "How did you love others today?"
                    };

                    for(ReflectModel r : reflections) {
                        String message = "<html><b>Date:</b> " + date2 + "<br/><br/>";
                        String[] answers = r.getAnswers();
                        for (int i = 0; i < answers.length; i++) {
                            message += "<html><b>" + questions[i] + "</b></html>" + "\n" + answers[i] + "\n\n";
                        }
                        System.out.println(message);
                    Font font = new Font("Arial", Font.PLAIN, 16);

                    // Set the font of the JOptionPane
                    UIManager.put("OptionPane.messageFont", font);

                    JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
                    }

                    
                }

            }
        });

        label = new JLabel("Which entry would you like to find?");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(label);
        panel.add(text);
        panel.add(button);

        text.setPreferredSize(new Dimension(90, 30));
        text.setMaximumSize(new Dimension(90, 30));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(Box.createVerticalGlue());
        frame.getContentPane().add(panel);
        frame.getContentPane().add(Box.createVerticalGlue());

        frame.setSize(450, 125);
        frame.setTitle("Journal");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
