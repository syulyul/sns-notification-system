package bitcamp.myapp.service;

import bitcamp.myapp.dao.NotificationDao;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultNotificationService implements NotificationService {

  NotificationDao notificationDao;

  {
    System.out.println("DefaultNotificationService 생성됨!");
  }

  public DefaultNotificationService(NotificationDao notificationDao) {
    this.notificationDao = notificationDao;
  }

  @Transactional
  @Override
  public int add(NotiLog notiLog) throws Exception {
    return notificationDao.insert(notiLog);
  }

  @Override
  public List<NotiLog> notiLogList(int memberNo) throws Exception {
    return notificationDao.findAllNotiLog(memberNo);
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
