package com.alert.mechanism;


import com.alert.mechanism.entity.Developer;
import com.alert.mechanism.service.DeveloperService;
import com.alert.mechanism.service.TeamService;
import com.alert.mechanism.utility.Status;
import com.alert.mechanism.utility.TeamTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeveloperTest  extends MechanismApplicationTests{

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private TeamService teamService;

    @Test
    public void testSaveDeveloper() {
        Developer dev = new Developer("Lucky","9041542868", Status.NOT_ENGAGED);
        Developer developer = developerService.saveDeveloper(dev);
        assertThat(developer.getName().equals("Lucky"));
    }

    @Test
    public void testSaveTeam(){
        Developer dev = new Developer("Saurabh","9835429408", Status.NOT_ENGAGED);
        List<Developer> developerList = new ArrayList<>();
        developerList.add(dev);
        TeamTemplate teamTemplate = new TeamTemplate("Mavericks",developerList);
        TeamTemplate team = teamService.saveTeam(teamTemplate);
        assertThat(team.getName().equals("Mavericks"));
    }

}
