package ro.tuc.ds2020;

import org.apache.catalina.authenticator.jaspic.SimpleServerAuthConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.validation.annotation.Validated;
import ro.tuc.ds2020.entities.MedicationPlanRPC;
import ro.tuc.ds2020.repositories.MedicationPlanRPCRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.server.MedPlan;
import ro.tuc.ds2020.server.MedPlanImpl;
import ro.tuc.ds2020.services.MedicationPlanRPCService;
import ro.tuc.ds2020.services.MedicationPlanService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@Validated
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Ds2020Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return application.sources(Ds2020Application.class);
    }

     private MedicationPlanRepository medicationPlanRepository;
    private MedicationRepository medicationRepository;
    private PatientRepository patientRepository;
    @Bean
    MedPlan bookMedication(MedicationPlanRepository medicationPlanRepository,MedicationRepository medicationRepository,PatientRepository patientRepository)
    {
        this.medicationPlanRepository=medicationPlanRepository;
        this.medicationRepository=medicationRepository;
        this.patientRepository=patientRepository;
        return new MedPlanImpl(medicationPlanRepository,medicationRepository,patientRepository);
    }
    /*
    @Bean()
    RmiServiceExporter exporter(MedPlan implementation) throws UnknownHostException {
        Class<MedPlan> serviceInterface=MedPlan.class;
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName("MedicationPlan");
        exporter.setRegistryPort(1099);
        //exporter.setRegistryHost("0.0.0.0");
        System.out.println("Server is running");
        return exporter;
    }
    */
    @Bean(name = "/medicationPlanRPC-test")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        //Class<MedPlan> interf=MedPlan.class;
        exporter.setService(bookMedication(medicationPlanRepository,medicationRepository,patientRepository));
        exporter.setServiceInterface(MedPlan.class);
        return exporter;
    }


    public static void main(String[] args){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Ds2020Application.class, args).getBean(MedPlan.class);
        System.out.println("Server is running");


    }
}
