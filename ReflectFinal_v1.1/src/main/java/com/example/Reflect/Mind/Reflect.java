package com.example.Reflect.Mind;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.example.Reflect.controller.ServerController;
import com.example.Reflect.models.ReflectModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reflect extends JFrame {

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    String date2 = dateFormat.format(date);

    private String formattedDate;
    private String answer = "";
    private String[] answers = new String[10];

    JLabel label;
    JButton button;
    ServerController server = new ServerController();

    public Reflect() {
    }


    public Reflect(String date, String[] answers) {
        this.formattedDate = date;
        this.answers = answers;
    }

    public void run() {
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


        final int[] questionIndex = {0};
        final String[] currentQuestion = {questions[questionIndex[0]]};

        JFrame frame = new JFrame();
        TextArea text = new TextArea();

        JSONArray array = new JSONArray();
        
        button = new JButton("Save");
        button.setPreferredSize(new Dimension(40, 40));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                answer = text.getText();

                boolean allQuestionsAnswered = false;

                if (questionIndex[0] == 0) {
                    answers[0] = answer;
                } else if (questionIndex[0] == 1) {
                    answers[1] = answer;
                } else if (questionIndex[0] == 2) {
                    answers[2] = answer;
                } else if (questionIndex[0] == 3) {
                    answers[3] = answer;
                } else if (questionIndex[0] == 4) {
                    answers[4] = answer;
                } else if (questionIndex[0] == 5) {
                    answers[5] = answer;
                } else if (questionIndex[0] == 6) {
                    answers[6] = answer;
                } else if (questionIndex[0] == 7) {
                    answers[7] = answer;
                } else if (questionIndex[0] == 8) {
                    answers[8] = answer;
                } else if (questionIndex[0] == 9) {
                    answers[9] = answer;
                    allQuestionsAnswered = true;
                }


                frame.dispose();

                // update currentQuestion and show the next question
                questionIndex[0]++;
                if (questionIndex[0] < questions.length) {
                    final String currentQuestion = questions[questionIndex[0]];
                    label.setText(currentQuestion);
                    text.setText("");
                    frame.setVisible(true);
                }

                if (allQuestionsAnswered) {
                    //System.out.println(new Reflect(date2, answers));
                    server.postReflect(answers);
                }
            }
        });

        frame.setLayout(new BorderLayout());
        label = new JLabel(currentQuestion[0]);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        frame.add(text);
        frame.setSize(600, 300);
        frame.add(label, BorderLayout.NORTH);
        frame.add(button, BorderLayout.SOUTH);
        frame.setTitle("Journal");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public ArrayList<ReflectModel> getReflects() {
        try {
            return server.getReflects();
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ReflectModel> getReflectsByDate(String date) {
        try {
            return server.getReflectsByDate(date);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Reflect{" +
                "date=" + date2 +
                ", answers: " + answers;
    }
}
