package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.services.MedicationService;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    /*
    @GetMapping()
    public List<MedicationDTO>getMedications() {
        return medicationService.findMedications();

    }

    @PostMapping("/add")
    public Integer insertMedication(@RequestBody MedicationDTO medicationDTO){
        return medicationService.insert(medicationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Integer id){
        medicationService.delete(id);
    }
    @PutMapping("/{id}")
    public Integer updateMedication(@RequestBody MedicationDTO medicationDTO){
        return medicationService.update(medicationDTO);
    }

    }

    */

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        for (MedicationDTO dto : dtos) {
            Link personLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID patientID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID caregiverId) {
        MedicationDTO dto = medicationService.findMedicationById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        medicationService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<Medication> updateMedication(@PathVariable("id") UUID id, @RequestBody MedicationDTO medicationDTO) {


        Medication medication = medicationService.findByID(id);
        medication.setId(id);
        medication.setName(medicationDTO.getName());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage(medicationDTO.getDosage());

        medicationService.save(medication);

        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

}
