package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Patient;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDTO extends UtilizatorDTO{

    private UUID id;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;

    public CaregiverDTO(){

    }

    public CaregiverDTO(UUID id, String name, Date birthDate, String gender, String address,String username,String password,String userType) {
        super(username,password,userType);
        this.id = id;
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

}
