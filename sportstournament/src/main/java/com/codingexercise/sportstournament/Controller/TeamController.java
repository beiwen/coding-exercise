package com.codingexercise.sportstournament.Controller;

import com.codingexercise.sportstournament.Entity.Employee;
import com.codingexercise.sportstournament.Entity.Sports;
import com.codingexercise.sportstournament.Entity.Team;
import com.codingexercise.sportstournament.Repo.EmployeeRepository;
import com.codingexercise.sportstournament.Repo.SportsRepository;
import com.codingexercise.sportstournament.Repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SportsRepository sportsRepository;
    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/createTeam")
    public String createTeam(@RequestParam("sportName") String sportName) {
        if(sportName.equals("Tennis") || sportName.equals("Chess")) {
            return "cannotCreateTeam";
        }
        Sports sport = sportsRepository.findBySportsName(sportName);
        sport.createTeam();
        return "createTeam";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/addTeamMember")
    public String addTeamMember(@RequestParam("employeeName") String employeeName,
                                @RequestParam("teamId") Long teamId) {
        Employee addedEmployee = employeeRepository.findByEmployeeName(employeeName);
        Team team = teamRepository.findByTeamId(teamId);
        team.addTeamMember(addedEmployee);
        return "addTeamMember";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/removeTeamMember")
    public String removeTeamMember(@RequestParam("employeeName") String employeeName,
                                   @RequestParam("teamId") Long teamId) {
        Employee removedEmployee = employeeRepository.findByEmployeeName(employeeName);
        Team team = teamRepository.findByTeamId(teamId);
        team.removeTeamMember(removedEmployee);
        return "removeTeamMember";
    }


}
