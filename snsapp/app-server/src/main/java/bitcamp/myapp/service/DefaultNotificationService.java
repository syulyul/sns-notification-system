package bitcamp.myapp.service;

import bitcamp.myapp.dao.NotificationDao;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultNotificationService implements NotificationService {

  @Autowired
  ServletContext context;
  @Autowired
  NotificationDao notificationDao;

  {
    System.out.println("DefaultNotificationService 생성됨!");
  }

  @Transactional
  @Override
  public int add(NotiLog notiLog) throws Exception {
    String key = "notReadNotiCount" + notiLog.getMemberNo();
    try {
      context.setAttribute(key, (Integer) context.getAttribute(key) + 1);
    } catch (Exception e) {
      // 알림을 받을 유저가 로그인되어 있지 않을 경우 context에 key가 없어 발생하는 오류 처리
    }
    return notificationDao.insert(notiLog);
  }

  @Override
  public List<NotiLog> notiLogList(int memberNo, int limit, int page) throws Exception {
    return notificationDao.findAllNotiLog(memberNo, limit, limit * (page - 1));
  }

  @Override
  public int notiLogCount(int memberNo) throws Exception {
    return notificationDao.getNotiLogCount(memberNo);
  }

  @Override
  public int notReadNotiLogCount(int memberNo) throws Exception {
    return notificationDao.getNotReadNotiLogCount(memberNo);
  }

  @Override
  public NotiLog getNotiLog(int notiNo) throws Exception {
    return notificationDao.findBy(notiNo);
  }

  @Transactional
  @Override
  public int updateState(int notiNo, int notiState) throws Exception {
    return notificationDao.updateState(notiNo, notiState);
  }

  @Override
  public int updateAllState(int memberNo, int notiState) {
    return notificationDao.updateAllState(memberNo, notiState);
  }

  @Override
  public List<NotiType> notiTypeList() throws Exception {
    return notificationDao.findAllNotiType();
  }

  @Override
  public String getNotiTypeName(int notiTypeNo) {
    return notificationDao.findNotiTypeName(notiTypeNo);
  }
}
