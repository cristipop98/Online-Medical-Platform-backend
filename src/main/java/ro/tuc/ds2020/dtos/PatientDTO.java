package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Caregiver;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDTO extends UtilizatorDTO {

    private UUID id;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String medicalRecord;
    //private Caregiver caregiver;
    private UUID caregiver_id;

    public PatientDTO(){

    }
    public PatientDTO(UUID id,String name,Date birthDate,String gender,String address,String medicalRecord,UUID caregiver_id,String username,String password,String userType){
        super(username,password,userType);
        this.id=id;
        this.name=name;
        this.birthDate=birthDate;
        this.gender=gender;
        this.address=address;
        this.medicalRecord=medicalRecord;
        this.caregiver_id=caregiver_id;
        //this.caregiver=caregiver;
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public UUID getCaregiver_id() {
        return caregiver_id;
    }

    public void setCaregiver_id(UUID caregiver_id) {
        this.caregiver_id = caregiver_id;
    }
}
