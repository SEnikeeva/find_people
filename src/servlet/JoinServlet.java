package servlet;

import helper.FreemarkerHelper;
import model.User;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int user_id = ((User) session.getAttribute("current_user")).getId();
        int post_id = Integer.parseInt(req.getParameter("postId"));
        try {
            new PostService().addGamer(post_id, user_id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FreemarkerHelper.render(req, resp, "join.ftl", null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/index");
    }
}
