package service;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordService {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin@example.com", "admin");
        users.put("user@example.com", "user1");
    }

    public boolean sendResetLink(String email) {
        // Kiểm tra xem email có trong hệ thống không
        if (!users.containsKey(email)) {
            return false;
        }

        // Giả định rằng email reset mật khẩu đã được gửi
        System.out.println("Gửi email đặt lại mật khẩu tới: " + email);
        return true;
    }
}
