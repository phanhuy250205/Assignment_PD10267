package service;

import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123456");
        users.put("user1", "password123");
    }

    public String register(String username, String password, String confirmPassword, String email) {
        // Kiểm tra username có bị rỗng không
        if (username == null || username.trim().isEmpty()) {
            return "Tên tài khoản không được để trống!";
        }

        // Kiểm tra username có tồn tại không
        if (users.containsKey(username)) {
            return "Tài khoản đã tồn tại!";
        }

        // Kiểm tra mật khẩu có bị rỗng không
        if (password == null || password.trim().isEmpty()) {
            return "Mật khẩu không được để trống!";
        }

        // Kiểm tra mật khẩu có khớp không
        if (!password.equals(confirmPassword)) {
            return "Mật khẩu xác nhận không khớp!";
        }

        // Kiểm tra độ dài mật khẩu (ít nhất 6 ký tự)
        if (password.length() < 6) {
            return "Mật khẩu phải có ít nhất 6 ký tự!";
        }

        // Kiểm tra email có hợp lệ không (giả định email hợp lệ nếu chứa '@' và '.')
        if (email == null || !email.contains("@") || !email.contains(".")) {
            return "Email không hợp lệ!";
        }

        // Đăng ký thành công
        users.put(username, password);
        return "Đăng ký thành công!";
    }
}
