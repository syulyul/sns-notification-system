package bitcamp.myapp.controller;

import bitcamp.myapp.service.NotificationService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    {
        System.out.println("NotificationController 생성됨!");
    }

    @Autowired
    NotificationService notificationService;

    @GetMapping("list")
    public void list(Model model, int memberNo) throws Exception {
        model.addAttribute("list", notificationService.notiLogList(memberNo));
    }

    @PostMapping("updateState")
    public void update(int notiState, int notiNo, HttpSession session) throws Exception {

        Member loginUser = (Member) session.getAttribute("loginUser");

        notificationService.updateState(notiNo, notiState);
    }

}
