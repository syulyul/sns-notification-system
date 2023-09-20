package bitcamp.myapp.service;

import bitcamp.myapp.controller.NaverSensV2;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class SmsService {

    private final MemberDao memberDao; // MemberDao 주입

    public SmsService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 핸드폰 번호로 가입된 회원 수 조회
    public Member memberTelCount(String phoneNumber) {
        return memberDao.countByTel(phoneNumber);
    }
    // 문자 메시지 발송과 랜덤 코드 생성을 한 번에 처리
    public String sendRandomMessage(String phoneNumber) {
        NaverSensV2 message = new NaverSensV2();
        Random rand = new Random();
        // StringBuilder numStr = new StringBuilder();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
            //numStr.append(ran);
        }
        message.sendMsg(phoneNumber, numStr);

        return numStr;
    }


    // 문자 코드 검증
    public boolean verifyCode(String code, HttpSession session) {
        String rand = (String) session.getAttribute("rand");
        if (rand != null && rand.equals(code)) {
            // 코드 일치, 세션에서 코드 제거
            session.removeAttribute("rand");
            return true;
        }
        // 코드 불일치
        return false;
    }
}