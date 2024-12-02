package servlet;

import Dao.VideosDao;
import entity.VideoEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/videoDetail")
public class VideoDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideosDao videosDao = new VideosDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id từ request (có thể lấy từ tham số URL hoặc form)
        String videoId = request.getParameter("id");

        // Kiểm tra nếu videoId không có
        if (videoId == null || videoId.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video ID is required");
            return;
        }

        try {
            // Truy vấn video từ cơ sở dữ liệu theo videoId
            VideoEntity video = videosDao.findById(videoId); // Giả sử bạn có phương thức findById trong DAO

            // Kiểm tra nếu không tìm thấy video
            if (video == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
                return;
            }

            // Đưa dữ liệu video vào request để truyền tới JSP
            request.setAttribute("video", video);

            // Chuyển hướng đến trang chi tiết video (videoDetail.jsp)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");  // Đảm bảo đường dẫn đúng
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();  // Có thể thay thế bằng logging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving video details");
        }
    }
}
