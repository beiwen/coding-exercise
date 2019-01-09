package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

//This is the entity class which Hibernate will automatically translate into a table.
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private int registeredSportsNumber;
    private List<Sports> registeredSports;
}
