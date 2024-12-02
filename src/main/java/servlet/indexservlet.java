package servlet;

import Dao.VideosDao;
import entity.VideoEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class indexservlet extends HttpServlet {
    private VideosDao videoDao = new VideosDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy session của người dùng
        HttpSession session = req.getSession();

        // Kiểm tra xem danh sách video đã lưu trong session hay chưa
        List<VideoEntity> activeVideos = (List<VideoEntity>) session.getAttribute("videos");

        String pageParam = req.getParameter("page");


        if (activeVideos == null) {
            // Nếu chưa có danh sách video, lấy từ cơ sở dữ liệu và lưu vào session
            activeVideos = videoDao.findActiveVideos();
            session.setAttribute("videos", activeVideos);
        }

        // Đẩy danh sách video vào request để hiển thị trên JSP
        req.setAttribute("videos", activeVideos);

        // Forward đến trang index.jsp
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
