package bitcamp.myapp.controller;

import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Mypage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Controller
@RequestMapping("/myPage")
public class MyPageController {

    {
        System.out.println("MyPageController 생성됨!");
    }

    @GetMapping("{no}")
    public String detail(@PathVariable int no, Model model) throws Exception {
        model.addAttribute("myPage", new Mypage());
        model.addAttribute("member", new Member());
        List<Member> list = new ArrayList<Member>();
        Member a = new Member();
        a.setNick("AAA");
        Member b = new Member();
        b.setNick("BBB");
        Member c = new Member();
        c.setNick("CCC");
        list.add(a);
        list.add(b);
        list.add(c);
        model.addAttribute("list", list);
        HashSet<Member> set = new HashSet<Member>();
        set.add(b);
        model.addAttribute("loginUser", b);
        model.addAttribute("set", set);
        return "myPage/detail";
    }


}
