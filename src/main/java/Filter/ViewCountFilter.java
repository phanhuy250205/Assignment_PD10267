package Filter;


import Dao.ShareDao;
import Dao.VideosDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")  // Áp dụng filter cho tất cả các yêu cầu
public class ViewCountFilter implements Filter {

    private ShareDao videoShareDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo videoShareDao ở đây
        videoShareDao = new ShareDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Kiểm tra xem yêu cầu có liên quan đến video không
        String videoId = httpRequest.getParameter("id");

        if (videoId != null && !videoId.isEmpty()) {
            try {
                // Tăng số lượt xem của video
                videoShareDao.updateViews(Integer.parseInt(videoId));
            } catch (Exception e) {
                e.printStackTrace();
                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi cập nhật lượt xem.");
                return;
            }
        }

        // Tiếp tục xử lý yêu cầu
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup nếu cần
    }
}

