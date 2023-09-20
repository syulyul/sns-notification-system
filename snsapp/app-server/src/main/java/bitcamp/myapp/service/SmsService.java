package bitcamp.myapp.service;

import bitcamp.myapp.NcsConfig;
import bitcamp.myapp.dto.MessageDto;
import bitcamp.myapp.dto.SmsRequestDto;
import bitcamp.myapp.dto.SmsResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@PropertySource("ncs.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class SmsService {

        private final NcsConfig ncsConfig; // 생성자 주입으로 NcsConfig 의존성 주입
        private final String smsConfirmNum = createSmsKey();

        public String getSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
                String space = " ";
                String newLine = "\n";
                String method = "POST";
                String url = "/sms/v2/services/"+ ncsConfig.getServiceId() + "/messages";
                String accessKey = ncsConfig.getAccessKey();
                String secretKey = ncsConfig.getSecretKey();

                String message = new StringBuilder()
                        .append(method)
                        .append(space)
                        .append(url)
                        .append(newLine)
                        .append(time)
                        .append(newLine)
                        .append(accessKey)
                        .toString();

                SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(signingKey);

                byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
                String encodeBase64String = Base64.encodeBase64String(rawHmac);

                return encodeBase64String;
        }

        public SmsResponseDto sendSms(MessageDto messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
                String time = Long.toString(System.currentTimeMillis());
                String accessKey = ncsConfig.getAccessKey(); // accessKey 추가

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("x-ncp-apigw-timestamp", time);
                headers.set("x-ncp-iam-access-key", accessKey); // 수정
                headers.set("x-ncp-apigw-signature-v2", getSignature(time));

                List<MessageDto> messages = new ArrayList<>();
                messages.add(messageDto);

                SmsRequestDto request = SmsRequestDto.builder()
                        .type("SMS")
                        .contentType("COMM")
                        .countryCode("82")
                        .from(ncsConfig.getPhone()) // 수정
                        .content("[서비스명 테스트닷] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
                        .messages(messages)
                        .build();

                // 쌓은 바디를 json 형태로 반환
                ObjectMapper objectMapper = new ObjectMapper();
                String body = objectMapper.writeValueAsString(request);
                // jsonBody와 헤더 조립
                HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                // restTemplate로 post 요청 보내고 오류가 없으면 202 코드 반환
                SmsResponseDto smsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ ncsConfig.getServiceId() +"/messages"), httpBody, SmsResponseDto.class);
                SmsResponseDto responseDto = SmsResponseDto.builder()
                        .requestId("12345")
                        .requestTime(LocalDateTime.now())
                        .statusCode("200")
                        .statusName("OK")
                        .smsConfirmNum(smsConfirmNum)
                        .build();                // redisUtil.setDataExpire(smsConfirmNum, messageDto.getTo(), 60 * 3L); // 유효시간 3분
                return smsResponseDto;
        }

        // 인증코드 만들기
        public static String createSmsKey() {
                StringBuilder key = new StringBuilder();
                Random rnd = new Random();

                for (int i = 0; i < 5; i++) { // 인증코드 5자리
                        key.append((rnd.nextInt(10)));
                }
                return key.toString();
        }
}