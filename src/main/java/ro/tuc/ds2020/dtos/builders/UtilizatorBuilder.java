package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UtilizatorDTO;
import ro.tuc.ds2020.entities.Utilizator;

public class UtilizatorBuilder {

    private UtilizatorBuilder(){}

    public static UtilizatorDTO toUtilizatorDTO(Utilizator user){
        return new UtilizatorDTO(user.getId(),user.getUsername(),user.getPassword(),user.getUserType());

    }

    public static Utilizator toEntity(UtilizatorDTO userDTO) {
        return new Utilizator(userDTO.getUsername(),
                userDTO.getPassword(),userDTO.getUserType());
    }

}
