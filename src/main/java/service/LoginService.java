package service;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123456");
        users.put("user1", "password123");
    }

    public boolean authenticate(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
