package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChangePasswordService;

public class ChangePasswordTest {
    private ChangePasswordService changePasswordService;

    @BeforeEach
    public void setUp() {
        changePasswordService = new ChangePasswordService();  // Khởi tạo service trước mỗi test
    }

    @Test
    public void testChangePasswordSuccess() {
        boolean result = changePasswordService.changePassword("admin", "123456", "newpassword");
        System.out.println("Test Đổi mật khẩu thành công - Kết quả thực tế: " + result);
        assertTrue(result, "❌ Lỗi: Tài khoản và mật khẩu hợp lệ nhưng đổi không thành công!");
    }

    @Test
    public void testChangePasswordFail_WrongOldPassword() {
        boolean result = changePasswordService.changePassword("admin", "wrongpass", "newpassword");
        System.out.println("Test Sai mật khẩu cũ - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Mật khẩu cũ sai nhưng vẫn đổi được!");
    }

    @Test
    public void testChangePasswordFail_EmptyNewPassword() {
        boolean result = changePasswordService.changePassword("admin", "123456", "");
        System.out.println("Test Mật khẩu mới rỗng - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Mật khẩu mới rỗng nhưng vẫn đổi được!");
    }

    @Test
    public void testChangePasswordFail_UserNotFound() {
        boolean result = changePasswordService.changePassword("nonexistentUser", "123456", "newpassword");
        System.out.println("Test Tài khoản không tồn tại - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Tài khoản không tồn tại nhưng vẫn đổi được mật khẩu!");
    }

    @Test
    public void testChangePasswordFail_SameAsOldPassword() {
        boolean result = changePasswordService.changePassword("admin", "123456", "123456");
        System.out.println("Test Mật khẩu mới trùng với mật khẩu cũ - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Mật khẩu mới trùng với mật khẩu cũ nhưng vẫn đổi được!");
    }
}
