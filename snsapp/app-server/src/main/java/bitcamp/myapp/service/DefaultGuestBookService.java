package bitcamp.myapp.service;

import bitcamp.myapp.dao.GuestBookDao;
import bitcamp.myapp.vo.GuestBook;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultGuestBookService implements GuestBookService {
    @Autowired
    GuestBookDao guestBookDao;

    @Autowired
    NotificationService notificationService;

    {
        System.out.println("DefaultGuestBookService 생성됨!");
    }

    @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
    @Override
    public int add(GuestBook guestBook) throws Exception {
        int count = guestBookDao.insert(guestBook);
        return count;
    }

    @Override
    public List<GuestBook> list(int userNo) throws Exception {
        return guestBookDao.findAll(userNo);
    }

    @Override
    public GuestBook get(int guestBookNo) throws Exception {
        return guestBookDao.findBy(guestBookNo);
    }

    @Override
    public int delete(int guestBookNo) throws Exception {
        guestBookDao.deleteLikes(guestBookNo);
        return guestBookDao.delete(guestBookNo);
    }

    @Override
    public int increaseLikes(int guestBookNo) throws Exception {
        return guestBookDao.updateLike(guestBookNo);
    }

    @Override
    public int decreaseLikes(int guestBookNo) throws Exception {
        return guestBookDao.cancelLike(guestBookNo);
    }

    @Override
    public int like(Member member, GuestBook guestBook) throws Exception {
        int result = guestBookDao.insertLike(member.getNo(), guestBook.getNo());
        notificationService.add(new NotiLog(
                guestBook.getWriter().getNo(),
                NotiType.FOLLOW_TYPE,
                member.getNick() + "님이 회원의 게시글을 좋아합니다.",
                "/guestBook/" + guestBook.getWriter().getNo())); // 이 부분 고민해봐야함
        return result;
    }

    @Override
    public int unlike(Member member, GuestBook guestBook) throws Exception {
        int result = guestBookDao.deleteLike(member.getNo(), guestBook.getNo());
        // notificationService.add(new NotiLog(
        // board.getWriter().getNo(),
        // NotiType.FOLLOW_TYPE,
        // member.getNick() + "님이 회원의 게시글 좋아요를 취소 했습니다..",
        // "/board/" +board.getCategory()+"/"+ board.getNo()));
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
}