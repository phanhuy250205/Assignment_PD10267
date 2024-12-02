package servlet;

import Dao.ShareDao;
import Dao.VideosDao;
import entity.Sharesentity;
import entity.VideoEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShareDao shareDao = new ShareDao();
    private VideosDao videosDao = new VideosDao();

    @Override
    public void init() throws ServletException {
        super.init();
        shareDao = new ShareDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<VideoEntity> videoList = videosDao.findAll(); // Lấy danh sách video từ cơ sở dữ liệu
            request.setAttribute("sharelist", videoList); // Đưa danh sách video vào request scope
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chiase.jsp"); // Chuyển hướng đến trang JSP
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching video data");
        }
    }
}
