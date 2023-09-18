package bitcamp.myapp.service;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardPhoto;

import java.util.List;

public interface BoardService {
    int add(Board board) throws Exception;
    List<Board> list(int category) throws Exception;
    Board get(int boardNo) throws Exception;
    int update(Board board) throws Exception;
    int delete(int boardNo) throws Exception;
    int increaseViewCount(int boardNo) throws Exception;

    BoardPhoto getAttachedFile(int fileNo) throws Exception;
    int deleteAttachedFile(int fileNo) throws Exception;

    int like(int memberNo, int boardNo) throws Exception;
    int unLike(int memberNo, int boardNo) throws Exception;
    int increaseLike(int memberNo, int boardNo) throws Exception;
    int decreaseLike(int memberNo, int boardNo) throws Exception;
}