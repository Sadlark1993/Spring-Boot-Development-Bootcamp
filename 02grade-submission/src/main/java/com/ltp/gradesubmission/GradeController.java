package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

  /*
   * I've filled the list out of the method, to avoid it to add more rows for each
   * page refresh.
   */

  List<Grade> studentGrades = new ArrayList<Grade>();

  // just to pre populate the list.
  public GradeController() {
    this.studentGrades.add(new Grade("Harry", "Potions", "C-"));
    this.studentGrades.add(new Grade("Hermione", "Charms", "A+"));
    this.studentGrades.add(new Grade("Neville", "Herbology", "A+"));
  }

  @GetMapping("/")
  public String getForm(Model model) {

    // created a new POJO object that will be linked to the form
    model.addAttribute("grade", new Grade());
    return "form";
  }

  @PostMapping("/handleSubmit")
  public String submitGrade(Grade grade) {
    studentGrades.add(grade);
    /*
     * the redirection executes the handle method of /grades. If we returned /grades
     * directly, it would not execute its handler method.
     */
    return "redirect:/grades";
  }

  @GetMapping("/grades")
  public String getGrades(Model model) {

    model.addAttribute("grades", studentGrades);
    return "grades";
  }

}
