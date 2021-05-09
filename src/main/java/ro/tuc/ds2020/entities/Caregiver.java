package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="caregiver")
public class Caregiver extends Utilizator {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="birthDate",nullable=true)
    private Date birthDate;

    @Column(name="gender",nullable=true)
    private String gender;

    @Column(name="address",nullable=true)
    private String address;

  //  @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
  //  @JoinColumn(name="utilizator_id",nullable=false)
  //  private Utilizator utilizator;


    //@Column(name="pacientList",nullable=false)
    //@OneToMany(mappedBy="caregiver")
   // @JoinColumn(name="patient_id")
    //private List<Patient> PatientList;


    public Caregiver(){}

    public Caregiver(String name, Date birthDate, String gender, String address,String username,String password,String userType) {
        super(username,password,userType);
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /*
    public List<Patient> getPatientList() {
        return PatientList;
    }

    public void setPatientList(List<Patient> patientList) {
        PatientList = patientList;
    }
    */
}
