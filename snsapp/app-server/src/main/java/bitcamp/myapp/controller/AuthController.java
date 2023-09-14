package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

  {
    System.out.println("AuthController 생성됨!");
  }


  @GetMapping("form")
  public void form(@CookieValue(required = false) String email, Model model) {
    model.addAttribute("email", email);
  }



}
