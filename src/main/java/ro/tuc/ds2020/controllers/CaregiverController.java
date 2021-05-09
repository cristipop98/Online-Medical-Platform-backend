package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.CaregiverService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService){
        this.caregiverService=caregiverService;

    }
    /*
    @GetMapping()
    public List<CaregiverDTO> getCaregivers() {
        return caregiverService.findCaregivers();


    }
    @PostMapping("/add")
    public Integer insertCaregiver(@RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.insert(caregiverDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCaregiver(@PathVariable Integer id)
    {
        caregiverService.delete(id);
    }

    @PutMapping("/{id}")
    public Integer updateCaregiver(@RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.update(caregiverDTO);
    }
    */







    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link personLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID patientID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id){
        caregiverService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);

    }

    @PutMapping(value="/{id}")
    public @ResponseBody ResponseEntity<Caregiver> updatePatient(@PathVariable("id") UUID id,@RequestBody CaregiverDTO caregiverDTO){


        Caregiver caregiver=caregiverService.findByID(id);
        caregiver.setId(id);
        caregiver.setName(caregiverDTO.getName());
        caregiver.setBirthDate(caregiverDTO.getBirthDate());
        caregiver.setGender(caregiverDTO.getGender());
        caregiver.setAddress(caregiverDTO.getAddress());

        caregiverService.save(caregiver);

        return new ResponseEntity<>(caregiver, HttpStatus.OK);
    }



}
