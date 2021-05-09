package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Patient;

public class PatientBuilder {

    private PatientBuilder(){}

    public static PatientDTO toPatientDTO(Patient patient){
        return new PatientDTO(patient.getId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord(),
                patient.getCaregiver().getId(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getUserType());
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.getName(),
                patientDTO.getBirthDate(),
                patientDTO.getGender(),
                patientDTO.getAddress(),
                patientDTO.getMedicalRecord(),
                patientDTO.getUsername(),
                patientDTO.getPassword(),
                patientDTO.getUserType());
    }

}
