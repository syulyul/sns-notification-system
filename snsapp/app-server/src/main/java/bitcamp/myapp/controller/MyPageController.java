package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardCommentService;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.MyPageService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.MyPage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.desktop.SystemSleepListener;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

  @Autowired
  MemberService memberService;

  @Autowired
  MyPageService myPageService;

  @Autowired
  BoardService boardService;

  @Autowired
  BoardCommentService boardCommentService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  {
    System.out.println("MyPageController 생성됨!");
  }

  @GetMapping("{no}")
  public String detail(
      @PathVariable int no,
      @RequestParam(defaultValue = "") String show,
      @RequestParam(name = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "15") int pageSize,
      Model model,
      HttpSession session,
      @ModelAttribute("queryString") String queryString) throws Exception {
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    // 세션에 저장된 방문한 마이페이지 번호 목록을 가져오기
    HashSet<Integer> visitedMyPages = loginUser.getVisitedMyPages();

    // 만약 방문한 적 없는 마이페이지라면 조회수 증가
    if (!visitedMyPages.contains(no) && loginUser.getNo() != no) {
      myPageService.increaseVisitCount(no);

      // 방문한 마이페이지 번호를 세션에 추가
      visitedMyPages.add(no);
    }

    model.addAttribute("myPage", myPageService.get(no));
    model.addAttribute("show", show);
    model.addAttribute("page", page);

    switch (show) {
      case "followers":
        model.addAttribute("followList", myPageService.followerList(no));
        model.addAttribute("maxPage",
            (myPageService.getFollowerCount(no) + pageSize - 1) / pageSize);
        break;
      case "followings":
        model.addAttribute("followList", myPageService.followingList(no));
        model.addAttribute("maxPage",
            (myPageService.getFollowingCount(no) + pageSize - 1) / pageSize);
        break;
      case "searchMembers":
        model.addAttribute("keyword", keyword);
        model.addAttribute("followList", myPageService.searchMembersList(keyword, pageSize, page));
        model.addAttribute("maxPage",
            (myPageService.getSearchMembersCount(keyword) + pageSize - 1) / pageSize);
        break;
      default:
        model.addAttribute("followList", null);
        model.addAttribute("boardList", boardService.list(1, pageSize, 1));
        model.addAttribute("myboardList",
            boardService.myboardlist(1, no, pageSize, 1));
        model.addAttribute("mycommentList", boardCommentService.mycommentlist(no, pageSize, 1));
        break;
    }
    session.setAttribute("loginUser", loginUser);
    return "myPage/detail";
  }

  @PostMapping("{no}")
  public String searchMembers(
      @PathVariable int no,
      @RequestParam(defaultValue = "") String show,
      @RequestParam("keyword") String keyword,
      @RequestParam(defaultValue = "1") int page) throws Exception {
    String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
    String queryString = String.format("?show=%s&keyword=%s&page=%d", show, encodedKeyword, page);

    return "redirect:/myPage/" + no + queryString;
  }

  @GetMapping("{no}/info")
  public String info(
      @PathVariable int no,
      Model model,
      HttpServletRequest request,
      HttpSession session) throws Exception {
    MyPage myPage = myPageService.get(no);
    model.addAttribute("myPage", myPage);

    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    // request 객체가 null이 아닌 경우에만 모델에 추가
    if (request != null) {
      model.addAttribute("request", request);
    } else {
      return "redirect:/";
    }

    return "myPage/memberInfoUpdate";
  }

  @PostMapping("{no}/update")
  public String update(
      Member member,
      @PathVariable int no,
      @RequestParam("password") String password,
      @RequestParam("birthday") String birthday,
      @RequestParam("gender") int gender,
      @RequestParam("stateMessage") String stateMessage,
      Model model,
      MultipartFile photofile,
      HttpSession session) throws Exception {
    member.setPhoneNumber(member.getPhoneNumber().replaceAll("\\D+", ""));
    
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    MyPage myPage = myPageService.get(member.getNo());

    if (loginUser.getNo() == myPage.getNo()) {
        if (photofile.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-nc7-bucket-14", "sns_member/", photofile);
          member.setPhoto(uploadFileUrl);
        }

        myPage.setGender(gender);
        myPage.setStateMessage(stateMessage);
        // myPage.setEmail(email);
        if (birthday.isEmpty()) {
          birthday = null;
        } else {
          // 생일 값을 문자열에서 Timestamp로 변환
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Date parsedDate = dateFormat.parse(birthday);
          Timestamp timestamp = new Timestamp(parsedDate.getTime());

          myPage.setBirthday(timestamp);

        }

        myPage.setGender(gender);
        myPage.setStateMessage(stateMessage);

        if (member.getEmail().equals(" ") || member.getEmail().isEmpty()) {
          member.setEmail(null);
        }

      if (memberService.update(member) == 0 || myPageService.update(myPage) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        // 사용자 정보 업데이트 후, 세션에 새 정보를 설정
        loginUser.setName(member.getName()); // 사용자 이름 업데이트
        loginUser.setNick(member.getNick()); // 사용자 닉네임 업데이트
        if (!photofile.isEmpty()) {
          loginUser.setPhoto(member.getPhoto()); // 사용자 사진 업데이트
        }

        // 세션에 업데이트된 loginUser 속성을 다시 설정
        session.setAttribute("loginUser", loginUser);

        return "redirect:/myPage/" + myPage.getNo();
      }

    } else {
      return "redirect:/error";
    }

  }

  @GetMapping("{no}/update")
  public String delete(
          Member member,
          @PathVariable int no,
          Model model,
          HttpSession session) throws Exception {
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    MyPage myPage = myPageService.get(member.getNo());

    if (loginUser.getNo() == myPage.getNo()) {
      member.setPhoto(null);
      myPage.setGender(0);
      myPage.setStateMessage(null);
      myPage.setEmail(null);
      myPage.setBirthday(null);
      member.setName("탈퇴한 사용자");
      member.setNick((System.currentTimeMillis()>>10)%987+"탈퇴한 사용자"+System.currentTimeMillis()%1234);
      member.setPhoneNumber("000-"+System.currentTimeMillis());
      member.setPassword(null);
      myPage.setVisitCount(0);

      if (memberService.update(member) == 0 || myPageService.update(myPage) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        session.invalidate();
        return "redirect:/";
      }
    } else {
      return "redirect:/error";
    }

  }

  @GetMapping("follow")
  public void follow(
      @RequestParam("followingNo") int followingNo,
      HttpSession session,
      HttpServletResponse response) throws Exception {
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

    Map<String, Object> returnMap = new HashMap<>();
    try {
      int result = myPageService.follow(loginUser, followingNo);
      loginUser.getFollowMemberSet().add(memberService.get(followingNo));
      session.setAttribute("loginUser", loginUser);
      returnMap.put("result", "success");

    } catch (Exception e) {
      returnMap.put("result", "fail");

    } finally {
      try {
        response.getWriter().print(new ObjectMapper().writeValueAsString(returnMap));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

  }

  @GetMapping("unfollow")
  public void unfollow(
      @RequestParam("followingNo") int followingNo,
      HttpSession session,
      HttpServletResponse response) throws Exception {
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

    Map<String, Object> returnMap = new HashMap<>();
    try {
      int result = myPageService.unfollow(loginUser, followingNo);
      loginUser.getFollowMemberSet().remove(memberService.get(followingNo));
      session.setAttribute("loginUser", loginUser);
      returnMap.put("result", "success");

    } catch (Exception e) {
      returnMap.put("result", "fail");

    } finally {
      try {
        response.getWriter().print(new ObjectMapper().writeValueAsString(returnMap));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

}
