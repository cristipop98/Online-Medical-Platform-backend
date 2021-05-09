package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class DoctorDTO extends UtilizatorDTO {

    private UUID id;
    private String name;

    public DoctorDTO(){}

    public DoctorDTO(UUID id, String name,String username,String password,String userRole) {
        super(username,password,userRole);
        this.id = id;
        this.name=name;

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
}
