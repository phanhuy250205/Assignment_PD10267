package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/logout")
public class LogoutServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy session hiện tại
        HttpSession session = req.getSession(false); // false để không tạo session mới nếu session không tồn tại

        // Nếu session tồn tại, hủy session
        if (session != null) {
            session.invalidate(); // Xóa tất cả thông tin trong session
        }

        // Sau khi đăng xuất, chuyển hướng người dùng đến trang đăng nhập
        resp.sendRedirect("login.jsp");
    }
}
