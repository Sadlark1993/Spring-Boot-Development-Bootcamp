package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  /*
   * the annotation @RequestParam means that the argument must come from a GET
   * request, only.
   */
  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    int index = getGradeIndex(id);
    // System.out.println(name);
    // created a new POJO object that will be linked to the form
    model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentGrades.get(index));
    return "form";
  }

  @PostMapping("/handleSubmit")
  public String submitGrade(@Valid Grade grade, BindingResult result) {
    System.out.println("Has errors: " + result.hasErrors());
    if(result.hasErrors()) return "form";
    int index = getGradeIndex(grade.getId());
    if (index == Constants.NOT_FOUND) { // Registers a new grade
      studentGrades.add(grade);
    } else { // Updates existing grade
      studentGrades.set(index, grade);
    }
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

  public Integer getGradeIndex(String id) {
    for (int i = 0; i < studentGrades.size(); i++) {
      if (studentGrades.get(i).getId().equals(id))
        return i;
    }
    return Constants.NOT_FOUND;
  }

}
