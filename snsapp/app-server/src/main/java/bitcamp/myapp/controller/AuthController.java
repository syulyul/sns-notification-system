package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

  {
    System.out.println("AuthController 생성됨!");
  }

  @Autowired
  MemberService memberService;

  @GetMapping("form")
  public void form(@CookieValue(required = false) String phoneNumber, Model model) {
    model.addAttribute("phoneNumber", phoneNumber);
  }

  @PostMapping("login")
  public String login(
      String phone_Number,
      String password,
      String savephone_Number,
      HttpSession session,
      Model model,
      HttpServletResponse response) throws Exception {

    if (savephone_Number != null) {
      Cookie cookie = new Cookie("phone_Number", phone_Number);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone_Number", "phone_Number");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }

}
