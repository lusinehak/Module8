package business.objects;

public class Email {
    private final String receiver = "lusine-hakobian@mail.ru";
    private final String subject = "Test Email";

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        String name = receiver.split("-")[0];
        return utils.MailTemplate.generateContent(name, new User().getUserName());
    }
}
