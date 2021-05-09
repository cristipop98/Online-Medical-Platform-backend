package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanRPCDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.dtos.builders.MedicationPlanRPCBuilder;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.MedicationPlanRPC;
import ro.tuc.ds2020.repositories.MedicationPlanRPCRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanRPCService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRPCRepository medicationPlanRPCRepository;

    public MedicationPlanRPCService(MedicationPlanRPCRepository medicationPlanRPCRepository) {
        this.medicationPlanRPCRepository = medicationPlanRPCRepository;
    }

    public List<MedicationPlanRPCDTO> findMedicationPlans() {
        List<MedicationPlanRPC> personList = medicationPlanRPCRepository.findAll();

        return personList.stream()
                .map(MedicationPlanRPCBuilder::toMedicationPlanRPCDTO)
                .collect(Collectors.toList());

    }

    public MedicationPlanRPCDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlanRPC> prosumerOptional = medicationPlanRPCRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlanRPC.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanRPCBuilder.toMedicationPlanRPCDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationPlanRPCDTO medicationDTO) {
        MedicationPlanRPC medication = MedicationPlanRPCBuilder.toEntity(medicationDTO);
        medication = medicationPlanRPCRepository.save(medication);
        LOGGER.debug("Caregiver with id {} was inserted in db", medication.getId());
        return medication.getId();
    }

    public UUID delete(UUID id) {
        // Patient patient = PatientBuilder.toEntity(id);
        medicationPlanRPCRepository.deleteById(id);
        return id;

    }

    public void save(MedicationPlanRPC medication) {
        medicationPlanRPCRepository.save(medication);
    }

    public MedicationPlanRPC findByID(UUID id) {
        Optional<MedicationPlanRPC> prosumerOptional = medicationPlanRPCRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlanRPC.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }
}