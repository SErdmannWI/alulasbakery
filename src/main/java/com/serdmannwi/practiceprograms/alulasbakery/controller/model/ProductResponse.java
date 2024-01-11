package com.serdmannwi.practiceprograms.alulasbakery.controller.model;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {
    @JsonProperty("productName")
    private String name;
    @JsonProperty("productId")
    private String id;
   @JsonProperty("timeToComplete")
    private int timeForJob;
   @JsonProperty("isFinished")
   private boolean isFinished;

    public ProductResponse() {}

    public ProductResponse(String name, String id, int timeForJob) {
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

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
