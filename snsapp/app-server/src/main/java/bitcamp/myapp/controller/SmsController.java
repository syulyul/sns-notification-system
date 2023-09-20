package bitcamp.myapp.controller;

import bitcamp.myapp.dto.MessageDto;
import bitcamp.myapp.dto.SmsRequestDto;
import bitcamp.myapp.dto.SmsResponseDto;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.MyPageService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/find")
public class SmsController {
    @Autowired
    MemberService memberService;
    @Autowired
    MyPageService myPageService;
    @Autowired
    NcpObjectStorageService ncpObjectStorageService;

    private final SmsService smsService;
    @GetMapping("send")
    public String add() {
        return "auth/membership";
    }

    @PostMapping("send")
    public SmsResponseDto sendSms(@RequestBody MessageDto messageDto,
                                  Model model,
                                  HttpServletResponse response)
            throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponseDto responseDto = smsService.sendSms(messageDto);
        model.addAttribute("response", response);
        return responseDto;
    }
}