package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService){this.medicationPlanService=medicationPlanService;}

    @GetMapping()
    public ResponseEntity<List<MedicationPlanDTO>> getMedications() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlans();
        for (MedicationPlanDTO dto : dtos) {
            Link personLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getId())).withRel("medicationDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationPlanDTO medicationDTO) {
        UUID patientID = medicationPlanService.insert(medicationDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanDTO> getMedicationPlan(@PathVariable("id") UUID caregiverId) {
        MedicationPlanDTO dto = medicationPlanService.findMedicationPlanById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id){
        medicationPlanService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);

    }
    /*

    @PutMapping(value="/{id}")
    public @ResponseBody ResponseEntity<MedicationPlan> updateMedication(@PathVariable("id") UUID id, @RequestBody MedicationPlanDTO medicationDTO){


        MedicationPlan medication=medicationPlanService.findByID(id);
        medication.setId(id);
        medication.setName(medicationDTO.getName());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage(medicationDTO.getDosage());

        medicationService.save(medication);

        return new ResponseEntity<>(medication, HttpStatus.OK);
    }
    */
}