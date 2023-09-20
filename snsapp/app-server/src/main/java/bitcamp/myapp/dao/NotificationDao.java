package bitcamp.myapp.dao;

import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NotificationDao {

  int insert(NotiLog noti);

  List<NotiLog> findAllNotiLog(int memberNo);

  NotiLog findBy(int notiNo);

  int updateState(@Param("notiNo") int notiNo, @Param("notiState") int notiState);

  int updateAllState(@Param("memberNo") int memberNo, @Param("notiState") int notiState);

  List<NotiType> findAllNotiType();

  String findNotiTypeName(int notiTypeNo);
}
