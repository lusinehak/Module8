package business.objects;

public class Email {
    private final String receiver = "lusine-hakobian@mail.ru";
    private final String subject = "Test Email";
    private String content = "This is a test email";

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
