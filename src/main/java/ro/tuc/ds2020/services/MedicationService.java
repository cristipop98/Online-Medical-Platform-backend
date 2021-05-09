package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository){this.medicationRepository=medicationRepository;}

    public List<MedicationDTO> findMedications() {
        List<Medication> personList = medicationRepository.findAll();
        return personList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationDTO medicationDTO) {
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication = medicationRepository.save(medication);
        LOGGER.debug("Caregiver with id {} was inserted in db", medication.getId());
        return medication.getId();
    }

    public UUID delete(UUID id)
    {
        // Patient patient = PatientBuilder.toEntity(id);
        medicationRepository.deleteById(id);
        return id;

    }

    public void save(Medication medication){
        medicationRepository.save(medication);
    }
    public Medication findByID(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public UUID update(MedicationDTO medicationDTO){
        Medication medication=MedicationBuilder.toEntity(medicationDTO);
        return medicationRepository.save(medication).getId();
    }
}
