package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Malyar on 22.04.2015.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "login";
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("message", "Hello Admin!");
        return "admin";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        if (name.equalsIgnoreCase("admin")) {
            return "redirect:/admin";
        } else {
            return "welcome";
        }
    }
}
