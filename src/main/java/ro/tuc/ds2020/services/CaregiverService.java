package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
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
public class CaregiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;
    //private final UtilizatorRepository utilizatorRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository){
        this.caregiverRepository=caregiverRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> personList = caregiverRepository.findAll();
        return personList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public UUID insert(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
       // Optional<Utilizator> user=utilizatorRepository.findById(caregiverDTO.getUtilizator_id());
        //caregiver.setUtilizator(user.get());
        //user.get().setCaregiver(caregiver);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID delete(UUID id)
    {
        // Patient patient = PatientBuilder.toEntity(id);
        caregiverRepository.deleteById(id);

        return id;

    }

    public void save(Caregiver patient){
        caregiverRepository.save(patient);
    }
    public Caregiver findByID(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public UUID update(CaregiverDTO caregiverDTO){
        Optional<Caregiver> caregiver=caregiverRepository.findById(caregiverDTO.getId());
        if(!caregiver.isPresent()){
            LOGGER.error("Caregiver with id {} was not found in db", caregiverDTO.getId());
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + caregiverDTO.getId());
        }
      //  Utilizator user=caregiver.get().getUtilizator();
        Caregiver caregiver1=CaregiverBuilder.toEntity(caregiverDTO);
       // caregiver1.setUtilizator(user);
        return caregiverRepository.save(caregiver1).getId();

    }

}
