package servlet;

import Dao.UserDao;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao(); // Khởi tạo UserDao
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        // Kiểm tra nếu tham số action là "delete" thì thực hiện xóa người dùng
        if ("delete".equals(action)) {
            try {
                // Lấy userId từ tham số yêu cầu (dùng POST để lấy tham số)
                String userIdParam = req.getParameter("userId");

                // Kiểm tra nếu userId không phải là null và là một số hợp lệ
                if (userIdParam != null && !userIdParam.isEmpty()) {
                    int userId = Integer.parseInt(userIdParam); // Chuyển đổi sang kiểu int

                    // Gọi phương thức delete từ UserDao để xóa người dùng
                    userDao.deleteById(userId);

                    // Sau khi xóa, chuyển hướng lại tới trang danh sách người dùng
                    resp.sendRedirect("listUsers"); // Điều này sẽ gọi lại phương thức doGet để tải lại danh sách người dùng
                    return;  // Dừng xử lý tiếp tục
                } else {
                    req.setAttribute("error", "User ID không hợp lệ");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Lỗi khi xóa người dùng");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý hiển thị danh sách người dùng
        try {
            List<Usersentity> userlist = userDao.findAll();
            req.setAttribute("userlist", userlist);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi danh sách người dùng");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
