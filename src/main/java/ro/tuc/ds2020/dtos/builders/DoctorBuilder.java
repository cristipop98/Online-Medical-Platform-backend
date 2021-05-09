package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.Doctor;

public class DoctorBuilder {


    private DoctorBuilder() {
    }


        public static DoctorDTO toDoctorDTO(Doctor doctor){

            return new DoctorDTO(doctor.getId(),doctor.getName(),doctor.getUsername(),doctor.getPassword(),doctor.getUserType());

        }

        public static Doctor toEntity(DoctorDTO doctorDTO){
            return new Doctor(doctorDTO.getName(), doctorDTO.getUsername(),doctorDTO.getPassword(),doctorDTO.getUserType());
    }

}


