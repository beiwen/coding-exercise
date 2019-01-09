package com.codingexercise.sportstournament.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sports {
    @Id
    @GeneratedValue
    private Long sportsId;
    private String sportsName;
    private int minParticipants;
    private int maxParticipants;
    private int teamSize;
    private int currentFilled;
    private int openSlots;
    private boolean isFull;

}
