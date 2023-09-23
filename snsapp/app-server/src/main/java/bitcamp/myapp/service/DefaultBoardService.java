package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardCommentDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardPhoto;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultBoardService implements BoardService {

  @Autowired
  BoardDao boardDao;
  @Autowired
  BoardCommentDao boardCommentDao;
  @Autowired
  NotificationService notificationService;

  {
    System.out.println("DefaultBoardService 생성됨!");
  }

  @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
  @Override
  public int add(Board board) throws Exception {
    int count = boardDao.insert(board);
    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
    return count;
  }

  @Override
  public List<Board> list(int category, int page, int pageSize) throws Exception {
    int startRecord = (page - 1) * pageSize;
    return boardDao.findAll(category, pageSize, startRecord);
  }

  @Override
  public int getTotalCount(int category) throws Exception {
    return boardDao.getTotalCount(category);
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findBy(boardNo);
  }

  @Transactional
  @Override
  public int update(Board board) throws Exception {
    int count = boardDao.update(board);
    if (count > 0 && !board.getAttachedFiles().isEmpty()) {
      boardDao.insertFiles(board);
    }
    return count;
  }

  @Transactional
  @Override
  public int delete(int boardNo) throws Exception {
    boardDao.deleteFiles(boardNo);
    boardCommentDao.deleteComments(boardNo);
    boardDao.deleteLikes(boardNo);
    return boardDao.delete(boardNo);
  }

  @Transactional
  @Override
  public int increaseViewCount(int boardNo) throws Exception {
    return boardDao.updateCount(boardNo);
  }

  @Override
  public BoardPhoto getAttachedFile(int fileNo) throws Exception {
    return boardDao.findPhotoBy(fileNo);
  }

  @Override
  public int deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo);
  }

  // like 테이블 정보 삽입 삭제
  @Override
  public int like(Member member, Board board) throws Exception {
    int result = boardDao.insertLike(member.getNo(), board.getNo());
    notificationService.add(new NotiLog(
        board.getWriter().getNo(),
        NotiType.FOLLOW_TYPE,
        member.getNick() + "님이 회원의 게시글을 좋아합니다.",
        "/board/" + board.getCategory() + "/" + board.getNo()));
    return result;
  }

  @Override
  public int unlike(Member member, Board board) throws Exception {
    int result = boardDao.deleteLike(member.getNo(), board.getNo());
//        notificationService.add(new NotiLog(
//            board.getWriter().getNo(),
//            NotiType.FOLLOW_TYPE,
//            member.getNick() + "님이 회원의 게시글 좋아요를 취소 했습니다..",
//            "/board/" +board.getCategory()+"/"+ board.getNo()));
    return result;
  }


  // board 테이블 좋아요 수 +1 -1
  @Transactional
  @Override
  public int increaseLikes(int boardNo) throws Exception {
    return boardDao.updateLike(boardNo);
  }

  @Override
  public int decreaseLikes(int boardNo) throws Exception {
    return boardDao.cancelLike(boardNo);
  }

  @Override
  public List<Integer> likelist(int memberNo) throws Exception {
    return boardDao.findLikeByMno(memberNo);
  }
  @Override
  public List<String> boardlikelist(int boardNo) throws Exception {
    return boardDao.findLikeByBno(boardNo);
  }


}