package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class UtilizatorDTO extends RepresentationModel<UtilizatorDTO>{


    private UUID id;
    private String username;
    private String password;
    private String userType;


    public UtilizatorDTO(){};

    public UtilizatorDTO(UUID id,String username, String password,String userType) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.userType=userType;
    }

    public UtilizatorDTO(String username, String password, String userType) {
        this.username=username;
        this.password=password;
        this.userType=userType;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}


