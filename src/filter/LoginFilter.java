package filter;


import model.User;
import repository.UserRepositoryJdbcImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/profile", "/login", "/registration", "/join", "/chat", "/createpost"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getServletPath();
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("current_user");
        if (path.equals("/login") || path.equals("/registration")) {
            if (user == null) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/profile");
            }
        } else {
            if (user != null) {
                filterChain.doFilter(req, resp);
            } else {
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        try {
                            user = new UserRepositoryJdbcImpl().findByName(cookie.getValue());
                            if (user != null) {
                                req.getSession().setAttribute("current_user", user);
                                resp.sendRedirect("/profile");
                                break;
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            System.out.println("500");
                        }
                    }
                }
                if (user == null) {
                    resp.sendRedirect("/login");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
