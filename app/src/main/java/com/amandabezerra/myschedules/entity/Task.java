package com.amandabezerra.myschedules.entity;

public class Task {

    private String title;
    private String description;
    private String completed;
    private String deadline;
    private final static String userid = "@string/userid";

    public Task(String title, String description,
                String completed, String deadline) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.deadline = deadline;
    }

    public Task() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public static String getUserid() {
        return userid;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
