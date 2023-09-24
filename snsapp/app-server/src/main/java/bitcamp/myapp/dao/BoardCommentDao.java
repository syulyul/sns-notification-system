package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentDao {
  int insert(BoardComment boardComment);
  List<BoardComment> findAll(int boardNo);
  BoardComment findBy(int no, int boardNo);
  int update(BoardComment boardComment);
  int delete(int no, int boardNo);
  int deleteComments(int boardNo); // 해당 게시판이 삭제되면 댓글까지 다 삭제

  List<BoardComment> findAllByMno(
          @Param("writerNo") int writerNo,
          @Param("limit") int limit,
          @Param("offset") int offset);
}
