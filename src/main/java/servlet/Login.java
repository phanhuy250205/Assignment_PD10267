package servlet;

import Dao.UserDao;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

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
        // Hiển thị trang login
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy thông tin từ form
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            // Kiểm tra dữ liệu đầu vào
            if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
                req.setAttribute("error", "Username and password are required.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Mã hóa mật khẩu người dùng nhập vào

            // Tìm người dùng trong cơ sở dữ liệu với mật khẩu đã mã hóa
            Usersentity user = userDao.findByUsernameAndPassword(username,password);

            if (user != null) {
                // Nếu đăng nhập thành công, lưu thông tin vào session
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("fullname", user.getFullname());
                boolean isAdmin = user.isAdmin();  // Giả sử `isAdmin` là phương thức trả về giá trị true/false
                session.setAttribute("isAdmin", isAdmin);

                // Chuyển hướng tới trang chủ (index.jsp)
                resp.sendRedirect(req.getContextPath() + "/index");
            } else {
                // Nếu đăng nhập không thành công
                req.setAttribute("error", "Invalid username or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có sự cố xảy ra
            req.setAttribute("error", "An error occurred. Please try again later.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}