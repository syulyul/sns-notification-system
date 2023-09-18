package bitcamp.myapp.controller;

import bitcamp.myapp.service.MyPageService;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


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
//    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUser = new LoginUser();
        loginUser.setNo(1);
        HashSet<Member> followingSet = new HashSet<>();
        Member member1 = new Member();
        member1.setNo(1);
        followingSet.add(member1);
        loginUser.setFollowMemberSet(followingSet);
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
        myPageService.increaseVisitCount(no);
        model.addAttribute("loginUser", loginUser);
        return "myPage/detail";
    }

    @GetMapping("follow")
    public void follow(
            @RequestParam("myPageNo") int myPageNo,
            @RequestParam("followingNo") int followingNo,
            HttpServletResponse response) throws Exception, IOException {
//    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUser = new LoginUser();
        loginUser.setNo(1);
        loginUser.setFollowMemberSet(new HashSet<>());
        myPageService.follow(loginUser.getNo(), followingNo);
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(new HashMap<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("unfollow")
    public void unfollow(
            @RequestParam("myPageNo") int myPageNo,
            @RequestParam("followingNo") int followingNo,
            HttpServletResponse response) throws Exception {
//    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUser = new LoginUser();
        loginUser.setNo(1);
        loginUser.setFollowMemberSet(new HashSet<>());
        myPageService.unfollow(loginUser.getNo(), followingNo);
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(new HashMap<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
