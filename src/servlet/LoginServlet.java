package servlet;

import helper.FreemarkerHelper;
import model.User;
import repository.UserRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FreemarkerHelper.render(req, resp, "login.ftl", null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("email");
        String password = req.getParameter("password");
        User user = (User) session.getAttribute("current_user");
        User usr = null;
        try {
            usr = new UserRepositoryJdbcImpl().validateUser(username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (usr != null) {
            if (req.getParameter("rememberme") != null) {
                Cookie cookie = new Cookie("rememberme", usr.getId().toString());
                cookie.setMaxAge(60 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            session.setAttribute("current_user", usr);
            resp.sendRedirect("/profile");
        } else resp.sendRedirect("/login");
    }
}
