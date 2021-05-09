package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;

    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, MedicationRepository medicationRepository, PatientRepository patientRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationRepository=medicationRepository;
        this.patientRepository=patientRepository;
    }

    public List<MedicationPlanDTO> findMedicationPlans() {
        List<MedicationPlan> personList = medicationPlanRepository.findAll();
        return personList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationPlanDTO medicationDTO) {
        MedicationPlan medication = MedicationPlanBuilder.toEntity(medicationDTO);
        Optional<Medication> med=medicationRepository.findById(medicationDTO.getMedication_id());
        Optional<Patient> patient=patientRepository.findById(medicationDTO.getPatient_id());


        medication.setMedication(med.get());
        medication.setPatient(patient.get());

        medication = medicationPlanRepository.save(medication);

        LOGGER.debug("Caregiver with id {} was inserted in db", medication.getId());
        LOGGER.debug("Patient with id {} was added to MedicationPlan", medication.getPatient());

        return medication.getId();
    }

    public UUID delete(UUID id) {
        // Patient patient = PatientBuilder.toEntity(id);
        medicationPlanRepository.deleteById(id);
        return id;

    }

    public void save(MedicationPlan medication) {
        medicationPlanRepository.save(medication);
    }

    public MedicationPlan findByID(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }
}