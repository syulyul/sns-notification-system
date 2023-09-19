package bitcamp.myapp.service;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardComment;
import bitcamp.myapp.vo.BoardPhoto;

import java.util.List;

public interface BoardCommentService {
    int add(BoardComment boardComment) throws Exception;
    List<BoardComment> list(int boardNo) throws Exception;
    BoardComment get(int boardNo) throws Exception;
    int update(BoardComment boardComment) throws Exception;
    int delete(int boardNo) throws Exception;
}