package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {

  /*
   * I've filled the list out of the method, to avoid it to add more rows for each
   * page refresh.
   */

  List<Grade> studentGrades = Arrays.asList(
      new Grade("Harry", "Potions", "C-"),
      new Grade("Hermione", "Charms", "A+"),
      new Grade("Neville", "Herbology", "A+"));

  @GetMapping("/grades")
  public String getGrades(Model model) {

    model.addAttribute("grade", studentGrades);
    return "grades";
  }

  @GetMapping("/g")
  public String getForm(Model model) {

    model.addAttribute("form", new Grade());
    return "form";
  }
}
