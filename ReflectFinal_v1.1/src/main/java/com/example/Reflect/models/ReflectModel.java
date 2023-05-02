package com.example.Reflect.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReflectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String date;
    private String[] answers = new String[10];

    public ReflectModel() {
    }

    public ReflectModel(String date, String[] answers) {
        this(null, date, answers);
    }

    public ReflectModel(Long id, String date, String[] answers) {
        this.id = id;
        this.date = date;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectModel reflect = (ReflectModel) o;
        return Objects.equals(id, reflect.id) &&
                Objects.equals(date, reflect.date) &&
                Objects.equals(answers, reflect.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, answers);
    }

    @Override
    public String toString() {
        String output = "";
        output += "ReflectModel [id=" + id + ", date=" + date + ", answers=";
        for (String s : answers) {
            output += s + ",";
        }
        return output.substring(0, output.length() - 1) + "]";
    }

}
