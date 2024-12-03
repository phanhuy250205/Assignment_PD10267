package servlet;

import Dao.*;
import com.google.gson.Gson;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/listvideo")
public class Videolist extends HttpServlet {
    private VideosDao videosDao = new VideosDao();
    private UserDao userDao = new UserDao();
    private  FavoriteDao favoriteDao = new FavoriteDao();
    private ShareDao shareDao = new ShareDao();
    private CommentDAO commentDao = new CommentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            req.getRequestDispatcher("/quanlyvideo.jsp").forward(req, resp);  // Hiển thị trang tạo video
        } else if ("edit".equals(action)) {
            editVideo(req, resp);  // Xử lý chỉnh sửa video
        } else if ("search".equals(action)) {
            searchVideos(req, resp);  // Tìm kiếm video
        } else if ("comment".equals(action)) {
            comment(req, resp);
        } else {
            listVideo(req, resp);  // Hiển thị danh sách video
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            createVideo(req, resp);  // Xử lý tạo video mới
        } else if ("update".equals(action)) {
            updateVideo(req, resp);  // Xử lý cập nhật video
        } else if ("delete".equals(action)) {
            deleteVideo(req, resp);  // Xử lý xóa video
        } else if ("search".equals(action)) {
            searchVideos(req, resp);  // Tìm kiếm video
        } else if ("addFavorite".equals(action)) {
            addFavorite(req, resp);  // Xử lý yêu thích video
        } else if ("share".equals(action)) {
            addShare(req, resp);  // Xử lý chia sẻ video
        } else if ("comment".equals(action)) {
            comment(req, resp);

        }
    }

    private void createVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String poster = request.getParameter("poster");
        String description = request.getParameter("description");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        // Lấy UserId từ session (người dùng đã đăng nhập)
        Long userId = (Long) request.getSession().getAttribute("userId");

        // Kiểm tra xem UserId có tồn tại trong session không
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy đối tượng Usersentity từ cơ sở dữ liệu bằng userId
        Usersentity user = userDao.findById(String.valueOf(userId));

        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            return;
        }

        // Xử lý views
        int views = 0;
        try {
            String viewsParam = request.getParameter("views");
            if (viewsParam != null && !viewsParam.isEmpty()) {
                views = Integer.parseInt(viewsParam);
            }
        } catch (NumberFormatException e) {
            views = 0;
        }

        // Kiểm tra ID video hợp lệ
        if (id == null || id.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing video ID");
            return;
        }

        // **Trích xuất ID video từ URL poster**
        String videoId = extractVideoId(poster);
        if (videoId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid YouTube URL in poster");
            return;
        }

        // Tạo đối tượng VideoEntity và gán thông tin
        VideoEntity video = new VideoEntity();
        video.setId(Integer.parseInt(id));
        video.setTitle(title);
        video.setPoster(videoId); // Lưu ID video thay vì URL đầy đủ
        video.setViews(views);
        video.setDescription(description);
        video.setActive(active);
        video.setUser(user);

        try {
            // Lưu video vào cơ sở dữ liệu
            videosDao.create(video);
            response.sendRedirect("listvideo?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating video");
        }
    }

    // Hàm trích xuất ID video từ URL YouTube
    private String extractVideoId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        // Biểu thức chính quy để trích xuất ID video
        String regExp = "^.*(?:youtu.be/|v/|u/\\w/|embed/|watch\\?v=|&v=)([^#&?]*).*";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1); // Trả về ID video nếu tìm thấy
        }
        return null; // Trả về null nếu URL không hợp lệ
    }



    private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String poster = req.getParameter("poster");
        String description = req.getParameter("description");
        boolean active = Boolean.valueOf(req.getParameter("active"));

        // Xử lý ngoại lệ cho trường views
        int views = 0;
        try {
            views = Integer.parseInt(req.getParameter("views"));
        } catch (NumberFormatException e) {
            views = 0;  // Đặt giá trị mặc định nếu không có giá trị hợp lệ
        }

        // Tìm video cần cập nhật và thực hiện cập nhật
        VideoEntity video = videosDao.findById(id);
        if (video != null) {
            video.setTitle(title);
            video.setPoster(poster);
            video.setViews(views);
            video.setDescription(description);
            video.setActive(active);

            videosDao.update(video);
        }

        resp.sendRedirect("listvideo?action=list"); // Sau khi cập nhật, chuyển hướng về danh sách video
    }

    private void editVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        VideoEntity video = videosDao.findById(id);
        if (video != null) {
            req.setAttribute("video", video);
            req.getRequestDispatcher("/editVideo.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("listvideo?action=list");
        }
    }

    private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy ID video từ tham số URL
        String id = req.getParameter("id");

        // Kiểm tra nếu ID là null hoặc rỗng, trả về lỗi yêu cầu ID
        if (id == null || id.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 - Bad Request
            resp.getWriter().write("Video ID is missing");
            return;
        }

        try {
            // Chuyển đổi ID thành số nguyên
            int videoId = Integer.parseInt(id);

            // Gọi phương thức xóa video từ DAO
            boolean isDeleted = videosDao.deleteById(videoId);

            // Kiểm tra xem việc xóa có thành công không
            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về trang quản lý video
                resp.sendRedirect("listvideo?action=list");// Redirect để người dùng thấy kết quả
            } else {
                // Nếu không tìm thấy video để xóa, trả về lỗi
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 - Not Found
                resp.getWriter().write("Video not found");
            }
        } catch (NumberFormatException e) {
            // Nếu ID không phải là số hợp lệ
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 - Bad Request
            resp.getWriter().write("Invalid video ID");
        } catch (Exception e) {
            // Xử lý các lỗi không mong muốn khác
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 - Internal Server Error
            resp.getWriter().write("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void searchVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");  // Lấy từ khóa từ request

        // Kiểm tra xem từ khóa có hợp lệ không
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Tìm các video có tiêu đề chứa từ khóa
            List<VideoEntity> searchResults = videosDao.searchByTitle(keyword.trim());  // Tìm video với từ khóa
            request.setAttribute("videos", searchResults);  // Đưa kết quả vào request
        } else {
            // Nếu không có từ khóa, hiển thị tất cả video
            request.setAttribute("videos", videosDao.findAll());
        }

        // Chuyển hướng đến trang quản lý video (quanlyvideo.jsp) và hiển thị kết quả tìm kiếm
        request.getRequestDispatcher("quanlyvideo.jsp").forward(request, response);
    }



    private void listVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin userId từ session và chuyển đổi sang Long (kiểu Long đã được lưu trong session)
        Long userId = (Long) req.getSession().getAttribute("userId"); // Đảm bảo kiểu Long
        Usersentity user = userDao.findById(String.valueOf(userId));
        // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (userId == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            // Lấy danh sách video của người dùng từ VideosDao
            List<VideoEntity> videos = videosDao.findVideosByUser(userId);
            long totalVideos = videosDao.countVideosByUserId(userId);
            long totalViews = videosDao.countTotalViewsByUserId(userId);
            // Đưa danh sách video vào request để hiển thị trên trang
            req.setAttribute("videos", videos);
            req.setAttribute("userImage", user.getImage());
            req.setAttribute("totalVideos", totalVideos);
            req.setAttribute("totalViews", totalViews);
            // Chuyển hướng đến trang quản lý video
            req.getRequestDispatcher("/quanlyvideo.jsp").forward(req, resp);

        } catch (Exception e) {
            // Nếu có lỗi xảy ra khi lấy danh sách video, redirect về trang đăng nhập
            resp.sendRedirect("login.jsp");
        }
    }

    private void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Lấy userId từ session
            Object userIdObj = request.getSession().getAttribute("userId");
            Long userId = userIdObj != null ? Long.parseLong(userIdObj.toString()) : null;

            if (userId == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Chưa đăng nhập.");
                return;
            }

            // Lấy videoId từ request
            String videoIdStr = request.getParameter("videoId");
            if (videoIdStr == null || videoIdStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video ID là bắt buộc.");
                return;
            }

            // Chuyển videoId thành Long
            Long videoId = Long.parseLong(videoIdStr);

            // Kiểm tra video tồn tại
            VideoEntity video = videosDao.findById(String.valueOf(videoId));
            if (video == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không tìm thấy video.");
                return;
            }

            // Kiểm tra user tồn tại
            Usersentity user = userDao.findById(String.valueOf(userId));
            if (user == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không tìm thấy người dùng.");
                return;
            }

            // Kiểm tra xem người dùng đã yêu thích video chưa
            boolean isAlreadyFavorited = favoriteDao.isVideoFavoritedByUser(userId, videoId);
            if (isAlreadyFavorited) {
                // Nếu đã yêu thích, chỉ hiển thị thông báo và không chuyển trang
                request.getSession().setAttribute("message", "Bạn đã yêu thích video này rồi.");

                // Trả về trang video chi tiết hoặc trang hiện tại
                response.sendRedirect("detail.jsp?id=" + videoId);  // Hoặc trang bạn muốn giữ lại
                return;
            } else {
                // Nếu chưa yêu thích, tạo và lưu Favorites
                Favoritesentity favorite = new Favoritesentity();
                favorite.setUser(user);
                favorite.setVideo(video);
                favorite.setLikeDate(new Date());

                favoriteDao.create(favorite);

                // Lưu thông tin vào session (nếu cần)
                request.getSession().setAttribute("message", "Video đã được thêm vào danh sách yêu thích!");

                // Chuyển hướng đến trang video chi tiết hoặc danh sách video
                response.sendRedirect("detail.jsp?id=" + videoId);  // Hoặc trang bạn muốn chuyển hướng tới sau khi yêu thích
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng Video ID không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();  // Ghi log chi tiết cho dev
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu.");
        }
    }

    private void addShare(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Lấy userId từ session
            Object userIdObj = request.getSession().getAttribute("userId");
            Long userId = userIdObj != null ? Long.parseLong(userIdObj.toString()) : null;

            if (userId == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bạn cần đăng nhập để chia sẻ video.");
                return;
            }

            // Lấy videoId từ request
            String videoIdStr = request.getParameter("videoId");
            if (videoIdStr == null || videoIdStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video ID là bắt buộc.");
                return;
            }

            // Chuyển videoId thành Long
            Long videoId = Long.parseLong(videoIdStr);

            // Kiểm tra video tồn tại
            VideoEntity video = videosDao.findById(String.valueOf(videoId));
            if (video == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Video không tồn tại.");
                return;
            }

            String shareLink = "https://yourdomain.com/watch?id=" + videoId;
            String videoSrc = "https://www.youtube.com/embed/" + video.getPoster(); // Giả định youtubeId có trong VideoEntity

            // Đưa link chia sẻ và link video vào request
            request.setAttribute("shareLink", shareLink);
            request.setAttribute("videoSrc", videoSrc);

            // Chuyển hướng tới copylink.jsp

            // Kiểm tra user tồn tại và lấy thông tin người dùng
            Usersentity user = userDao.findById(String.valueOf(userId));
            if (user == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Người dùng không tồn tại.");
                return;
            }

            // Lấy email của người dùng đã đăng nhập
            String email = user.getEmail();
            if (email == null || email.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Người dùng không có email.");
                return;
            }

            // Tạo đối tượng chia sẻ
            Sharesentity share = new Sharesentity();
            share.setUsersentity(user);
            share.setVideosentity(video);
            share.setEmails(email); // Sử dụng email của người dùng
            share.setShareDate(new Date());

            // Lưu chia sẻ vào cơ sở dữ liệu
            shareDao.create(share);

            // Kiểm tra và cập nhật số lượng chia sẻ của video
            Integer shareCount = video.getShareCount();
            if (shareCount == null) {
                shareCount = 0;  // Gán giá trị mặc định là 0 nếu shareCount là null
            }
            shareCount++;  // Tăng số lượng chia sẻ lên 1

            // Cập nhật video với shareCount mới
            video.setShareCount(shareCount);  // Cập nhật số lượng chia sẻ
            videosDao.update(video);  // Cập nhật video vào cơ sở dữ liệu

            // Lưu thông báo vào session
            request.getSession().setAttribute("message", "Video đã được chia sẻ thành công.");

            // Redirect tới trang chi tiết video
            request.getRequestDispatcher("/linkchiase.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng Video ID không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết cho developer
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu.");
        }
    }



    private void comment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy các thông tin từ request
        String videoId = request.getParameter("videoId");
        String content = request.getParameter("content");
        System.out.println(videoId);
        System.out.println(content);

        // Kiểm tra xem người dùng có đăng nhập hay không
        Long userId = (Long) request.getSession().getAttribute("userId"); // Lấy userId từ session
        System.out.println(userId);
        if (userId == null) {
            // Nếu không có userId trong session, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy đối tượng User từ userId
        Usersentity user = userDao.findById(String.valueOf(userId)); // Sử dụng phương thức thực sự để lấy User từ DB

        // Lấy đối tượng Video từ videoId
        VideoEntity video = videosDao.findById(videoId); // Sử dụng phương thức thực sự để lấy Video từ DB

        // Kiểm tra nếu không tìm thấy User hoặc Video, chuyển hướng đến trang lỗi
        if (user == null || video == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Kiểm tra nội dung bình luận (optional: tránh XSS hoặc nội dung rỗng)
        if (content == null || content.trim().isEmpty()) {
            response.sendRedirect("error.jsp?message=Content+is+required");
            return;
        }

        // Tạo đối tượng Comment và gán các thuộc tính
        Comment comment = new Comment();
        comment.setVideo(video);  // Gán đối tượng Video vào Comment
        comment.setUser(user);    // Gán đối tượng User vào Comment
        comment.setCommentText(content);
        comment.setCommentDate(new Date());

        try {
            // Lưu Comment vào cơ sở dữ liệu thông qua commentDao
            commentDao.create(comment);

            // Sau khi lưu thành công, chuyển hướng đến trang chi tiết video và hiển thị các bình luận
            response.sendRedirect("detail.jsp?id=" + videoId);
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi khi tạo bình luận, chuyển hướng tới trang lỗi và hiển thị thông báo chi tiết
            response.sendRedirect("error.jsp?message=Error+while+adding+comment");
        }
    }


}
