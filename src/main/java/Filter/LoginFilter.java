package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter initialized.");
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // In session ID và userId để kiểm tra
        if (session != null) {
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID in session: " + session.getAttribute("userId"));
        } else {
            System.out.println("No session found.");
        }

        String loginURI = httpRequest.getContextPath() + "/login";
        String registerURI = httpRequest.getContextPath() + "/dangky";
        String forgotPasswordURI = httpRequest.getContextPath() + "/changePassword";

        String currentPath = httpRequest.getRequestURI();

        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
        System.out.println("Is logged in: " + isLoggedIn);

        boolean isPublicRequest = currentPath.equals(loginURI) || currentPath.equals(registerURI) || currentPath.equals(forgotPasswordURI);
        System.out.println("Is public request: " + isPublicRequest);

        boolean isResourceRequest = currentPath.startsWith(httpRequest.getContextPath() + "/assets");
        System.out.println("Is resource request: " + isResourceRequest);

        if (isLoggedIn || isPublicRequest || isResourceRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter destroyed.");
    }
}
