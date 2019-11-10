package servlet;

import helper.FreemarkerHelper;
import model.Post;
import model.User;
import repository.PostRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User usr = (User) session.getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        List<Post> posts = new ArrayList<>();
        try {
            posts = new PostRepositoryJdbcImpl().findByAuthor(usr);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        root.put("user", usr);
        root.put("posts", posts);
        try {
            FreemarkerHelper.render(req, resp, "profile.ftl", root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
