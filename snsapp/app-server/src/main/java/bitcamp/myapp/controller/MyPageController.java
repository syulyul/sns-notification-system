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

    @Autowired
    MyPageService myPageService;

    {
        System.out.println("MyPageController 생성됨!");
    }

    @GetMapping("{no}")
    public String detail(
            @PathVariable int no,
            @RequestParam(defaultValue = "") String show,
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

        // 세션에 저장된 방문한 마이페이지 번호 목록을 가져오기
        HashSet<Integer> visitedMyPages = loginUser.getVisitedMyPages();

        // 만약 방문한 적 없는 마이페이지라면 조회수 증가
        if (!visitedMyPages.contains(no)) {
            myPageService.increaseVisitCount(no);

            // 방문한 마이페이지 번호를 세션에 추가
            visitedMyPages.add(no);
        }

        model.addAttribute("myPage", myPageService.get(no));
        model.addAttribute("show", show);
        switch (show) {
            case "followers":
                model.addAttribute("list", myPageService.followerList(no));
                break;
            case "followings":
                model.addAttribute("list", myPageService.followingList(no));
                break;
            default:
                model.addAttribute("list", null);
                break;
        }
//        myPageService.increaseVisitCount(no);
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
        loginUser.setNick("김성주");
        loginUser.setFollowMemberSet(new HashSet<>());
        myPageService.follow(loginUser, followingNo);
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
        loginUser.setNick("김성주");
        loginUser.setFollowMemberSet(new HashSet<>());
        myPageService.unfollow(loginUser, followingNo);
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(new HashMap<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
