package ro.tuc.ds2020.dtos.builders;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.MedicationPlanRPCDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.MedicationPlanRPC;

public class MedicationPlanRPCBuilder {

    private MedicationPlanRPCBuilder(){

    }

    public static MedicationPlanRPCDTO toMedicationPlanRPCDTO(MedicationPlanRPC medicationPlanrpc) {

        return new MedicationPlanRPCDTO(medicationPlanrpc.getId(), medicationPlanrpc.getMedicationList(), medicationPlanrpc.getIntake(), medicationPlanrpc.getDuration());


    }
    public static MedicationPlanRPC toEntity(MedicationPlanRPCDTO caregiverDTO) {
        return new MedicationPlanRPC(
                caregiverDTO.getMedicationList(),
                caregiverDTO.getIntake(),
                caregiverDTO.getDuration());
    }
}