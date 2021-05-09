package ro.tuc.ds2020.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanRPCDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.services.MedicationPlanRPCService;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlanRPC")
public class MedicationPlanRPCController {

    private final MedicationPlanRPCService medicationPlanRPCService;

    @Autowired
    public MedicationPlanRPCController(MedicationPlanRPCService medicationPlanRPCService) {
        this.medicationPlanRPCService = medicationPlanRPCService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationPlanRPCDTO>> getMedications() {
        List<MedicationPlanRPCDTO> dtos = medicationPlanRPCService.findMedicationPlans();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationPlanRPCDTO medicationDTO) {
        UUID patientID = medicationPlanRPCService.insert(medicationDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanRPCDTO> getMedicationPlan(@PathVariable("id") UUID caregiverId) {
        MedicationPlanRPCDTO dto = medicationPlanRPCService.findMedicationPlanById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        medicationPlanRPCService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}