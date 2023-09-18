package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardCommentService;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardComment;
import bitcamp.myapp.vo.BoardLike;
import bitcamp.myapp.vo.BoardPhoto;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("add")
    public String add(BoardComment boardComment, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }
        boardComment.setWriter(loginUser);

        boardCommentService.add(boardComment);
        return "redirect:/board/detail?category=1&no=" + boardComment.getBoardNo();
    }

    @GetMapping("delete/{no}/{boardNo}")
    public String delete(@PathVariable int no, @PathVariable int boardNo, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        BoardComment b = boardCommentService.get(no, boardNo);

        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
        } else {
            boardCommentService.delete(b.getNo(), b.getBoardNo());
            return "redirect:/board/detail?category=1&no=" + boardNo;
        }
    }

    @GetMapping("list")
    public void list(int boardNo, Model model) throws Exception {
        model.addAttribute("list", boardCommentService.list(boardNo));
    }

    @PostMapping("update")
    public String update(BoardComment boardComment, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        BoardComment b = boardCommentService.get(boardComment.getNo(), boardComment.getBoardNo());
        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("댓글이 존재하지 않거나 변경 권한이 없습니다.");
        }

        boardCommentService.update(boardComment);
        return "redirect:/board/detail?category=1&no=" + boardComment.getBoardNo();
    }
}