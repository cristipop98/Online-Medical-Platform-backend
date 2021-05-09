package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.entities.MedicationPlan;

public class MedicationPlanBuilder {

    private MedicationPlanBuilder(){}

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {

        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getStartDate(),
                medicationPlan.getEndDate(),
                medicationPlan.getMedication().getId(),
                medicationPlan.getPatient().getId());


    }

    public static MedicationPlan toEntity(MedicationPlanDTO caregiverDTO) {
        return new MedicationPlan(
                caregiverDTO.getStartDate(),
                caregiverDTO.getEndDate());
    }
}
