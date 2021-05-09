package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Medication;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MedicationPlanDTO extends RepresentationModel<MedicationPlanDTO> {

    private UUID id;
    private Date startDate;
    private Date endDate;
    private UUID medication_id;
    private UUID patient_id;

    public MedicationPlanDTO(){}

    public MedicationPlanDTO(UUID id,Date startDate,Date endDate,UUID medication_id,UUID patient_id) {
        this.id = id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.medication_id=medication_id;
        this.patient_id=patient_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public UUID getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(UUID medication_id) {
        this.medication_id = medication_id;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }
}
