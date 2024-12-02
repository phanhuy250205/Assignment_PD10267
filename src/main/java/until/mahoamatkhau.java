package until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class mahoamatkhau {
    // Hàm mã hóa mật khẩu bằng SHA-256
    public String hashPasswordWithMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        // MD5 sẽ tạo ra chuỗi có độ dài 32 ký tự
        return hexString.toString();
    }

}
