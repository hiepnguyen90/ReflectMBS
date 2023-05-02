package com.example.Reflect.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JacksonJsonParser;

import com.example.Reflect.Mind.Reflect;
import com.example.Reflect.models.ReflectModel;
public class ServerController {
    private final HashSet<ReflectModel> reflectsSeen = new HashSet<>();
    // why a HashSet??
    // Root URL
    String rootURL = "http://localhost:8080";

    public ArrayList<ReflectModel> getReflects() throws IOException, ParseException {
        // Reflections
        final StringBuilder informationStringReflections;
        ArrayList<ReflectModel> reflections = new ArrayList<>();
        // Create connection
        final URL urlReflections = new URL(rootURL + "/reflect");
        final HttpURLConnection conn;
        conn = (HttpURLConnection) urlReflections.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.connect();
        // Get Server Status
        int responseCode = conn.getResponseCode();
        // Check if server is online
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            // Get Messages
            informationStringReflections = new StringBuilder();
            Scanner scan = new Scanner(urlReflections.openStream());
            while (scan.hasNext()) {
                informationStringReflections.append(scan.nextLine());
            }
            scan.close();
        }
        // Parse Messages from JSON Data
        JSONParser parse = new JSONParser();
        JSONArray dataObjectMessages = (JSONArray) parse.parse(String.valueOf(informationStringReflections)); // Messages
        // Add all reflection data into ArrayList
        for (Object o : dataObjectMessages) {
            Long id = (Long) ((JSONObject) o).get("id");
            String date = (String) ((JSONObject) o).get("date");
            JSONArray answerArrayJson = (JSONArray) ((JSONObject) o).get("answers");
            List<String> answerList = new ArrayList<>();
            for (int i = 0; i < answerArrayJson.size(); i++) {
                answerList.add((String) answerArrayJson.get(i));
            }
            String[] answer = new String[answerList.size()];
            answerList.toArray(answer);
            
            ReflectModel reflect = new ReflectModel(id, date, answer);
            reflections.add(reflect);
        }
        
        conn.disconnect();
        return reflections;
    }
    
    public ArrayList<ReflectModel> getReflectsByDate(String date) throws IOException, ParseException {
        final ArrayList<ReflectModel> reflections = getReflects();
        final ArrayList<ReflectModel> reflectionsForDate = new ArrayList<>();
        for (ReflectModel r : reflections) {
            try {
                if (r.getDate().equals(date)) {
                    reflectionsForDate.add(r);
                }
            } catch (NullPointerException e) {
                //System.out.println("No date");
            }
            
        }
        return reflectionsForDate;
    }
    /*
    public ArrayList<Message> readMessages() throws IOException, ParseException {
        ArrayList<Message> messages = getMessages();
        // Iterate through read messages
        for (Message read : messagesSeen) {
            // Use message comparator, if messages are the same (difference = 0), then remove message from messages
            messages.removeIf(message -> 0 == read.compareTo(message));
        }
        // Add all messages to read
        messagesSeen.addAll(messages);
        // Return messages
        if (messages.size() == 0) {
            messages.add(new Message("No new messages", "UnderARock", "You"));
        }
        return messages;
    }
    public ArrayList<Message> readMessages(Id id) throws IOException, ParseException {
        ArrayList<Message> messages = getMessagesForId(id);
        // Iterate through read messages
        for (Message read : messagesSeen) {
            // Use message comparator, if messages are the same (difference = 0), then remove message from messages
            messages.removeIf(message -> 0 == read.compareTo(message));
        }
        // Add all messages to read
        messagesSeen.addAll(messages);
        // Return messages
        if (messages.size() == 0) {
            messages.add(new Message("No new messages", "UnderARock", "You"));
        }
        return messages;
    }
    public Message getMessageForSequence(String seq) throws IOException, ParseException {
        final ArrayList<Message> messages = getMessages();
        Message message = null;
        for (Message m : messages) {
            if (m.getSeqId().equals(seq)) {
                message = m;
            }
        }
        return message;
    }
    public ArrayList<Message> getMessagesFromFriend(Id friendId) throws IOException, ParseException {
        final ArrayList<Message> messages = getMessages();
        final ArrayList<Message> MessagesFromFriend = new ArrayList<>();
        for (Message m : messages) {
            if (m.getFromId().equals(friendId.getGithub())) {
                MessagesFromFriend.add(m);
            }
        }
        return MessagesFromFriend;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) throws IOException, ParseException {
        final ArrayList<Message> messages = getMessages();
        final ArrayList<Message> MessagesFromFriend = new ArrayList<>();
        for (Message m : messages) {
            if (m.getToId().equals(myId.getGithub()) && m.getFromId().equals(friendId.getGithub())) {
                MessagesFromFriend.add(m);
            }
        }
        return MessagesFromFriend;
    } */
    public ReflectModel postReflect(String[] answers) {
        // Finals
        final URL urlReflections;
        final HttpURLConnection conn;

        // Get date
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String date2 = dateFormat.format(date);
        
        // Create Json
        JSONObject reflect = new JSONObject();
        JSONArray array = new JSONArray();
        for (String s : answers) {
            array.add(s);
        };
        reflect.put("date", date2);
        reflect.put("answers", array);
        // Post Message
        System.err.println("This is the json: " + reflect.toString());
        try {
            // Create Connection
            urlReflections = new URL(rootURL + "/reflect/create");
            conn = (HttpURLConnection) urlReflections.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            // Write JSon
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(reflect.toString());
            }
            // Read return Json
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Post failed");
            throw new RuntimeException(e);
        }
        conn.disconnect();
        return new ReflectModel(date2, answers);
    }
}
