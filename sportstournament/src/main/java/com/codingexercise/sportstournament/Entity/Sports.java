package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sports {
    @Id
    @GeneratedValue
    private Long sportsId;
    private String sportsName;
    private int teamNumber;
    private int minParticipants;
    private int maxParticipants;
    private int currentFilled;
    private int openSlots;
    @OneToMany(mappedBy = "sport")
    private List<Team> teams;

    //Constructor
    public Sports(String sportsName) {
        this.sportsName = sportsName;
        teamNumber = 0;
        if(sportsName.equals("Tennis") || sportsName.equals("Chess")) {
            minParticipants = 2;
            maxParticipants = 10;
            openSlots = 10;
        } else if(sportsName.equals("Relay")) {
            minParticipants = 12;
            maxParticipants = 48;
            openSlots = 48;
        } else if(sportsName.equals("Football")) {
            minParticipants = 90;
            maxParticipants = 360;
            openSlots = 360;
        } else if(sportsName.equals("Volleyball")) {
            minParticipants = 24;
            maxParticipants = 96;
            openSlots = 96;
        }
        currentFilled = 0;
        teams = new ArrayList<>();
    }

    public String getSportsName() {
        return sportsName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getCurrentFilled() {
        return currentFilled;
    }

    public void setCurrentFilled(int currentFilled) {
        this.currentFilled = currentFilled;
    }

    public int getOpenSlots() {
        return openSlots;
    }

    public void setOpenSlots(int openSlots) {
        this.openSlots = openSlots;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public boolean isFull() {
        return currentFilled == maxParticipants? true : false;
    }

    public Team createTeam() {
        this.teamNumber++;
        Team newTeam = new Team(this);
        this.teams.add(newTeam);
        return newTeam;
    }
}
