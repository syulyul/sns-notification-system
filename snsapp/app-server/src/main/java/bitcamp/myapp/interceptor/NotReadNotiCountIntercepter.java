package bitcamp.myapp.interceptor;

import bitcamp.myapp.service.NotificationService;
import bitcamp.myapp.vo.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


public class NotReadNotiCountIntercepter implements HandlerInterceptor {

  @Autowired
  NotificationService notificationService;

  {
    System.out.println("NotReadNotiCountIntercepter 생성됨!");
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    session.setAttribute("notReadNotiCount",
        notificationService.notReadNotiLogCount(
            ((Member) session.getAttribute("loginUser")).getNo()));
    return true;
  }
}
