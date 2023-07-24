package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {

  // accessing the model directly from the list of parameters
  @GetMapping("/grades")
  public String getGrades(Model model) {
    Grade grade = new Grade("Harry", "Potions", "C-"); // create a POJO object
    model.addAttribute("grade", grade); // adding data to the model
    return "grades";
  }
}
