package servlet;

import helper.FreemarkerHelper;
import model.*;
import repository.MessageRepositoryJdbcImpl;
import repository.PostRepositoryJdbcImpl;
import service.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> root = new HashMap<>();
        try {
            Post post = new PostRepositoryJdbcImpl().findByGameName(req.getParameter("gameName"));
            root.put("post", post);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FreemarkerHelper.render(req, resp, "post.ftl", root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
