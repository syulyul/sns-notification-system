package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {
  int insert(Board board);
  List<Board> findAll(int category);
  Board findBy(int no);
  int update(Board board);
  int updateCount(int no);
  int delete(int no);

  int insertFiles(Board board);
  BoardPhoto findFileBy(int no);
  int deleteFile(int fileNo);
  int deleteFiles(int boardNo);

  int insertLike(@Param("memberNo") int memberNo, @Param("boardNo") int boardNo);
  int deleteLike(@Param("memberNo") int memberNo, @Param("boardNo") int boardNo);
  int updateIncreaseLikes(@Param("memberNo") int memberNo, @Param("boardNo") int boardNo);
  int updateDecreaseLikes(@Param("memberNo") int memberNo, @Param("boardNo") int boardNo);
}
