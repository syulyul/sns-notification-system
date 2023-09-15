package bitcamp.myapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

  {
    System.out.println("AuthController 생성됨!");
  }

  @PostMapping("login")
  public String login(
      String email,
      String password,
      String saveEmail,
      HttpSession session,
      Model model,
      HttpServletResponse response) throws Exception {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

//    Member loginUser = memberService.get(email, password);
//    if (loginUser == null) {
//      model.addAttribute("refresh", "2;url=form");
//      throw new Exception("회원 정보가 일치하지 않습니다.");
//    }

//    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }


}
