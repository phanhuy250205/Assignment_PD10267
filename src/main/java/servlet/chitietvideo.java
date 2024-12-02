package servlet;

import Dao.CommentDAO;  // Import CommentDAO
import Dao.VideosDao;
import entity.Comment;
import entity.VideoEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/detail")
public class chitietvideo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideosDao videosDao;
    private CommentDAO commentDao;  // Declare commentDao

    @Override
    public void init() throws ServletException {
        super.init();
        videosDao = new VideosDao();
        commentDao = new CommentDAO();  // Initialize commentDao
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoId = req.getParameter("id");

        // Check if videoId is null or empty
        if (videoId == null || videoId.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video ID is required.");
            return;
        }

        try {
            // Fetch video by videoId
            VideoEntity video = videosDao.findById(videoId);
            if (video == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found.");
                return;
            }

            // Fetch comments for the video
            List<Comment> comments = commentDao.getCommentsForVideo(Long.parseLong(videoId));
            System.out.println("Comments found: " + (comments != null ? comments.size() : "null"));

            // Set comments in the request, if null, set an empty list
            req.setAttribute("comments", comments != null ? comments : new ArrayList<>());
            // Set the video in the request
            req.setAttribute("video", video);

            // Forward to the JSP page
            req.getRequestDispatcher("/detail.jsp").forward(req, resp);
        } catch (Exception e) {
            // Handle unexpected errors
            e.printStackTrace();  // Log error for debugging
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred.");
        }
    }
}
