package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/auth")
public class AuthController {
    {
        System.out.println("AuthController 생성됨!");
    }

    @Autowired
    MemberService memberService;

    @Autowired
    NcpObjectStorageService ncpObjectStorageService;

    @GetMapping("form")
    public void form(@CookieValue(required = false) String phoneNumber, HttpSession session, Model model) {
//        model.addAttribute("phoneNumber", phoneNumber);
        session.setAttribute("phoneNumber", phoneNumber);
    }

    @GetMapping("add")
    public String add() {
        return "auth/membership";
    }

    @PostMapping("login")
    public String login(
            String phoneNumber,
            String password,
            String savePhoneNumber,
            HttpSession session,
            Model model,
            HttpServletResponse response) throws Exception {

        if (savePhoneNumber != null) {
            Cookie cookie = new Cookie("phoneNumber", phoneNumber);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("phoneNumber", "no");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

         Member loginUser = memberService.get(phoneNumber, password);
         if (loginUser == null) {
             model.addAttribute("refresh", "2;url=index");
             throw new Exception("회원 정보가 일치하지 않습니다.");
         }
        LoginUser loginUserObject = new LoginUser(loginUser);
         session.setAttribute("loginUser", loginUserObject);
//         System.out.println(loginUser.getNick());
        return "redirect:/myPage/" + loginUser.getNo() + "?show=followers";
    }

    @PostMapping("add")
    public String add(
            Member member,
            MultipartFile photofile,
            Model model) throws Exception {

        try {
            System.out.println(member);
            if (photofile.getSize() > 0) {
                String uploadFileUrl = ncpObjectStorageService.uploadFile(
                        "bitcamp-nc7-bucket-14", "sns_member/", photofile);
                member.setPhoto(uploadFileUrl);
            }
            memberService.add(member);
            return "auth/membership";

        } catch (Exception e) {
            model.addAttribute("message", "회원 등록 오류!");
            model.addAttribute("refresh", "2;url=list");
            throw e;
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/";
    }
}