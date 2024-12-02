package servlet;

import Dao.FavoriteDao;
import Dao.UserDao;
import Dao.VideosDao;
import entity.Favoritesentity;
import entity.Usersentity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorites")
public class FavoriteServlet extends HttpServlet {
    private FavoriteDao favoriteDao = new FavoriteDao();
    private UserDao userDao = new UserDao();
    private VideosDao videosDao = new VideosDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý khi người dùng gửi yêu cầu POST (ví dụ, khi nhấn nút Unfavorite)
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            deleteFavorite(req, resp); // Xóa yêu thích
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action in POST request");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("list".equals(action)) {
            listFavorites(req, resp); // Hiển thị danh sách yêu thích
        } else if ("delete".equals(action)) {
            deleteFavorite(req, resp); // Xóa yêu thích
        } else {
            resp.sendRedirect("favorites?action=list"); // Điều hướng lại nếu không có hành động rõ ràng
        }
    }

    private void listFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy userId từ session
        Long userId = (Long) req.getSession().getAttribute("userId");

        if (userId != null) {
            Usersentity user = userDao.findById(String.valueOf(userId));
            if (user != null) {
                // Lấy danh sách yêu thích của người dùng
                List<Favoritesentity> favorites = favoriteDao.findFavoritesByUser(user);
                req.setAttribute("favorites", favorites);  // Truyền dữ liệu yêu thích tới view
                req.getRequestDispatcher("/favories.jsp").forward(req, resp);  // Hiển thị trang favorites.jsp
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } else {
            resp.sendRedirect("login.jsp");  // Nếu không có userId trong session, chuyển hướng tới trang login
        }
    }

    private void deleteFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy favoriteId và userId từ request và session
        String favoriteIdStr = req.getParameter("id");
        Long userId = (Long) req.getSession().getAttribute("userId");

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (userId == null) {
            resp.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập
            return;
        }

        // Kiểm tra nếu id yêu thích hợp lệ
        if (favoriteIdStr != null && !favoriteIdStr.isEmpty()) {
            try {
                Long favoriteId = Long.parseLong(favoriteIdStr); // Chuyển đổi favoriteId từ String sang Long

                // Tìm đối tượng yêu thích trong cơ sở dữ liệu
                Favoritesentity favorite = favoriteDao.findById(String.valueOf(favoriteId));

                // Kiểm tra xem yêu thích có tồn tại và thuộc quyền của người dùng hay không
                if (favorite == null || !favorite.getUser().getId().equals(userId)) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not allowed to delete this favorite");
                    return;
                }

                // Xóa yêu thích khỏi cơ sở dữ liệu
                boolean deleted = favoriteDao.deleteById(Math.toIntExact(favoriteId));

                if (deleted) {
                    // Thành công, chuyển hướng về trang danh sách yêu thích
                    req.getSession().setAttribute("message", "Video đã được xóa khỏi danh sách yêu thích!");
                    resp.sendRedirect("favorites?action=list"); // Chuyển hướng về danh sách yêu thích
                } else {
                    // Nếu không xóa được, gửi lỗi
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Favorite not found");
                }
            } catch (NumberFormatException e) {
                // Nếu ID không hợp lệ
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid favorite ID format");
            } catch (Exception e) {
                // Nếu có lỗi bất ngờ (ví dụ cơ sở dữ liệu, kết nối)
                e.printStackTrace(); // Ghi log chi tiết
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the favorite");
            }
        } else {
            // Nếu ID yêu thích không hợp lệ hoặc không có
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid favorite ID");
        }
    }


}