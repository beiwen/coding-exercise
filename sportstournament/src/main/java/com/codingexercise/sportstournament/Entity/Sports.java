package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    public Sports() {

    }

    public Long getSportsId() {
        return sportsId;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
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

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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
