package bitcamp.myapp.service;

import bitcamp.myapp.vo.GuestBook;
import bitcamp.myapp.vo.Member;
import java.util.List;

public interface GuestBookService {

  int add(GuestBook guestBook) throws Exception;

  List<GuestBook> list(int no, int limit, int page) throws Exception;

  int getTotalCount(int memNo) throws Exception;

  GuestBook get(int guestBookNo) throws Exception;

  int delete(int guestBookNo) throws Exception;

  int increaseLikes(int guestBookNo) throws Exception; // guestbook 테이블 좋아요+1

  int decreaseLikes(int guestBookNo) throws Exception; // guestbook 테이블 좋아요-1

  int like(Member member, GuestBook guestBook) throws Exception; // guestbook_like 테이블 좋아요정보 추가

  int unlike(Member member, GuestBook guestBook) throws Exception; // guestbook_like 테이블 좋아요정보 삭제

  List<Integer> likelist(int memberNo) throws Exception;

  List<String> guestBooklikelist(int guestBookNo) throws Exception;

  String getMemberNickByNo(int memberNo) throws Exception;
}
