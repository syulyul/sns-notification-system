package bitcamp.myapp.service;

import bitcamp.myapp.dao.GuestBookDao;
import bitcamp.myapp.vo.GuestBook;
import bitcamp.myapp.vo.LoginUser;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultGuestBookService implements GuestBookService {

  @Autowired
  GuestBookDao guestBookDao;

  @Autowired
  HttpSession session;

  @Autowired
  NotificationService notificationService;

  {
    System.out.println("DefaultGuestBookService 생성됨!");
  }

  @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
  @Override
  public int add(GuestBook guestBook) throws Exception {
    int count = guestBookDao.insert(guestBook);
    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    if (!loginUser.equals(guestBook.getMpno())) {
      notificationService.add(new NotiLog(
          guestBook.getMpno(),
          NotiType.LIKE_TYPE,
          loginUser.getNick() + "님이 회원님의 방명록에 글을 작성했습니다.",
          "/guestBook/" + guestBook.getMpno()));
    }
    return count;
  }

  @Override
  public List<GuestBook> list(int no, int limit, int page) throws Exception {
    return guestBookDao.findAll(no, limit, limit * (page - 1));
  }

  @Override
  public int getTotalCount(int mpno) throws Exception {
    return guestBookDao.getTotalCount(mpno);
  }

  @Override
  public GuestBook get(int guestBookNo) throws Exception {
    return guestBookDao.findBy(guestBookNo);
  }

  @Transactional
  @Override
  public int delete(int guestBookNo) throws Exception {
    guestBookDao.deleteLikes(guestBookNo);
    return guestBookDao.delete(guestBookNo);
  }

  @Transactional
  @Override
  public int increaseLikes(int guestBookNo) throws Exception {
    return guestBookDao.updateLike(guestBookNo);
  }

  @Transactional
  @Override
  public int decreaseLikes(int guestBookNo) throws Exception {
    return guestBookDao.cancelLike(guestBookNo);
  }

  @Transactional
  @Override
  public int like(Member member, GuestBook guestBook) throws Exception {
    int result = guestBookDao.insertLike(member.getNo(), guestBook.getNo());
    guestBookDao.updateLike(guestBook.getNo());
    if (!session.getAttribute("loginUser").equals(guestBook.getWriter())) {
      notificationService.add(new NotiLog(
          guestBook.getWriter().getNo(),
          NotiType.FOLLOW_TYPE,
          member.getNick() + "님이 회원의 방명록 글을 좋아합니다.",
          "/guestBook/" + guestBook.getMpno()));
    }
    return result;
  }

  @Transactional
  @Override
  public int unlike(Member member, GuestBook guestBook) throws Exception {
    int result = guestBookDao.deleteLike(member.getNo(), guestBook.getNo());
    guestBookDao.cancelLike(guestBook.getNo());
//    if (!session.getAttribute("loginUser").equals(guestBook.getWriter())) {
//      notificationService.add(new NotiLog(
//          guestBook.getWriter().getNo(),
//          NotiType.FOLLOW_TYPE,
//          member.getNick() + "님이 회원의 방명록 글의 좋아요를 취소 했습니다..",
//          "/guestBook/" + guestBook.getMpno()));
//    }
    return result;
  }

  @Override
  public List<Integer> likelist(int memberNo) throws Exception {
    return guestBookDao.findLikeByMno(memberNo);
  }

  @Override
  public List<String> guestBooklikelist(int guestBookNo) throws Exception {
    return guestBookDao.findLikeByBno(guestBookNo);
  }

  @Override
  public String getMemberNickByNo(int memberNo) throws Exception {
    // GuestBookDao를 이용하여 회원 번호로 회원 닉네임을 가져옴
    return guestBookDao.findNickByMpno(memberNo);
  }
}