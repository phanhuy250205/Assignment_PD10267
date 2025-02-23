package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UpdateProfileService;

public class UpdateProfileTest {
    private UpdateProfileService updateProfileService;

    @BeforeEach
    public void setUp() {
        updateProfileService = new UpdateProfileService();  // Khởi tạo service trước mỗi test
    }

    @Test
    public void testUpdateProfileSuccess() {
        boolean result = updateProfileService.updateProfile("admin", "Admin User", "admin@example.com");
        System.out.println("Test Cập nhật thông tin thành công - Kết quả thực tế: " + result);
        assertTrue(result, "❌ Lỗi: Cập nhật thông tin thành công nhưng bị lỗi!");
    }

    @Test
    public void testUpdateProfileFail_EmptyName() {
        boolean result = updateProfileService.updateProfile("admin", "", "admin@example.com");
        System.out.println("Test Tên rỗng - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Tên rỗng nhưng vẫn cập nhật được!");
    }

    @Test
    public void testUpdateProfileFail_InvalidEmail() {
        boolean result = updateProfileService.updateProfile("admin", "Admin User", "invalid-email");
        System.out.println("Test Email không hợp lệ - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Email sai định dạng nhưng vẫn cập nhật được!");
    }

    @Test
    public void testUpdateProfileFail_UserNotFound() {
        boolean result = updateProfileService.updateProfile("nonexistentUser", "New User", "newuser@example.com");
        System.out.println("Test Tài khoản không tồn tại - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Tài khoản không tồn tại nhưng vẫn cập nhật được thông tin!");
    }

    @Test
    public void testUpdateProfileFail_EmptyEmail() {
        boolean result = updateProfileService.updateProfile("admin", "Admin User", "");
        System.out.println("Test Email rỗng - Kết quả thực tế: " + result);
        assertFalse(result, "❌ Lỗi: Email rỗng nhưng vẫn cập nhật được!");
    }
}
