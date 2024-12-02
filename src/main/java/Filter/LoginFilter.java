//package Filter;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//
//@WebFilter("/*")  // Áp dụng filter cho tất cả các URL
//public class LoginFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("LoginFilter initialized.");
//    }
//
//    @Override
//    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        // Ép kiểu ServletRequest và ServletResponse sang HttpServletRequest và HttpServletResponse
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // Lấy session hiện tại, nếu session chưa tồn tại thì trả về null
//        HttpSession session = httpRequest.getSession(false);
//
//        // Định nghĩa các đường dẫn công khai
//        String loginURI = httpRequest.getContextPath() + "/login";        // Trang đăng nhập
//        String registerURI = httpRequest.getContextPath() + "/dangky"; // Trang đăng ký
//        String forgotPasswordURI = httpRequest.getContextPath() + "/changePassword"; // Trang quên mật khẩu
//
//        // Lấy đường dẫn hiện tại mà người dùng yêu cầu
//        String currentPath = httpRequest.getRequestURI();
//
//
//        // - Người dùng đã đăng nhập: Session tồn tại và có attribute "userId"
//        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
//        System.out.println("Is logged in: " + isLoggedIn);
//        // - Yêu cầu truy cập các trang công khai
//        boolean isPublicRequest = currentPath.equals(loginURI) || currentPath.equals(registerURI) || currentPath.equals(forgotPasswordURI);
//
//        // - Yêu cầu truy cập tài nguyên tĩnh (CSS, JS, hình ảnh, v.v.)
//        boolean isResourceRequest = currentPath.startsWith(httpRequest.getContextPath() + "/assets");
//
//        // Nếu người dùng đã đăng nhập hoặc yêu cầu trang công khai, hoặc yêu cầu tài nguyên tĩnh, cho phép tiếp tục
//        if (isLoggedIn || isPublicRequest || isResourceRequest) {
//            chain.doFilter(request, response);
//        } else {
//            // Nếu chưa đăng nhập và yêu cầu không hợp lệ (không phải trang công khai), chuyển hướng đến trang đăng nhập
//            httpResponse.sendRedirect(loginURI);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("LoginFilter destroyed.");
//    }
//}
