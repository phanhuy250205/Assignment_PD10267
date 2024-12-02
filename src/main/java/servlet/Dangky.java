package servlet;

import Dao.UserDao;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import until.mahoamatkhau;

@WebServlet("/dangky")
@MultipartConfig  // Chú thích này để kích hoạt upload file
public class Dangky extends HttpServlet {




    // Hàm upload ảnh
    private String uploadFile(HttpServletRequest req) throws IOException, ServletException {
        Part filePart = req.getPart("avatar"); // Tên input file trong form
        if (filePart != null && filePart.getSize() > 0) {
            // Kiểm tra kích thước tối đa của tệp (ví dụ: 5MB)
            long maxSize = 5 * 1024 * 1024; // 5MB
            if (filePart.getSize() > maxSize) {
                throw new ServletException("File quá lớn, kích thước tối đa là 5MB");
            }

            // Kiểm tra loại tệp (chỉ chấp nhận JPEG, PNG)
            String[] allowedTypes = {"image/jpeg", "image/png"};
            String contentType = filePart.getContentType();
            boolean isValidType = Arrays.stream(allowedTypes).anyMatch(contentType::equals);
            if (!isValidType) {
                throw new ServletException("Loại tệp không hợp lệ, chỉ chấp nhận JPEG và PNG");
            }

            // Lấy tên file gốc
            String originalFileName = filePart.getSubmittedFileName();

            // Tạo tên file duy nhất
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

            // Đường dẫn lưu file
            String uploadPath = req.getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            // Lưu file vào thư mục
            filePart.write(uploadPath + File.separator + uniqueFileName);

            // Trả về tên file đã upload, ví dụ như "/assets/img/{uniqueFileName}"
            return "assets" + File.separator + "img" + File.separator + uniqueFileName;
        }
        return null; // Trả về null nếu không có ảnh
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        String userRole = req.getParameter("userRole"); // Lấy vai trò từ dropdown

        // Kiểm tra mật khẩu
        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu không khớp. Vui lòng thử lại.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        mahoamatkhau hashUtil = new mahoamatkhau();
        // Mã hóa mật khẩu
        try {
            password = hashUtil.hashPasswordWithMD5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi khi mã hóa mật khẩu.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Xác định vai trò (quản trị viên hoặc người dùng thông thường)
        boolean isAdmin = "admin".equals(userRole);

        // Xử lý upload ảnh
        String imagePath = null;
        try {
            imagePath = uploadFile(req);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi khi upload ảnh.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Tạo đối tượng người dùng

        Usersentity use = new Usersentity();
        use.setFullname(fullname);
        use.setEmail(email);
        use.setPassword(password);
        use.setAdmin(isAdmin);
        use.setImage(imagePath);  // Lưu tên ảnh

        try {
            // Lưu thông tin người dùng vào cơ sở dữ liệu
            UserDao userDao = new UserDao();
            userDao.create(use);

            req.setAttribute("message", "Đăng ký thành công.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi khi đăng ký. Vui lòng thử lại.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
