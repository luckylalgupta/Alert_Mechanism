package com.alert.mechanism.controller;

import com.alert.mechanism.entity.Developer;
import com.alert.mechanism.entity.Team;
import com.alert.mechanism.exception.DeveloperEmptyException;
import com.alert.mechanism.exception.DeveloperNameException;
import com.alert.mechanism.exception.DeveloperPhoneNumberException;
import com.alert.mechanism.service.DeveloperService;
import com.alert.mechanism.service.TeamService;
import com.alert.mechanism.utility.TeamTemplate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    private TeamTemplate saveTeam(@RequestBody TeamTemplate teamTemplate){
        return teamService.saveTeam(teamTemplate);
    }

    @GetMapping("/team/{teamId}")
    private Long assignTask(@PathVariable("teamId") Long teamId) throws JSONException {
       return teamService.assignTask(teamId);
    }
    @PostMapping("/developer")
    private Developer saveDeveloper(@RequestBody Developer developer){
        if(null == developer){
            throw new DeveloperEmptyException("Developer information requires");
        }
        if(null == developer.getName()){
            throw new DeveloperNameException("Developer name should not be empty");
        }
        if(null == developer.getPhoneNumber()){
            throw new DeveloperPhoneNumberException("Developer phone number should not empty");
        }
        return developerService.saveDeveloper(developer);
    }
    @GetMapping("/developer/{id}")
    private Developer getDeveloper(@PathVariable("id") Long id){
        return developerService.getDeveloperById(id);
    }
}
