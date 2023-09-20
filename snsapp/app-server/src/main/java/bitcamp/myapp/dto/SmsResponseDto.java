package bitcamp.myapp.dto;

import java.time.LocalDateTime;

public class SmsResponseDto {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    private String smsConfirmNum;

    // 생성자
    public SmsResponseDto() {
    }

    public SmsResponseDto(String requestId, LocalDateTime requestTime, String statusCode, String statusName, String smsConfirmNum) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.smsConfirmNum = smsConfirmNum;
    }

    // 게터와 세터 메서드
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSmsConfirmNum() {
        return smsConfirmNum;
    }

    public void setSmsConfirmNum(String smsConfirmNum) {
        this.smsConfirmNum = smsConfirmNum;
    }

    // 빌더 패턴
    public static SmsResponseDtoBuilder builder() {
        return new SmsResponseDtoBuilder();
    }

    public static class SmsResponseDtoBuilder {
        private String requestId;
        private LocalDateTime requestTime;
        private String statusCode;
        private String statusName;
        private String smsConfirmNum;

        public SmsResponseDtoBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public SmsResponseDtoBuilder requestTime(LocalDateTime requestTime) {
            this.requestTime = requestTime;
            return this;
        }

        public SmsResponseDtoBuilder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public SmsResponseDtoBuilder statusName(String statusName) {
            this.statusName = statusName;
            return this;
        }

        public SmsResponseDtoBuilder smsConfirmNum(String smsConfirmNum) {
            this.smsConfirmNum = smsConfirmNum;
            return this;
        }

        public SmsResponseDto build() {
            return new SmsResponseDto(requestId, requestTime, statusCode, statusName, smsConfirmNum);
        }
    }
}