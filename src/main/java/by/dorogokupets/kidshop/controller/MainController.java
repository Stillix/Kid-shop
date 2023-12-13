package by.dorogokupets.kidshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/cv-service")
  public String showHomePage(Model model) {
    model.addAttribute("message", "Добро пожаловать на главную страницу!");
    return "home-page";
  }

  @GetMapping("/cv-service/about")
  public String showAbout(Model model) {
    model.addAttribute("message", "Добро пожаловать на главную страницу!");
    return "about";
  }

  @GetMapping("/cv-service/contacts")
  public String showContacts(Model model) {
    model.addAttribute("message", "Добро пожаловать на главную страницу!");
    return "contacts";
  }
}
