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
    private ShareDao shareDao;
    private VideosDao videosDao;

    @Override
    public void init() throws ServletException {
        shareDao = new ShareDao();
        videosDao = new VideosDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy `videoId` từ request
            String videoIdParam = request.getParameter("videoId");
            if (videoIdParam != null) {
                int videoId = Integer.parseInt(videoIdParam);

                // Gọi DAO để tăng lượt chia sẻ
                videosDao.incrementShareCount(videoId);

                // Redirect đến trang hiển thị chia sẻ
                response.sendRedirect(request.getContextPath() + "/chiase.jsp?videoId=" + videoId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video ID");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid video ID format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing share request");
        }
    }
}
