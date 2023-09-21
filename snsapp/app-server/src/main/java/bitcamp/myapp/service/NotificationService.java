package bitcamp.myapp.service;

import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;

public interface NotificationService {

  int add(NotiLog notiLog) throws Exception;

  List<NotiLog> notiLogList(int memberNo) throws Exception;

  List<NotiLog> notReadNotiLogList(int memberNo) throws Exception;

  NotiLog getNotiLog(int notiNo) throws Exception;

  int updateState(int notiNo, int notiState) throws Exception;

  int updateAllState(int memberNo, int notiState);

  List<NotiType> notiTypeList() throws Exception;

  String getNotiTypeName(int notiTypeNo);
}
