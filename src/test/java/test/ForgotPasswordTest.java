package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ForgotPasswordService;

public class ForgotPasswordTest {
    private ForgotPasswordService forgotPasswordService;

    @BeforeEach
    public void setUp() {
        forgotPasswordService = new ForgotPasswordService();  // Khởi tạo service trước mỗi test
    }

    @Test
    public void testForgotPasswordSuccess() {
        boolean result = forgotPasswordService.sendResetLink("user@example.com");
        System.out.println("Test Gửi email đặt lại mật khẩu thành công - Kết quả thực tế: " + result);
        assertTrue(result, "❌ Lỗi: Gửi email đặt lại mật khẩu thành công nhưng bị lỗi!");
    }

    @Test
    public void testForgotPasswordFail_EmailNotFound() {
        boolean result = forgotPasswordService.sendResetLink("notexist@example.com");
        System.out.println("Test Email không tồn tại - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Email không tồn tại nhưng vẫn gửi được link đặt lại mật khẩu!");
    }

    @Test
    public void testForgotPasswordFail_InvalidEmail() {
        boolean result = forgotPasswordService.sendResetLink("invalid-email");
        System.out.println("Test Email sai định dạng - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Email sai định dạng nhưng vẫn gửi được link đặt lại mật khẩu!");
    }

    @Test
    public void testForgotPasswordFail_EmptyEmail() {
        boolean result = forgotPasswordService.sendResetLink("");
        System.out.println("Test Email rỗng - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Email rỗng nhưng vẫn gửi được link đặt lại mật khẩu!");
    }
}
