package bitcamp.myapp.dto;



public class MessageDto {
    private String to;

    // 기본 생성자

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    // 빌더 메서드
    public static MessageDtoBuilder builder() {
        return new MessageDtoBuilder();
    }

    public static class MessageDtoBuilder {
        private String to;

        public MessageDtoBuilder to(String to) {
            this.to = to;
            return this;
        }

        public MessageDto build() {
            MessageDto messageDto = new MessageDto();
            messageDto.setTo(this.to);
            return messageDto;
        }
    }
}