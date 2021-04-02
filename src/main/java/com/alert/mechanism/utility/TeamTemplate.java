package com.alert.mechanism.utility;

import com.alert.mechanism.entity.Developer;
import com.alert.mechanism.entity.Team;

import javax.persistence.OneToMany;
import java.util.List;

public class TeamTemplate  {

    private String name;

    @OneToMany
    List<Developer> developerList;

    public TeamTemplate(String name, List<Developer> developerList) {
        this.name = name;
        this.developerList = developerList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }
}
