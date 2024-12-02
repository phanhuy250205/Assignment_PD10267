package servlet;

import Dao.UserDao;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/thongtin")
@MultipartConfig  // Cho phép tải lên tệp tin (file uploads)
public class Thaydoithongtin extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();  // Khởi tạo đối tượng UserDao để làm việc với cơ sở dữ liệu
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);  // Lấy session hiện tại (nếu có)

        if (session != null && session.getAttribute("userId") != null) {
            Long userId = (Long) session.getAttribute("userId");  // Lấy ID người dùng từ session

            // Lấy thông tin người dùng từ cơ sở dữ liệu
            Usersentity user = userDao.findById(String.valueOf(userId));

            if (user != null) {
                req.setAttribute("profileImage", user.getImage());  // Truyền ảnh vào JSP để hiển thị
                req.setAttribute("user", user);
                req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);  // Chuyển hướng đến trang chỉnh sửa thông tin
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");  // Nếu không tìm thấy người dùng, chuyển hướng về trang đăng nhập
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");  // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        // Kiểm tra các trường thông tin người dùng
        if (fullname == null || email == null || fullname.trim().isEmpty() || email.trim().isEmpty()) {
            req.setAttribute("error", "Thông tin không hợp lệ");
            req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra định dạng email
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailPattern)) {
            req.setAttribute("error", "Email không hợp lệ");
            req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            Long userId = (Long) session.getAttribute("userId");

            String newImagePath = null;
            Part imagePart = req.getPart("avatar");

            // Xử lý ảnh nếu có
            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName.replace(" ", "_");

                // Đường dẫn lưu ảnh
                String uploadDir = getServletContext().getRealPath("/assets/img");
                File uploadDirectory = new File(uploadDir);
                if (!uploadDirectory.exists()) {
                    uploadDirectory.mkdirs();
                }

                Path filePath = Paths.get(uploadDir, uniqueFileName);
                try (InputStream input = imagePart.getInputStream()) {
                    Files.copy(input, filePath);
                }

                // Thay vì lưu đường dẫn đầy đủ, chỉ lưu tên tệp ảnh
                newImagePath = uniqueFileName; // Lưu chỉ tên tệp, không cần "/assets/img/"

            }

            // Cập nhật thông tin người dùng
            boolean isUpdated = userDao.updateUserInfo(userId, fullname, email, newImagePath);
            if (isUpdated) {
                resp.sendRedirect(req.getContextPath() + "/index");  // Chuyển hướng nếu cập nhật thành công
            } else {
                req.setAttribute("error", "Cập nhật thông tin thất bại");
                req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);  // Chuyển hướng về trang chỉnh sửa nếu thất bại
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");  // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
        }
    }
}
