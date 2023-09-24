package bitcamp.myapp.dao;

import bitcamp.myapp.vo.GuestBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuestBookDao {
  int insert(GuestBook guestBook);
  List<GuestBook> findAll(@Param("no") int no);
  GuestBook findBy(int no);
  int delete(int no);

  int updateLike(int guestBookNo); // 좋아요 수 1 증가
  int cancelLike(int guestBookNo); // 좋아요 수 1 감소
  int insertLike(@Param("memberNo") int memberNo, @Param("guestBookNo") int guestBookNo); // 좋아요 테이블에 추가
  int deleteLike(@Param("memberNo") int memberNo, @Param("guestBookNo") int guestBookNo); // 좋아요 테이블에 삭제
  int deleteLikes(int guestBookNo); // 게시판 삭제시 좋아요도 삭제
  List<Integer> findLikeByMno(int memberNo);
  List<String> findLikeByBno(int guestBookNo);
}