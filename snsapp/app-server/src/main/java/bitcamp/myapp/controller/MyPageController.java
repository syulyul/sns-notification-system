package bitcamp.myapp.controller;

import bitcamp.myapp.service.MyPageService;
import bitcamp.myapp.vo.LoginUser;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/myPage")
public class MyPageController {

  private static final int SHOW_BOARD = 0;
  private static final int SHOW_FOLLOWERS = 1;
  private static final int SHOW_FOLLOWINGS = 2;

  @Autowired
  MyPageService myPageService;

  {
    System.out.println("MyPageController 생성됨!");
  }

  @GetMapping("{no}")
  public String detail(
      @PathVariable int no,
      @RequestParam(defaultValue = "0") int show,
      Model model,
      HttpSession session) throws Exception {
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    model.addAttribute("myPage", myPageService.get(no));
    switch (show) {
      case SHOW_FOLLOWERS:
        model.addAttribute("list", myPageService.followerList(no));
        break;
      case SHOW_FOLLOWINGS:
        model.addAttribute("list", myPageService.followingList(no));
        break;
      default:
        model.addAttribute("list", null);
        break;
    }
    model.addAttribute("loginUser", loginUser);
    return "myPage/detail";
  }


}
