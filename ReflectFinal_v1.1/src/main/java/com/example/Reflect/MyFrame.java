package com.example.Reflect;

import com.example.Reflect.Mind.*;
//import com.example.Reflect.Body.*;
import com.example.Reflect.Soul.*;

//import jakarta.websocket.server.ServerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MyFrame extends JFrame {

    JFrame frame = new JFrame("Reflect MBS v1.00");
    JPanel panelCont = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel mindPanel = new JPanel();

    JPanel bodyPanel = new JPanel();

    JPanel soulPanel = new JPanel();

    // Main Menu

    JButton mind = new JButton("Mind");
    JButton body = new JButton("Body");
    JButton soul = new JButton("Soul");

    // Mind Menu

    JButton mindfulness = new JButton("Mindfulness");
    JButton reflect = new JButton("Reflect");
    JButton read = new JButton("Read");
    JButton return1 = new JButton("Return to Main Menu");

    // Body Menu

    JButton stretch = new JButton("Stretch");
    JButton calisthenics = new JButton("Calisthenics");
    JButton return2 = new JButton("Return to Main Menu");
    JButton weights = new JButton("Weights");


    // Soul Menu

    JButton gratitude = new JButton("Gratitude");
    JButton kindness = new JButton("Kindness");
    JButton return3 = new JButton("Return to Main Menu");

    CardLayout c1 = new CardLayout();


    MyFrame() {
        panelCont.setLayout(c1);

        //Main Menu

        mainPanel.add(mind);
        mainPanel.add(body);
        mainPanel.add(soul);

        ImageIcon imageIcon = new ImageIcon("/Users/hiep/Desktop/background1.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(imageIcon);

        mainPanel.setBackground(Color.white);
        mainPanel.add(backgroundLabel);

       // Mind Menu
        mindPanel.add(mindfulness);
        mindPanel.add(reflect);
        mindPanel.add(read);
        mindPanel.add(return1);

        ImageIcon imageIcon2 = new ImageIcon("/Users/hiep/Desktop/background1.png");
        Image image2 = imageIcon2.getImage();
        Image scaledImage2 = image2.getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon2 = new ImageIcon(scaledImage2);

        JLabel backgroundLabel2 = new JLabel(imageIcon2);

        mindPanel.setBackground(Color.white);
        mindPanel.add(backgroundLabel2);


        //Body Menu
        bodyPanel.add(stretch);
        bodyPanel.add(calisthenics);
        bodyPanel.add(weights);
        bodyPanel.add(return2);

        ImageIcon imageIcon3 = new ImageIcon("/Users/hiep/Desktop/background1.png");
        Image image3 = imageIcon3.getImage();
        Image scaledImage3 = image3.getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon3 = new ImageIcon(scaledImage3);

        JLabel backgroundLabel3 = new JLabel(imageIcon3);

        bodyPanel.setBackground(Color.white);
        bodyPanel.add(backgroundLabel3);



        //Soul Menu
        soulPanel.add(gratitude);
        soulPanel.add(kindness);
        soulPanel.add(return3);

        ImageIcon imageIcon4 = new ImageIcon("/Users/hiep/Desktop/background1.png");
        Image image4 = imageIcon4.getImage();
        Image scaledImage4 = image4.getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon4 = new ImageIcon(scaledImage4);

        JLabel backgroundLabel4 = new JLabel(imageIcon4);

        soulPanel.setBackground(Color.white);
        soulPanel.add(backgroundLabel4);



        //adding panels to panel cont

        panelCont.add(mainPanel, "1");
        panelCont.add(mindPanel,"2");
        panelCont.add(bodyPanel, "3");
        panelCont.add(soulPanel, "4");

        //button sizes
        mind.setPreferredSize(new Dimension(100, 50));
        body.setPreferredSize(new Dimension(100, 50));
        soul.setPreferredSize(new Dimension(100, 50));
        mindfulness.setPreferredSize(new Dimension(100, 50));
        reflect.setPreferredSize(new Dimension(100, 50));
        read.setPreferredSize(new Dimension(100, 50));
        return1.setPreferredSize(new Dimension(150, 50));
        stretch.setPreferredSize(new Dimension(100, 50));
        calisthenics.setPreferredSize(new Dimension(100, 50));
        return2.setPreferredSize(new Dimension(150, 50));
        weights.setPreferredSize(new Dimension(100, 50));
        gratitude.setPreferredSize(new Dimension(100, 50));
        kindness.setPreferredSize(new Dimension(100, 50));
        return3.setPreferredSize(new Dimension(150, 50));


        //mind panel

        mind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               c1.show(panelCont, "2");

            }
        });

        mindfulness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mindfulness button pushed");
                Mindfulness mindfulness = new Mindfulness();
                mindfulness.run();

            }
        });

        reflect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reflect button pushed");
                Reflect reflect = new Reflect();
                reflect.run();
            }
        });
        

        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Read read = new Read();
                read.run();
                //Reflect reflect = new Reflect();
                //System.out.println("All:" + reflect.getReflects().toString());
                //System.out.println("By Date: " + reflect.getReflectsByDate("04/30/23").toString());
            }
        });

        //body panel

        body.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "3");

            }
        });

        stretch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=FI51zRzgIe4"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        calisthenics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=hEXjX6nwDoY"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        weights.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = new File("/Users/hiep/Desktop/IMG_1328.mov");
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //soul panel

        soul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "4");

            }
        });

        gratitude.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        kindness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Kindess Button pushed");
                Kindness kindness = new Kindness();
                kindness.run();
            }
        });


        //return to home buttons

        return1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "1");
            }
        });

        return2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "1");
            }
        });

        return3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "1");
            }
        });

        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}




