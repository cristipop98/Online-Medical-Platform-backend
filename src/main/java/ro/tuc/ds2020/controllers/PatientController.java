package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.CaregiverService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;
    private final CaregiverService caregiverService;
    @Autowired
    public PatientController(PatientService patientService,CaregiverService caregiverService){
        this.patientService=patientService;
        this.caregiverService=caregiverService;

    }
    /*
    @GetMapping()
    public List<PatientDTO> getPatients() {

        return patientService.findPatients();

    }
    @PostMapping("/add")
    public Integer insertPatient(@RequestBody PatientDTO patientDTO){
        return patientService.insert(patientDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id)
    {
        patientService.delete(id);
    }

    @PutMapping("/{id}")
    public Integer updatePatient(@RequestBody PatientDTO patientDTO){
        return patientService.update(patientDTO);
    }
    */


    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID personId) {
        PatientDTO dto = patientService.findPatientById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id){
        patientService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);

    }

    @PutMapping(value="/{id}")
    public @ResponseBody ResponseEntity<Patient> updatePatient(@PathVariable("id") UUID id,@RequestBody PatientDTO patientDTO){


        Patient patient=patientService.findByID(id);
        patient.setId(id);
        patient.setName(patientDTO.getName());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setGender(patientDTO.getGender());
        patient.setAddress(patientDTO.getAddress());
        patient.setMedicalRecord(patientDTO.getMedicalRecord());

       patientService.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    /*
    @PutMapping(value="/{id}")
    public @ResponseBody ResponseEntity<PatientDTO> updatePatient(@PathVariable("id") UUID id,@RequestParam String name,
                                                                  @RequestParam String birthDate,@RequestParam String gender,
                                                                  @RequestParam String address,@RequestParam String medicalRecord){

        Optional<PatientDTO> dto = Optional.ofNullable(patientService.findPatientById(id));

        if(!dto.isPresent())
            return ResponseEntity.notFound().build();




        return ResponseEntity.noContent().build();




    }
    */

}
