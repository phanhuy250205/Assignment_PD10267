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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
   private  static Map<String, String> users = new HashMap<>();
    static {
        users.put("admin","123456");
        users.put("user1","password123");

    }

    public  boolean authenticate(String username, String password) {
        if (username == null || password == null  || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return users.containsKey(username) && users.get(username).equals(password);
    }

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

            // Tìm người dùng trong cơ sở dữ liệu
            Usersentity user = userDao.findByUsernameAndPassword(username, password);

            if (user != null) {
                // Nếu đăng nhập thành công, lưu thông tin vào session
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("fullname", user.getFullname());
                boolean isAdmin = user.isAdmin();
                session.setAttribute("isAdmin", isAdmin);

                // Lưu thông báo vào session và chuyển hướng
                session.setAttribute("success", "Đăng nhập thành công!");
                resp.sendRedirect(req.getContextPath() + "/index.jsp");  // Chuyển hướng thay vì forward
            } else {
                // Nếu đăng nhập không thành công
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred. Please try again later.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }



}