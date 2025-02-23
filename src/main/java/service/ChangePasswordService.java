package service;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordService {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123456");
        users.put("user1", "password123");
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // Kiểm tra xem tài khoản có tồn tại không
        if (!users.containsKey(username)) {
            return false;
        }

        // Kiểm tra mật khẩu cũ có đúng không
        if (!users.get(username).equals(oldPassword)) {
            return false;
        }

        // Kiểm tra mật khẩu mới có bị rỗng không
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        // **Kiểm tra nếu mật khẩu mới trùng với mật khẩu cũ**
        if (newPassword.equals(oldPassword)) {
            return false; // Không cho phép đổi mật khẩu trùng với mật khẩu cũ
        }

        // Đổi mật khẩu thành công
        users.put(username, newPassword);
        return true;
    }
}
