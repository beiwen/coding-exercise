package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

//This is the entity class which Hibernate will automatically translate into a table.
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private String employeeName;
    private int registeredSportsNumber;
    @ManyToMany
    private Set<Sports> registeredSports;
    @ManyToMany(mappedBy = "teamMembers")
    private Set<Team> addedTeams;

    // Constructor
    public Employee() {


    }
    public Long getEmployeeId() {
        return employeeId;
    }

    public int getRegisteredSportsNumber() {
        return registeredSportsNumber;
    }

    public void setRegisteredSportsNumber(int registeredSportsNumber) {
        this.registeredSportsNumber = registeredSportsNumber;
    }

    public Set<Sports> getRegisteredSports() {
        return registeredSports;
    }

    public void setRegisteredSports(Set<Sports> registeredSports) {
        this.registeredSports = registeredSports;
    }

    public Set<Team> getAddedTeams() {
        return addedTeams;
    }

    public void setAddedTeams(Set<Team> addedTeams) {
        this.addedTeams = addedTeams;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void registerSport(Sports s) {
        // check whether this individual has already registered this sport
        if(this.getRegisteredSports().contains(s)) {
            System.out.println("This individual has already registered the given sport");
            return;
        }

        // check whether this individual has already participated in 3 sports
        if(this.registeredSportsNumber == 3) {
            System.out.println("This individual has already participated in 3 sports");
            return;
        }

        // check whether this sport registration has been full
        if(s.isFull()) {
            System.out.println("This sport you wants to register has been full");
            return;
        }

        // check the sport the individual wants to register is a group sport or individual sport
        // assume that the possible sports options are Relay, Football, Volleyball, Tennis, Chess
        if(s.getSportsName().equals("Chess") || s.getSportsName().equals("Tennis")) {
            this.registeredSports.add(s);
            this.registeredSportsNumber++;
            s.setCurrentFilled(s.getCurrentFilled() + 1);
            s.setOpenSlots(s.getOpenSlots() - 1);
        } else {
            if(s.getTeamNumber() == 0) {
                Team newTeam = new Team(s);
                newTeam.addTeamMember(this);
                s.getTeams().add(newTeam);
                s.setTeamNumber(s.getTeamNumber() + 1);
            } else {
                boolean flag = false;
                for(Team team:s.getTeams()) {
                    if(!team.isFull()) {
                        team.addTeamMember(this);
                        flag = true;
                        break;
                    }
                }
                if(flag == false) {
                    Team newTeam = new Team(s);
                    newTeam.addTeamMember(this);
                    s.getTeams().add(newTeam);
                    s.setTeamNumber(s.getTeamNumber() + 1);
                }
            }
        }
    }

    public void deregisterSport(Sports s) {
        this.registeredSportsNumber--;
        this.registeredSports.remove(s);
        s.setCurrentFilled(s.getCurrentFilled() - 1);
        s.setOpenSlots(s.getOpenSlots() + 1);
        if(s.getSportsName().equals("Relay") || s.getSportsName().equals("Football") || s.getSportsName().equals("Volleyball")) {
            for(Team team: this.addedTeams) {
                if(team.getTeamMembers().contains(this)) {
                    this.addedTeams.remove(team);
                    team.getTeamMembers().remove(this);
                    team.setTeamSize(team.getTeamSize() - 1);
                    if(team.getTeamSize() == 0) {
                        s.getTeams().remove(team);
                        s.setTeamNumber(s.getTeamNumber() - 1);
                    }
                    break;
                }
            }
        }
    }
}
