package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;
   // private final UtilizatorRepository utilizatorRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,CaregiverRepository caregiverRepository){
        this.patientRepository=patientRepository;
        this.caregiverRepository=caregiverRepository;
        }

    public List<PatientDTO> findPatients() {
        List<Patient> personList = patientRepository.findAll();
        return personList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public UUID insert(PatientDTO personDTO) {
        Patient patient = PatientBuilder.toEntity(personDTO);
       // Optional<Utilizator> user=utilizatorRepository.findById(personDTO.getUser_id());
        Optional<Caregiver> caregiver=caregiverRepository.findById(personDTO.getCaregiver_id());
       // patient.setUtilizator(user.get());
       // user.get().setPatient(patient);
        patient.setCaregiver(caregiver.get());
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();

        //return patientRepository.save(patient).getId();
    }

    public UUID delete(UUID id)
    {
       // Patient patient = PatientBuilder.toEntity(id);
        patientRepository.deleteById(id);

        return id;

    }

    public void save(Patient patient){
        patientRepository.save(patient);
    }
    public Patient findByID(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }
    public UUID update(PatientDTO patientDTO){
        Optional<Patient> patient=patientRepository.findById(patientDTO.getId());
        if(!patient.isPresent()){
            LOGGER.error("Patient with id {} was not found in db", patientDTO.getId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientDTO.getId());
        }

        //Utilizator user=patient.get().getUtilizator();
        Caregiver caregiver=patient.get().getCaregiver();
        Patient p1=PatientBuilder.toEntity(patientDTO);
       // p1.setUtilizator(user);
        p1.setCaregiver(caregiver);

        return patientRepository.save(p1).getId();
    }
    /*
     public UUID update(PatientDTO patientDTO){
        Patient patient=PatientBuilder.toEntity(patientDTO);
        patient=patientRepository.save(patient);

        return patient.getId();
    }

     public void delete(PatientDTO patientDTO){
        Patient patient=PatientBuilder.toEntity(patientDTO);
        patientRepository.delete(patient);
    }

    public boolean delete(UUID id){
        boolean t=false;
       List<Patient> patientList=patientRepository.findAll();
       for(Patient a:patientList){
           if(a.getId()==id)
           {
               patientList.remove(a);
               t=true;
           }
       }
       return t;

    }

    public List<Patient> delete(UUID id) {
        List<Patient> personList = patientRepository.findAll();
        for(Patient a:personList){
            if(a.getId().equals(id)){
                personList.remove(a);
            }

        }
        return personList;
    }
    */

}
