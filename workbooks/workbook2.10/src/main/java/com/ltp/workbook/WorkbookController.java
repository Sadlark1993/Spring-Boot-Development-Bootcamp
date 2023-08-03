package com.ltp.workbook;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkbookController {

    List<Employee> employees = Arrays.asList(
            new Employee("Jim Halpert", 32, "Salesman"),
            new Employee("Andy Bernanrd", 38, "Salesman"),
            new Employee("Pam Beesly", 32, "Recepcionist"),
            new Employee("Michael Scott", 49, "Regional Manager"),
            new Employee("Ryan Howard", 28, "Temp"),
            new Employee("Angela Martin", 35, "Accountant"),
            new Employee("Dwight Schrute", 32, "Assistant to Regional Manager"));

    @GetMapping(value = "/")
    public String getStaff(Model model) {
        model.addAttribute("employees", employees);
        return "staff";
    }

}
