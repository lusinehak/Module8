package utils;

public class MailTemplate {
    private static String template = "Hello %s,\n\n" +
            "This is a test email send from %s account.\n" +
            "You can ignore it.";

    public static String generateContent(String receiver, String sender) {
        String content = String.format(template, receiver, sender);
        return content;
    }
}
