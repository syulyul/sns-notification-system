package bitcamp.myapp.controller;

import bitcamp.myapp.vo.Member;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  {
    System.out.println("HomeController 생성됨!");
  }

  @GetMapping("/")
  public String home(HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:auth/form";
    }

    return "redirect:/myPage/" + loginUser.getNo();
  }
}

