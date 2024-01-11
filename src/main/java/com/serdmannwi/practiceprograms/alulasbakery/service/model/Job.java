package com.serdmannwi.practiceprograms.alulasbakery.service.model;

public class Job {
    private String jobId;
    private int timeToComplete;

    public Job() {}

    public Job(String jobId, int timeToComplete) {
        this.jobId = jobId;
        this.timeToComplete = timeToComplete;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }
}
