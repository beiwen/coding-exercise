package com.codingexercise.sportstournament.Controller;

import com.codingexercise.sportstournament.Entity.Employee;
import com.codingexercise.sportstournament.Entity.Sports;
import com.codingexercise.sportstournament.Repo.EmployeeRepository;
import com.codingexercise.sportstournament.Repo.SportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportsController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SportsRepository sportsRepository;

    @RequestMapping(method = RequestMethod.PUT, value = "/register")
    public String registerSport(@RequestParam("employeeId") Long employeeId,
                                @RequestParam("registeredSportName") String registeredSportName) {

        Employee currentEmployee = employeeRepository.findByEmployeeId(employeeId);
        Sports registeredSport = sportsRepository.findBySportsName(registeredSportName);
        currentEmployee.registerSport(registeredSport);
        return "registered";
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/deregister")
    public String deregisterSport(@RequestParam("employeeId") Long employeeId,
                                  @RequestParam("deregisteredSportName") String deregisteredSportName) {
        Employee currentEmployee = employeeRepository.findByEmployeeId(employeeId);
        Sports deregisteredSport = sportsRepository.findBySportsName(deregisteredSportName);
        currentEmployee.deregisterSport(deregisteredSport);
        return "deregister";
    }

}
