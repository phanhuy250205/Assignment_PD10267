package until;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {
    private static final String FROM_EMAIL = "phanhuy250205@gmail.com";  // Thay thế bằng địa chỉ Gmail của bạn
    private static final String FROM_PASSWORD = "dfol lnwa pyhv kpqo";  // Mật khẩu ứng dụng của Gmail
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    public static void sendWelcomeEmail(String toEmail) {
        // Cấu hình email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Tạo session với thông tin tài khoản email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            // Tạo thông điệp email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Chào mừng bạn đến với Website của chúng tôi!");

            // Nội dung email (HTML)
            String emailContent = "<h1>Chào mừng bạn đến với website của chúng tôi!</h1>" +
                    "<p>Cảm ơn bạn đã đăng ký. Chúng tôi rất vui khi có bạn trong cộng đồng của chúng tôi.</p>";
            message.setContent(emailContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email chào mừng đã được gửi thành công.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
