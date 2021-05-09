package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.entities.ActivityTable;
import ro.tuc.ds2020.repositories.ActivityRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/activity")
public class ActivityTableController {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityTableController(ActivityRepository activityRepository){
        this.activityRepository=activityRepository;
    }

    @GetMapping()
    public List<ActivityTable> listaActivitati(){
        return activityRepository.findAll();
    }
}
