package ro.tuc.ds2020.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.entities.ActivityTable;
import ro.tuc.ds2020.repositories.ActivityRepository;

import java.io.IOException;
import java.util.Map;

@Component
 class Consumer implements MessageListener {

    @Autowired
    private AmqpTemplate amqpTemplate;
    ActivityRepository activityRepository;
    int i=0;
    public Consumer(ActivityRepository activityRepository){
        this.activityRepository=activityRepository;
    }

    @Override

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void onMessage(Message message){
        String a=new String(message.getBody());
        System.out.println("Recieved Message:"+a);
        JSONObject obj=new JSONObject();
        Gson gson=new Gson();
        Activity activ=gson.fromJson(a,Activity.class);
        ActivityTable activityTable=new ActivityTable();
        activityTable.setActivityId(i);
        activityTable.setId(activ.getPatient_id());
        activityTable.setStartTime(activ.getStartTime());
        activityTable.setEndTime(activ.getEndTime());
        activityTable.setName(activ.getName());
        if(activ.getEndTime()-activ.getStartTime()>25200000 && activ.getName().equals("Sleeping")){
            activityTable.setAnomaly(true);
        }
        else if(activ.getEndTime()-activ.getStartTime()>18000000 && activ.getName().equals("Leaving"))
        {
            activityTable.setAnomaly(true);
        }
        else if(activ.getEndTime()-activ.getStartTime()>1800000 && activ.getName().equals("Toileting"))
        {
            activityTable.setAnomaly(true);
        }
        else if(activ.getEndTime()-activ.getStartTime()>1800000 && activ.getName().equals("Grooming"))
        {
            activityTable.setAnomaly(true);
        }
        else if(activ.getEndTime()-activ.getStartTime()>1800000 && activ.getName().equals("Showering"))
        {
            activityTable.setAnomaly(true);
        }
        else{
            activityTable.setAnomaly(false);
        }
        activityRepository.save(activityTable);
        i++;


       // System.out.println("Patient_id:"+activ.getPatient_id());
       // System.out.println("StartTime:"+activ.getStartTime());
       // System.out.println("EndTime:"+activ.getEndTime());
       // System.out.println("Name:"+activ.getName());
        // String []str=a.split(",");
        //for(String i:str) {
          //  System.out.println(i);
            /*
           String[]str1=i.split(":");
           for(String j:str1) {
               String[]str2=j.split("patient_id");
               for(String k:str2) {
                   System.out.println(k);
               }
           }
           */

        }
        /*
        //byte[] a=(message.getBody()).toString().getBytes();
        ObjectMapper mapper=new ObjectMapper();
        try {
            Activity activ=mapper.readValue(a,Activity.class);
            System.out.println("Patient_id:"+activ.getPatient_id()+ " StartTime:"+activ.getStartTime()+ " EndTime:"+activ.getEndTime()+ " Name:"+activ.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //amqpTemplate.receiveAndConvert();
       //message.getMessageProperties();
        //String a=message.toString();
       // System.out.println(a);
        /*
        ObjectMapper mapper=new ObjectMapper();
        try {
            Map<String,Activity> map= (Map<String, Activity>) mapper.readValue(a,Activity.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
         String []str=a.split(",");
         for(String i:str)
             System.out.println(i);
    */
        //System.out.println("Recieved Message:"+a);


}

