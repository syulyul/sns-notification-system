package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardCommentDao;
import bitcamp.myapp.vo.BoardComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultBoardCommentService implements BoardCommentService {
    {
        System.out.println("DefaultBoardCommentService 생성됨!");
    }

    BoardCommentDao boardCommentDao;

    public DefaultBoardCommentService(BoardCommentDao boardCommentDao) {
        this.boardCommentDao = boardCommentDao;
    }

    @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
    @Override
    public int add(BoardComment boardComment) throws Exception {
        int count = boardCommentDao.insert(boardComment);
        return count;
    }

    @Override
    public List<BoardComment> list(int boardNo) throws Exception {
        return boardCommentDao.findAll(boardNo);
    }

    @Override
    public BoardComment get(int no) throws Exception {
        return boardCommentDao.findBy(no);
    }

    @Transactional
    @Override
    public int update(BoardComment boardComment) throws Exception {
        int count = boardCommentDao.update(boardComment);
        return count;
    }

    @Transactional
    @Override
    public int delete(int no) throws Exception {
        return boardCommentDao.delete(no);
    }
}