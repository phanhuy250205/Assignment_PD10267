package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.RegisterService;

public class RegisterTest {
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        registerService = new RegisterService();
    }

    @Test
    public void testRegisterSuccess() {
        String result = registerService.register("newuser", "password123", "password123", "user@example.com");
        System.out.println("Test Đăng ký thành công - Kết quả thực tế: " + result);
        assertEquals("Đăng ký thành công!", result, "❌ Lỗi: Đăng ký thành công nhưng bị lỗi!");
    }

    @Test
    public void testRegisterFail_UserExists() {
        String result = registerService.register("user1", "password123", "password123", "admin@example.com");
        System.out.println("Test Tài khoản đã tồn tại - Kết quả thực tế: " + result);
        assertEquals("Tài khoản đã tồn tại!", result, "❌ Lỗi: Tài khoản đã tồn tại nhưng vẫn đăng ký được!");
    }

    @Test
    public void testRegisterFail_EmptyUsername() {
        String result = registerService.register("", "password123", "password123", "user@example.com");
        System.out.println("Test Tên tài khoản trống - Kết quả thực tế: " + result);
        assertEquals("Tên tài khoản không được để trống!", result, "❌ Lỗi: Tên tài khoản trống nhưng vẫn đăng ký được!");
    }

    @Test
    public void testRegisterFail_PasswordMismatch() {
        String result = registerService.register("newuser", "password123", "password456", "user@example.com");
        System.out.println("Test Mật khẩu không khớp - Kết quả thực tế: " + result);
        assertEquals("Mật khẩu xác nhận không khớp!", result, "❌ Lỗi: Mật khẩu không khớp nhưng vẫn đăng ký được!");
    }

    @Test
    public void testRegisterFail_ShortPassword() {
        String result = registerService.register("newuser", "123", "123", "user@example.com");
        System.out.println("Test Mật khẩu quá ngắn - Kết quả thực tế: " + result);
        assertEquals("Mật khẩu phải có ít nhất 6 ký tự!", result, "❌ Lỗi: Mật khẩu quá ngắn nhưng vẫn đăng ký được!");
    }

    @Test
    public void testRegisterFail_InvalidEmail() {
        String result = registerService.register("newuser", "password123", "password123", "invalid-email");
        System.out.println("Test Email không hợp lệ - Kết quả thực tế: " + result);
        assertEquals("Email không hợp lệ!", result, "❌ Lỗi: Email không hợp lệ nhưng vẫn đăng ký được!");
    }
}
