package com.alert.mechanism.service;

import com.alert.mechanism.entity.Developer;
import com.alert.mechanism.entity.Team;
import com.alert.mechanism.exception.AlertFailedException;
import com.alert.mechanism.exception.DeveloperEmptyException;
import com.alert.mechanism.exception.DeveloperNameException;
import com.alert.mechanism.exception.DeveloperPhoneNumberException;
import com.alert.mechanism.repository.DeveloperRepository;
import com.alert.mechanism.repository.TeamRepository;
import com.alert.mechanism.utility.Status;
import com.alert.mechanism.utility.TeamTemplate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    RestTemplate restTemplate = new RestTemplate();


    public TeamTemplate saveTeam(TeamTemplate teamTemplate){

        List<Developer> savedDevelopers = new ArrayList<>();
        List<Developer> developers = teamTemplate.getDeveloperList();

        if(null == developers||developers.size()==0){
            throw new DeveloperEmptyException("At least one developer requires");
        }
        for(Developer developer :developers){
            if(null == developer.getStatus()){
                developer.setStatus(Status.NOT_ENGAGED);
            }
            if(null == developer.getName()){
                throw new DeveloperNameException("Developer name requires ");
            }
            if(null == developer.getPhoneNumber()){
                throw new DeveloperPhoneNumberException("Developer phone number requires ");
            }
            Developer dev = developerRepository.save(developer);
            savedDevelopers.add(dev);
        }
        Team t = new Team(teamTemplate.getName(),savedDevelopers);
        Team team= teamRepository.save(t);
        TeamTemplate template = new TeamTemplate(team.getName(), team.getDeveloperList());

        return template;

    }

    public Long assignTask(Long teamId) throws JSONException {

        Team team = teamRepository.getOne(teamId);

        List<Developer> developersList = team.getDeveloperList();

        Developer developer =developersList.stream()
                .filter(s-> s.getStatus()==Status.NOT_ENGAGED)
                .findAny()
                .orElse(null);
        if(developer == null){
            throw new DeveloperEmptyException("Every developer is engaged in this team");
        }

        developer.setStatus(Status.ENGAGED);

        developerRepository.save(developer);

        String getUrl = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Map> alertMapList = restTemplate.exchange(getUrl, HttpMethod.GET, entity, Map.class);
        JSONObject jsonObject;

        if (alertMapList.getStatusCode() == HttpStatus.OK) {
            jsonObject = new JSONObject(alertMapList.getBody());
            String successMessage = jsonObject.getString("success");

            if(successMessage.equals("alert sent")){
                return teamId;
            }
        }
        developer.setStatus(Status.NOT_ENGAGED);
        developerRepository.save(developer);
        throw new AlertFailedException("Something went while sending the alert");
    }
}
