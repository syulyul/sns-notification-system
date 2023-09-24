package bitcamp.myapp.controller;

import bitcamp.myapp.service.GuestBookService;
import bitcamp.myapp.vo.GuestBook;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/guestBook")
public class GuestBookController {

    @Autowired
    GuestBookService guestBookService;

    {
        System.out.println("GuestBookController 생성됨!");
    }

    @GetMapping("form")
    public void form() throws Exception {
    }

    @PostMapping("add")
    public String add(GuestBook guestBook, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }
        guestBook.setWriter(loginUser);

        guestBookService.add(guestBook);
        return "redirect:/guestBook/list/userNo=" + guestBook.getToUser().getNo();
    }

    @GetMapping("delete")
    public String delete(int no, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        GuestBook g = guestBookService.get(no);

        if (g == null || g.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
        } else {
            guestBookService.delete(g.getNo());
            return "redirect:/guestBook/list/userNo=" + loginUser.getNo();
        }
    }

    @GetMapping("/{no}")
    public String list(@PathVariable int no, Model model, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null) {
            List<Integer> likedGuestBooks = guestBookService.likelist(loginUser.getNo());
            model.addAttribute("likedGuestBooks", likedGuestBooks);
        }

        List<GuestBook> guestBookList = guestBookService.list(no);
        model.addAttribute("guestBookList", guestBookList);

        // 이 부분에서 회원의 닉네임을 가져와서 모델에 추가
        String guestBookOwnerNick = guestBookService.getMemberNickByNo(no);
        model.addAttribute("guestBookOwnerNick", guestBookOwnerNick);

        session.setAttribute("loginUser", loginUser);
        return "guestBook/read";
    }

    // 좋아요 기능
    @PostMapping("like")
    public int like(@RequestParam int guestBookNo, HttpSession session) throws Exception {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        try {
            GuestBook guestBook = guestBookService.get(guestBookNo);
            guestBookService.like(loginUser, guestBook);
            guestBookService.increaseLikes(guestBookNo);
            return 1; // 예: 성공시 1 반환
        } catch (Exception e) {
            return -1;
        }
    }

    @PostMapping("unlike")
    public int unlike(@RequestParam int guestBookNo, HttpSession session) throws Exception {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        try {
            GuestBook guestBook = guestBookService.get(guestBookNo);
            guestBookService.unlike(loginUser, guestBook);
            guestBookService.decreaseLikes(guestBookNo);
            return 1; // 예: 성공시 1 반환
        } catch (Exception e) {
            return -1;
        }
    }

    @GetMapping("/likedGuestBooks")
    public ResponseEntity<List<Integer>> getLikedGuestBooks(HttpSession session) {
        try {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null) {
                // 로그인되지 않은 경우
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<Integer> likedGuestBooks = guestBookService.likelist(loginUser.getNo());
            return new ResponseEntity<>(likedGuestBooks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}