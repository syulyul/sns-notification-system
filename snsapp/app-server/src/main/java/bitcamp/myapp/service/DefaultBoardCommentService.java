package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardCommentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardComment;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultBoardCommentService implements BoardCommentService {

  @Autowired
  HttpSession session;
  @Autowired
  BoardDao boardDao;
  @Autowired
  BoardCommentDao boardCommentDao;
  @Autowired
  NotificationService notificationService;

  {
    System.out.println("DefaultBoardCommentService 생성됨!");
  }

  @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
  @Override
  public int add(BoardComment boardComment) throws Exception {
    int count = boardCommentDao.insert(boardComment);
    Board board = boardDao.findBy(boardComment.getBoardNo());
    if (!session.getAttribute("loginUser").equals(board.getWriter())) {
      notificationService.add(new NotiLog(
          board.getWriter().getNo(),
          NotiType.COMMENT_TYPE,
          boardComment.getWriter().getNick() + "님이 회원님의 게시글에 댓글을 달았습니다.",
          "/board/detail/" + board.getCategory() + "/" + boardComment.getBoardNo()));
//        http://localhost/notification/updateState(memberNo=73,notiNo=409,notiState=1,url=/board/detail/1/66)
    }
    return count;
  }

  @Override
  public List<BoardComment> list(int boardNo) throws Exception {
    return boardCommentDao.findAll(boardNo);
  }

  @Override
  public BoardComment get(int no, int boardNo) throws Exception {
    return boardCommentDao.findBy(no, boardNo);
  }

  @Transactional
  @Override
  public int update(BoardComment boardComment) throws Exception {
    int count = boardCommentDao.update(boardComment);
    return count;
  }

  @Transactional
  @Override
  public int delete(int no, int boardNo) throws Exception {
    return boardCommentDao.delete(no, boardNo);
  }

  @Override
  public List<BoardComment> mycommentlist(int writerNo, int limit, int page) throws Exception {
    return boardCommentDao.findAllByMno(writerNo, limit, limit * (page - 1));
  }
}