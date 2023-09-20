package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberDao {
  int insert(Member member);
  List<Member> findAll();
  Member findBy(int no);
  Member findByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);
  int update(Member member);
  int delete(int no);
  int countByTel(@Param("phoneNumber") String phoneNumber);
}
