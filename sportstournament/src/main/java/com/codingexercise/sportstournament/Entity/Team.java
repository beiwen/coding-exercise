package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;
    private Long sportsId;
    private List<Employee> teamMembers;

}
