package com.alert.mechanism.service;

import com.alert.mechanism.entity.Developer;
import com.alert.mechanism.entity.Team;
import com.alert.mechanism.repository.DeveloperRepository;
import com.alert.mechanism.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer saveDeveloper(Developer developer){
        if(null ==developer.getStatus()){
            developer.setStatus(Status.NOT_ENGAGED);
        }
        return developerRepository.save(developer);
    }

    public Developer getDeveloperById(Long id){
        return developerRepository.getOne(id);
    }

}
