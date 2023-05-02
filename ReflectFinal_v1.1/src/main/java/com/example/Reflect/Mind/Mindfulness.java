package com.example.Reflect.Mind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mindfulness {
    JFrame frame;
    JLabel counterLabel;
    Timer timer;
    Timer timer2;
    int second;
    int second2;
    public void run() {
        frame = new JFrame();

        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);

        counterLabel = new JLabel("Ready?");
        counterLabel.setFont(new Font("Arial", Font.BOLD, 30));
        counterLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.add(counterLabel);
        frame.setVisible(true);

        second = 0;
        Font font = new Font("Arial", Font.BOLD, 20);
        UIManager.put("OptionPane.messageFont", font);

        JOptionPane.showMessageDialog(frame, "We're going to do a breathing technique that will recenter your mind\n" +
                "We will inhale for 5 seconds, hold, then exhale for 5 seconds");


        //breathe in begins

        breatheIn();
        timer.start();

    }

    public void breatheIn() {
        final int totalTime = 5; // Total time for inhale in seconds


        final JProgressBar progressBar = new JProgressBar(0, totalTime);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(300,100));

        timer = new Timer(1000, new ActionListener() {
            int remainingTime = totalTime;

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                counterLabel.setText("<html><div style='text-align: center;'>Inhale<br>" + remainingTime + "</div></html>");
                progressBar.setValue(totalTime - remainingTime);

                if (remainingTime == 0) {
                    counterLabel.setText("Hold");
                    timer.stop();

                    // Exhale begins
                    second2 = 6;
                    breatheOut();
                    timer2.start();
                }
            }
        });

        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressPanel.add(progressBar);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressPanel, BorderLayout.SOUTH);
        panel.add(counterLabel, BorderLayout.CENTER);
        frame.setContentPane(panel);
    }

        public void breatheOut(){

            final int totalTime = 5; // Total time for exhale in seconds
            final JProgressBar progressBar = new JProgressBar(0, totalTime); // reverse max and min values
            progressBar.setValue(totalTime);
            progressBar.setPreferredSize(new Dimension(300, 100));


            timer2 = new Timer(1000, new ActionListener() {
                int remainingTime = totalTime;

                @Override
                public void actionPerformed(ActionEvent e) {
                    remainingTime--;
                    counterLabel.setText("<html><div style='text-align: center;'>Exhale<br>" + remainingTime + "</div></html>");
                    progressBar.setValue(remainingTime); // set value to remaining time

                    if (remainingTime == 0) {
                        timer2.stop();

                        int confirmation = JOptionPane.showConfirmDialog(frame, "Would you like try again?", "Confirmation",
                                JOptionPane.YES_NO_OPTION);

                        //recursion starts here
                        if (confirmation == JOptionPane.YES_OPTION) {
                            counterLabel.setText("Ready?");
                            second = 0;
                            breatheIn();
                            timer.start();
                        } else {
                            frame.dispose();
                            timer2.stop();
                        }
                    }
                }
            });

            JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            progressPanel.add(progressBar);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(progressPanel, BorderLayout.SOUTH);
            panel.add(counterLabel, BorderLayout.CENTER);
            frame.setContentPane(panel);
        }

    }

    //        progressBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


