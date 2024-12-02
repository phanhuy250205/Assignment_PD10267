package servlet;

import Dao.CommentDAO;
import Dao.UserDao;
import Dao.VideosDao;
import entity.Comment;
import entity.Usersentity;
import entity.VideoEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/commentServlet")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();
    private VideosDao videosDao = new VideosDao();
    private CommentDAO commentDao = new CommentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("listComments".equals(action)) {
            listComments(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addComment".equals(action)) {
            addComment(req, resp);
        }
    }

    private void addComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if user is logged in
        Object sessionUserId = req.getSession().getAttribute("userId");
        if (sessionUserId == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        Long userId = Long.parseLong(sessionUserId.toString());

        // Get parameters
        String videoIdStr = req.getParameter("videoId");
        String commentContent = req.getParameter("content");

        // Validate parameters
        if (videoIdStr == null || videoIdStr.isEmpty() || commentContent == null || commentContent.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }

        try {
            Long videoId = Long.parseLong(videoIdStr);

            // Verify user and video exist
            Usersentity user = userDao.findById(String.valueOf(userId));
            VideoEntity video = videosDao.findById(String.valueOf(videoId));

            if (user == null || video == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User or video not found");
                return;
            }

            // Create and save the comment
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setVideo(video);
            comment.setCommentText(commentContent);
            comment.setCommentDate(new Date());

            commentDao.create(comment);

            // Redirect back to video detail page
            resp.sendRedirect("commentServlet?action=listComments&videoId=" + videoId);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid video ID format");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding comment");
        }
    }

    private void listComments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String videoIdStr = req.getParameter("videoId");
            Long videoId = Long.parseLong(videoIdStr);

            // Debug để kiểm tra videoId
            System.out.println("VideoID received: " + videoId);

            List<Comment> comments = commentDao.getCommentsForVideo(videoId);

            // Debug để kiểm tra comments
            System.out.println("Comments retrieved: " + (comments != null ? comments.size() : "null"));
            if (comments != null) {
                for (Comment c : comments) {
                    System.out.println("Comment text: " + c.getCommentText());
                    System.out.println("User: " + c.getUser().getFullname());
                }
            }

            // Kiểm tra comments trước khi set
            if (comments == null) {
                comments = new ArrayList<>();
            }

            req.setAttribute("comments", comments);
            System.out.println("Comments set to request attribute");

            // Forward
            req.getRequestDispatcher("detail.jsp").forward(req, resp);

        } catch (Exception e) {
            System.err.println("Error in listComments: " + e.getMessage());
            e.printStackTrace();
        }
    }
}