package ro.tuc.ds2020.entities;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="activitytable")
public class ActivityTable implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer activityId;

    @Column(name = "patient_id", nullable = false)
    private int id;

    @Column(name="startTime",nullable=false)
    private long startTime;

    @Column(name="endTime",nullable=false)
    private long endTime;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="anomaly",nullable=false)
    private boolean anomaly;


    public ActivityTable(){
    }

    public ActivityTable(Integer activityId,int id, long startTime, long endTime, String name, boolean anomaly) {
        this.activityId=activityId;
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.anomaly = anomaly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAnomaly() {
        return anomaly;
    }

    public void setAnomaly(boolean anomaly) {
        this.anomaly = anomaly;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
