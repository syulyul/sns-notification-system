package bitcamp.myapp.dao;

import bitcamp.myapp.vo.BoardComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentDao {
  int insert(BoardComment boardComment);
  List<BoardComment> findAll(int boardNo);
  BoardComment findBy(int no);
  int update(BoardComment boardComment);
  int delete(int no);
}
