package ro.tuc.ds2020.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.MedicationPlanException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanRPCDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.dtos.builders.MedicationPlanRPCBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.MedicationPlanRPC;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.MedicationPlanRPCRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.MedicationPlanRPCService;
import ro.tuc.ds2020.services.MedicationPlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MedPlanImpl implements MedPlan{

     public MedicationPlanRepository medicationPlanRepository;
     public MedicationRepository medicationRepository;
     public PatientRepository patientRepository;

    public MedPlanImpl(MedicationPlanRepository medicationPlanRepository,MedicationRepository medicationRepository,PatientRepository patientRepository){
     this.medicationPlanRepository=medicationPlanRepository;
     this.medicationRepository=medicationRepository;
     this.patientRepository=patientRepository;
     }


    @Override
    public List<MedicationPlan> medPlan(){
         MedicationPlanService medicationPlanService= new MedicationPlanService(medicationPlanRepository,medicationRepository,patientRepository);
         List<MedicationPlanDTO> plan=medicationPlanService.findMedicationPlans();
        List<MedicationPlan> lista=new ArrayList<>();
        MedicationPlan ceva=new MedicationPlan();



        //if(ceva.getPeriod()<5)
        //  throw new MedicationPlanException("Prea mica durata");


         for(MedicationPlanDTO i:plan)
        {
          MedicationPlan rpc1= MedicationPlanBuilder.toEntity(i);
         rpc1.setId(i.getId());
         Optional<Medication> medication=medicationRepository.findById(i.getMedication_id());
         Optional<Patient> patient=patientRepository.findById(i.getPatient_id());
         rpc1.setMedication(medication.get());
         rpc1.setPatient(patient.get());
        lista.add(rpc1);
       // System.out.println(rpc1.toString());
        }
        return lista;
    }


}

