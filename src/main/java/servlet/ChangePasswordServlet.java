package servlet;

import Dao.UserDao;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userDao = new UserDao();  // Khởi tạo đối tượng UserDao
        } catch (Exception e) {
            throw new ServletException("Error initializing UserDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Hiển thị trang thay đổi mật khẩu
        req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy email từ request
            String email = req.getParameter("email");

            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Email is required.");
                req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
                return;
            }

            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");
            String confirmPassword = req.getParameter("confirmPassword");

            // Kiểm tra dữ liệu đầu vào
            if (oldPassword == null || newPassword == null || confirmPassword == null ||
                    oldPassword.trim().isEmpty() || newPassword.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                req.setAttribute("error", "All fields are required.");
                req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                req.setAttribute("error", "New password and confirm password do not match.");
                req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
                return;
            }

            // Gọi UserDao để thay đổi mật khẩu
            boolean isPasswordChanged = userDao.changePassword(email, oldPassword, newPassword);

            if (isPasswordChanged) {
                // Thông báo thành công và chuyển hướng tới trang chủ
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("error", "Old password is incorrect or email not found.");
                req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred. Please try again later.");
            req.getRequestDispatcher("/doimatkhau.jsp").forward(req, resp);
        }
    }
}
