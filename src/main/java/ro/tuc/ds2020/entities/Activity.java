package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public  class Activity {
    private int patient_id;
    private long startTime;
    private long endTime;
    private String name;

    public Activity(int patient_id,long startTime, long endTime, String name) {
        this.patient_id=patient_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}