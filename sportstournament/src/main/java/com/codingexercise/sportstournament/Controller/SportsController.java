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
import org.springframework.ui.Model;

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

    @RequestMapping(method = RequestMethod.GET, value = "/getRegisteredSports")
    public String getRegisteredSports(@RequestParam("employeeId") Long employeeId,
                                      Model model) {
        Employee currentEmployee = employeeRepository.findByEmployeeId(employeeId);
        model.addAttribute("allRegisteredSports", currentEmployee.getRegisteredSports());
        return "allRegisteredSports";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSummaryForASport")
    public String getSummaryForASport(@RequestParam("sportName") String sportName,
                                      Model model) {
        Sports sport = sportsRepository.findBySportsName(sportName);
        model.addAttribute("currentFilled", sport.getCurrentFilled());
        model.addAttribute("openSlots", sport.getOpenSlots());
        return "sportSummary";
    }

}
