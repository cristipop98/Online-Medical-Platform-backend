package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="utilizator")
public class Utilizator implements Serializable{


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name = "userType", nullable = false)
    private String userType;

    /*
    @OneToOne(mappedBy = "utilizator", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Doctor doctor;

    @OneToOne(mappedBy = "utilizator",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Caregiver caregiver;

    @OneToOne(mappedBy = "utilizator", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Patient patient;
    */




    public Utilizator(){}

    public Utilizator(String username, String password,String userType) {
        this.username = username;
        this.password = password;
        this.userType=userType;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    /*
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

     */
}
