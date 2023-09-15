package bitcamp.myapp.service;

import bitcamp.myapp.vo.BoardPhoto;
import bitcamp.myapp.vo.Board;

import java.util.List;

public interface BoardService {
  int add(Board board) throws Exception;
  List<Board> list(int category) throws Exception;
  Board get(int boardNo) throws Exception;
  int update(Board board) throws Exception;
  int delete(int boardNo) throws Exception;

  //조회수
  int increaseViewCount(int boardNo) throws Exception;

  BoardPhoto getBoardPhoto(int fileNo) throws Exception;
  int deleteBoardPhoto(int fileNo) throws Exception;

  //좋아요 추가
  void increaseLikes(int boardNo, int memberNo) throws Exception;
//  int getLikes(int boardNo) throws Exception;


}
