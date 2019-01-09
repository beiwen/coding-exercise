package com.codingexercise.sportstournament.Repo;

import com.codingexercise.sportstournament.Entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>{
    Team findByTeamId(Long teamId);
}

