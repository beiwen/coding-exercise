package com.codingexercise.sportstournament.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;
    private int teamCapacity;
    private int teamSize;
    @ManyToMany
    private List<Employee> teamMembers;
    @ManyToOne
    private Sports sport;

    // Constructor
    public Team(Sports s) {
        this.sport = s;
        this.teamSize = 0;
        if(s.getSportsName().equals("Relay")) {
            this.teamCapacity = 6;
        } else if(s.getSportsName().equals("Football")) {
            this.teamCapacity = 45;
        } else {
            this.teamCapacity = 12;

        }
        teamMembers = new ArrayList<>(this.teamCapacity);
    }

    public Long getTeamId() {
        return teamId;
    }

    public int getTeamCapacity() {
        return teamCapacity;
    }

    public void setTeamCapacity(int teamCapacity) {
        this.teamCapacity = teamCapacity;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public List<Employee> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<Employee> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Sports getSport() {
        return sport;
    }

    public void setSports(Sports sport) {
        this.sport = sport;
    }

    public void addTeamMember(Employee e) {
        // check whether this team is full
        if(isFull()) {
            System.out.println("Current team has been full!");
            return;
        }
        // check whether this sport registration has been full
        if(this.getSport().isFull()) {
            System.out.println("The sport that the current team belongs to has been fully filled!");
            return;
        }
        // check whether this individual has already participate in 3 sports
        if(e.getRegisteredSportsNumber() == 3) {
            System.out.println("This individual you add into the team has participate in 3 sports");
            return;
        }
        // check whether this individual has registered this sport the team corresponds to.
        if(e.getRegisteredSports().contains(this.sport)) {
            System.out.println("This individual you add into the team has registered this sport");
            return;
        }

        this.teamMembers.add(e);
        this.teamSize++;

        e.getAddedTeams().add(this);
        e.setRegisteredSportsNumber(e.getRegisteredSportsNumber() + 1);
        e.getRegisteredSports().add(this.sport);

        this.sport.setCurrentFilled(this.sport.getCurrentFilled() + 1);
        this.sport.setOpenSlots(this.sport.getOpenSlots() - 1);
    }

    public void removeTeamMember(Employee e) {
        this.teamMembers.remove(e);
        this.teamSize--;
        if(teamSize == 0) {
            this.sport.getTeams().remove(this);
            this.sport.setTeamNumber(this.sport.getTeamNumber() - 1);
        }
        e.getRegisteredSports().remove(this.sport);
        e.setRegisteredSportsNumber(e.getRegisteredSportsNumber() - 1);
        e.getAddedTeams().remove(this);
        this.sport.setCurrentFilled(this.sport.getCurrentFilled() - 1);
        this.sport.setOpenSlots(this.sport.getOpenSlots() + 1);
    }

    public boolean isFull() {
        return teamSize == teamCapacity? true : false;
    }

}
