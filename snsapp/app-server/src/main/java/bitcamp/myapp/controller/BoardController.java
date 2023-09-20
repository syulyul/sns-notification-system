package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardCommentService;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardComment;
import bitcamp.myapp.vo.BoardPhoto;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    {
        System.out.println("BoardController 생성됨!");
    }

    @Autowired
    BoardService boardService;

    @Autowired
    BoardCommentService boardCommentService;

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

        System.out.println(no);
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

        // 댓글 조회
        List<BoardComment> comments = null;
        comments = boardCommentService.list(no);
        model.addAttribute("comments", comments);

        return "board/detail";
    }

    @GetMapping("list")
    public String list(@RequestParam int category, Model model) throws Exception {
        if (category == 1) {
            model.addAttribute("list", boardService.list(category));
            return "board/list"; // 카테고리가 1일 때 "list.html"을 실행

        } else if (category == 2) {
            model.addAttribute("list", boardService.list(category));
            return "board/read"; // 카테고리가 2일 때 "read.html"을 실행
        } else {
            throw new Exception("유효하지 않은 카테고리입니다.");
        }
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

    @PostMapping("like")
    public int like(@RequestParam int memberNo, @RequestParam int boardNo) {
        try {
            boardService.like(memberNo, boardNo);
            return boardService.increaseLikes(boardNo);
        } catch (Exception e) {
            return -1;
        }
    }

    @PostMapping("unlike")
    public int unlike(@RequestParam int memberNo, @RequestParam int boardNo) {
        try {
            boardService.unlike(memberNo, boardNo);
            return boardService.decreaseLikes(boardNo);
        } catch (Exception e) {
            return -1;
        }
    }

    @PostMapping("addComment")
    public String addComment(BoardComment boardComment, HttpSession session, @RequestParam("boardNo") int boardNo) throws Exception {
        Member loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        boardComment.setBoardNo(boardNo);
        boardComment.setWriter(loginUser);

        boardCommentService.add(boardComment);
        return "redirect:/board/detail/1/" + boardComment.getBoardNo();
    }

    @GetMapping("detailComment/{boardNo}/{no}")
    public String detailComment(@PathVariable int boardNo, @PathVariable int no, Model model) throws Exception {
        BoardComment boardComment = boardCommentService.get(no, boardNo);
        if (boardComment != null) {
            model.addAttribute("boardComment", boardComment);
        }

        return "board/updateComment";
    }

    @PostMapping("updateComment")
    public String updateComment(BoardComment boardComment, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        BoardComment b = boardCommentService.get(boardComment.getNo(), boardComment.getBoardNo());
        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("댓글이 존재하지 않거나 변경 권한이 없습니다.");
        }

        boardCommentService.update(boardComment);
        return "redirect:/board/detail/1/" + boardComment.getBoardNo();
    }

    @GetMapping("deleteComment/{boardNo}/{no}")
    public String deleteComment(@PathVariable int no, @PathVariable int boardNo, HttpSession session) throws Exception {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/form";
        }

        BoardComment b = boardCommentService.get(no, boardNo);

        if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
            throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
        } else {
            boardCommentService.delete(no, boardNo);
            return "redirect:/board/detail/1/" + boardNo; 
        }
    }
}