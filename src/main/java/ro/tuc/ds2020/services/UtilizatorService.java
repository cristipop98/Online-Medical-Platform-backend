package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.UtilizatorDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.dtos.builders.UtilizatorBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Utilizator;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.repositories.UtilizatorRepository;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


    @Service
    public class UtilizatorService {

        private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
        private final UtilizatorRepository utilizatorRepository;

        @Autowired
        public UtilizatorService(UtilizatorRepository utilizatorRepository) {
            this.utilizatorRepository = utilizatorRepository;
        }

        public List<UtilizatorDTO> findUtilizatori() {
            List<Utilizator> personList = utilizatorRepository.findAll();
            return personList.stream()
                    .map(UtilizatorBuilder::toUtilizatorDTO)
                    .collect(Collectors.toList());
        }

        public String findUserByEmailAndPassword(String username, String password) {
          //  Optional<Utilizator> user = utilizatorRepository.findUserByUsernameAndPassword(username, password);
           // if (!user.isPresent()) {
             //   return new UtilizatorDTO();
            //}

           // return new UtilizatorDTO(user.get().getId(), user.get().getUsername(), user.get().getPassword(), user.get().getUserType());
            List<Utilizator> personList = utilizatorRepository.findAll();
            String role = null;
            for(Utilizator u : personList)
            {
                if(u.getUsername().equals(username) && u.getPassword().equals(password))
                {
                    role = u.getUserType();
                }
            }

            return role;

        }


        public UtilizatorDTO findUtilizatorByID(UUID id) {
            Optional<Utilizator> prosumerOptional = utilizatorRepository.findById(id);
            if (!prosumerOptional.isPresent()) {
                LOGGER.error("Caregiver with id {} was not found in db", id);
                throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
            }
            return UtilizatorBuilder.toUtilizatorDTO(prosumerOptional.get());
        }
    }
        /*

        public List<PatientDTO> findAllPatients(UUID id) {
            Optional<Caregiver> caregiver = utilizatorRepository.findCaregiver(id);
            List<Patient> patients = utilizatorRepository.findPatients(caregiver.get().getId());

            return patients.stream()
                    .map(PatientBuilder::toPatientDTO)
                    .collect(Collectors.toList());
        }
    }


        public UtilizatorDTO findPatientById(UUID id) {
            Optional<Utilizator> prosumerOptional = utilizatorRepository.findById(id);
            if (!prosumerOptional.isPresent()) {
                LOGGER.error("utilizator with id {} was not found in db", id);
                throw new ResourceNotFoundException(Utilizator.class.getSimpleName() + " with id: " + id);
            }
            return UtilizatorBuilder.toPatientDTO(prosumerOptional.get());
        }
       */




