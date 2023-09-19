package bitcamp.myapp.service;

import bitcamp.myapp.vo.BoardComment;

import java.util.List;

public interface BoardCommentService {
    int add(BoardComment boardComment) throws Exception;
    List<BoardComment> list(int boardNo) throws Exception;
    BoardComment get(int no, int boardNo) throws Exception;
    int update(BoardComment boardComment) throws Exception;
    int delete(int no, int boardNo) throws Exception;
}