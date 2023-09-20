package bitcamp.myapp.dto;

import java.util.List;

public class SmsRequestDto {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessageDto> messages;

    // 생성자
    public SmsRequestDto() {
    }

    public SmsRequestDto(String type, String contentType, String countryCode, String from, String content, List<MessageDto> messages) {
        this.type = type;
        this.contentType = contentType;
        this.countryCode = countryCode;
        this.from = from;
        this.content = content;
        this.messages = messages;
    }

    // 게터와 세터 메서드
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    // 빌더 패턴
    public static SmsRequestDtoBuilder builder() {
        return new SmsRequestDtoBuilder();
    }

    public static class SmsRequestDtoBuilder {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<MessageDto> messages;

        public SmsRequestDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SmsRequestDtoBuilder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public SmsRequestDtoBuilder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public SmsRequestDtoBuilder from(String from) {
            this.from = from;
            return this;
        }

        public SmsRequestDtoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public SmsRequestDtoBuilder messages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }

        public SmsRequestDto build() {
            return new SmsRequestDto(type, contentType, countryCode, from, content, messages);
        }
    }
}