package com.blowder.jsf.table;

public class User {
    private String firstName;
    private String secondName;
    private String job;

    public User(String firstName, String secondName, String job) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.job = job;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}