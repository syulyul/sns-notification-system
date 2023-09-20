package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardCommentService;
import bitcamp.myapp.vo.BoardComment;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/boardComment")
public class BoardCommentController {
    {
        System.out.println("BoardCommentController 생성됨!");
    }

    @Autowired
    BoardCommentService boardCommentService;

    @GetMapping("form")
    public void form() throws Exception {
    }




}