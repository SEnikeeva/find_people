package servlet;

import helper.FreemarkerHelper;
import model.Post;
import model.User;
import repository.PostRepositoryJdbcImpl;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/statistic")
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> root = new HashMap<>();
        List<Post> posts = new ArrayList<>();
        try {
            posts = new PostService().getTopPosts();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        root.put("posts", posts);
        try {
            FreemarkerHelper.render(req, resp, "statistic.ftl", root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
