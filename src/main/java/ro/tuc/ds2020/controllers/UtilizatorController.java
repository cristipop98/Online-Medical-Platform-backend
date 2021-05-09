package ro.tuc.ds2020.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.UtilizatorDTO;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Utilizator;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.PatientService;
import ro.tuc.ds2020.services.UtilizatorService;
//import ro.tuc.ds2020.services.UtilizatorService;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/utilizator")
public class UtilizatorController {

    private final UtilizatorService utilizatorService;

    @Autowired
    public UtilizatorController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    //@PostMapping
    //public UtilizatorDTO findUsernameAndPassword(@RequestBody UtilizatorDTO utilizatorDTO) throws NoSuchAlgorithmException {
      //  return utilizatorService.findUserByEmailAndPassword(utilizatorDTO.getUsername(), utilizatorDTO.getPassword());
   // }


    @GetMapping()
    public ResponseEntity<List<UtilizatorDTO>> getUtilizatori() {
        List<UtilizatorDTO> dtos = utilizatorService.findUtilizatori();
       /* for (UtilizatorDTO dto : dtos) {
            Link personLink = linkTo(methodOn(UtilizatorController.class)
                    .getUtilizator(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }

        */
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{username}/{password}")
    public ResponseEntity<String> getUtilizator(@PathVariable("username") String username, @PathVariable("password") String password) {
        String dto = utilizatorService.findUserByEmailAndPassword(username,password);
        return new ResponseEntity<String>(dto, HttpStatus.OK);
    }


}
    /*
    @GetMapping()
    public ResponseEntity<List<UtilizatorDTO>> getUtilizatori() {
        List<UtilizatorDTO> dtos = utilizatorService.findUtilizatori();
        for (UtilizatorDTO dto : dtos) {
            Link personLink = linkTo(methodOn(UtilizatorController.class)
                    .getUtilizator(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody UtilizatorDTO utilizatorDTO) {
        UUID utilizatorID = utilizatorService.insert(utilizatorDTO);
        return new ResponseEntity<>(utilizatorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UtilizatorDTO> getUtilizator(@PathVariable("id") UUID utilizatorID) {
        UtilizatorDTO dto = utilizatorService.findPatientById(utilizatorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        utilizatorService.delete(id);

    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<Utilizator> updateUtilizator(@PathVariable("id") UUID id, @RequestBody UtilizatorDTO utilizatorDTO) {


        Utilizator utilizator = utilizatorService.findByID(id);
        utilizator.setId(id);

        utilizator.setUserType(utilizatorDTO.getUserType());
        utilizator.setUsername(utilizatorDTO.getUsername());
        utilizator.setPassword(utilizatorDTO.getPassword());

        utilizatorService.save(utilizator);

        return new ResponseEntity<>(utilizator, HttpStatus.OK);
    }

}

     */

