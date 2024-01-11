package com.serdmannwi.practiceprograms.alulasbakery.service.model;

public class Product {
    private String name;
    private String id;
    private int timeForJob;
    private boolean isFinished;

    public Product() {}

    public Product(String name, String id, int timeForJob) {
        this.name = name;
        this.id = id;
        this.timeForJob = timeForJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimeForJob() {
        return timeForJob;
    }

    public void setTimeForJob(int timeForJob) {
        this.timeForJob = timeForJob;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
