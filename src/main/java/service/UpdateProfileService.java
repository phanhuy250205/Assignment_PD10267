package service;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileService {
    private static Map<String, UserProfile> users = new HashMap<>();

    static {
        users.put("admin", new UserProfile("admin", "Admin User", "admin@example.com"));
        users.put("user1", new UserProfile("user1", "User One", "user1@example.com"));
    }

    public boolean updateProfile(String username, String fullName, String email) {
        // Kiểm tra xem tài khoản có tồn tại không
        if (!users.containsKey(username)) {
            return false;
        }

        // Kiểm tra nếu tên trống
        if (fullName == null || fullName.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!email.contains("@")) {
            return false;
        }

        // Cập nhật thông tin
        users.put(username, new UserProfile(username, fullName, email));
        return true;
    }
}
