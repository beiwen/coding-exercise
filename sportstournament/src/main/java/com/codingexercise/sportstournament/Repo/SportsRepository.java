package com.codingexercise.sportstournament.Repo;

import com.codingexercise.sportstournament.Entity.Sports;
import org.springframework.data.repository.CrudRepository;

public interface SportsRepository extends CrudRepository<Sports, Long> {
    Sports findBySportsName(String sportsName);
}

