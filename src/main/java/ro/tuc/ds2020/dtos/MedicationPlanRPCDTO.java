package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Medication;

import java.util.List;
import java.util.UUID;

public class MedicationPlanRPCDTO extends RepresentationModel<MedicationPlanRPCDTO> {
    private UUID id;
    private String medicationList;
    private String intake;
    private int duration;

    public MedicationPlanRPCDTO(){}

    public MedicationPlanRPCDTO(UUID id, String medicationList, String intake, int duration) {
        this.id = id;
        this.medicationList = medicationList;
        this.intake = intake;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(String medicationList) {
        this.medicationList = medicationList;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
