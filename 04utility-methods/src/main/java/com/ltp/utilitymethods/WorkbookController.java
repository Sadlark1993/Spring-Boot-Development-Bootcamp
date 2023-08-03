package com.ltp.utilitymethods;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* There are utility methods for:
 * arrays;
 * calendars;
 * dates;
 * lists;
 * maps;
 * numbers;
 * objects;
 * sets;
 * strings...
 * 
 * Syntax: "${type.method(target, args)}"
 * exemples: "${strings.contains(target, 'ez')}"
 * 
 * the 'contains' method, for exemple, returns a value of true or false.
 * 
 * List of utility methods: https://github.com/thymeleaf/thymeleaf/tree/3.1-master/lib/thymeleaf/src/main/java/org/thymeleaf/expression
 */

@Controller
public class WorkbookController {

    @GetMapping(value = "/")
    public String getMethodName(Model model) {
        model.addAttribute("menu", "We sell chocolate rice cakes bubble tea");
        return "view";
    }

}
