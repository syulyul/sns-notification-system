package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardPhoto;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/board")
public class BoardController {
    {
        System.out.println("BoardController 생성됨!");
    }

    @Autowired
    BoardService boardService;

    @Autowired
    NcpObjectStorageService ncpObjectStorageService;

    @GetMapping("form")
    public void form() throws Exception {
    }

    @PostMapping("add")
    public String add(Board board, MultipartFile[] files, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }
        board.setWriter(loginUser);

        ArrayList<BoardPhoto> attachedFiles = new ArrayList<>();
        for (MultipartFile part : files) {
            if (part.getSize() > 0) {
                String uploadFileUrl = ncpObjectStorageService.uploadFile(
                        "bitcamp-nc7-bucket-14", "board/", part);
                BoardPhoto attachedFile = new BoardPhoto();
                attachedFile.setFilePath(uploadFileUrl);
                attachedFiles.add(attachedFile);
            }
        }
        board.setAttachedFiles(attachedFiles);

        boardService.add(board);
        return "redirect:/board/list?category=" + board.getCategory();
    }

    @GetMapping("delete")
    public String delete(int no, int category, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        Board b = boardService.get(no);

        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
        } else {
            boardService.delete(b.getNo());
            return "redirect:/board/list?category=" + category;
        }
    }

    @GetMapping("detail/{category}/{no}")
    public String detail(@PathVariable int category, @PathVariable int no, Model model) throws Exception {
        Board board = boardService.get(no);
        if (board != null) {
            boardService.increaseViewCount(no);
            model.addAttribute("board", board);
        }
        return "board/detail";
    }

    @GetMapping("list")
    public void list(int category, Model model) throws Exception {
        model.addAttribute("list", boardService.list(category));
    }

    @PostMapping("update")
    public String update(Board board, MultipartFile[] files, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        Board b = boardService.get(board.getNo());
        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("게시글이 존재하지 않거나 변경 권한이 없습니다.");
        }

        ArrayList<BoardPhoto> attachedFiles = new ArrayList<>();
        for (MultipartFile part : files) {
            if (part.getSize() > 0) {
                String uploadFileUrl = ncpObjectStorageService.uploadFile(
                        "bitcamp-nc7-bucket-14", "board/", part);
                BoardPhoto attachedFile = new BoardPhoto();
                attachedFile.setFilePath(uploadFileUrl);
                attachedFiles.add(attachedFile);
            }
        }
        board.setAttachedFiles(attachedFiles);

        boardService.update(board);
        return "redirect:/board/list?category=" + b.getCategory();
    }

    @GetMapping("fileDelete/{attachedFile}") // 예) .../fileDelete/attachedfile;no=30
    public String fileDelete(@MatrixVariable("no") int no, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        Board board = null;
        BoardPhoto attachedFile = boardService.getAttachedFile(no);
        board = boardService.get(attachedFile.getBoardNo());
        if (board.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("게시글 변경 권한이 없습니다!");
        }

        if (boardService.deleteAttachedFile(no) == 0) {
            throw new Exception("해당 번호의 첨부파일이 없습니다.");
        } else {
            return "redirect:/board/detail/" + board.getCategory() + "/" + board.getNo();
        }
    }

    @GetMapping("like")
    public String like(
            @RequestParam int show,
            @RequestParam("boardNo") int boardNo,
            @RequestParam("likes") int likes) throws Exception {
        // 로그인한 사용자 정보를 가져오는 코드를 추가
//        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUser = new LoginUser();
        loginUser.setNo(1); // 임시로 사용자 번호설정

        // 게시글 좋아요 토글 메소드를 호출하여 사용자의 좋아요를 추가하거나 제거합니다.
        BoardService.

        // 다시 해당 게시글 상세 페이지로 리다이렉트합니다.
        return "redirect:/board/detail/" + boardNo + "?show=" + show;
    }

    @GetMapping("unlike")
    public String unfollow(
            @RequestParam int show,
            @RequestParam("memberNo") int memberNo,
            @RequestParam("boardNo") int boardNo) throws Exception {
//    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUser = new LoginUser();
        loginUser.setNo(1);
        myPageService.unfollow(loginUser.getNo(), boardNo);
        return "redirect:" + myPageNo + "?show=" + show;
    }

//    @GetMapping("follow")
//    public String follow(
//            @RequestParam int show,
//            @RequestParam("myPageNo") int myPageNo,
//            @RequestParam("followingNo") int followingNo) throws Exception {
////    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
//        LoginUser loginUser = new LoginUser();
//        loginUser.setNo(1);
//        myPageService.follow(loginUser.getNo(), followingNo);
//        return "redirect:" + myPageNo + "?show=" + show;
//    }
//
//    @GetMapping("unfollow")
//    public String unfollow(
//            @RequestParam int show,
//            @RequestParam("myPageNo") int myPageNo,
//            @RequestParam("followingNo") int followingNo) throws Exception {
////    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
//        LoginUser loginUser = new LoginUser();
//        loginUser.setNo(1);
//        myPageService.unfollow(loginUser.getNo(), followingNo);
//        return "redirect:" + myPageNo + "?show=" + show;
//    }
}