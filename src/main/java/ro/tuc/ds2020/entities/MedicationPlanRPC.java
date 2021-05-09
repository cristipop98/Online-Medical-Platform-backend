package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="medicationPlanRPC")
public class MedicationPlanRPC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;


    @Column(name="MedicationList",nullable=false)
    private String medicationList;

    @Column(name="intake",nullable=false)
    private String intake;

    @Column(name="duration",nullable=false)
    private int duration;

    public MedicationPlanRPC(){
    }

    public MedicationPlanRPC(String medicationList,String intake,int duration){
        this.medicationList=medicationList;
        this.intake=intake;
        this.duration=duration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(String medicationList) {
        this.medicationList = medicationList;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MedicationPlanRPC{" +
                "id=" + id +
                ", medicationList='" + medicationList + '\'' +
                ", intake='" + intake + '\'' +
                ", duration=" + duration +
                '}';
    }
}

