package servlet;

import model.Post;
import repository.PostRepositoryJdbcImpl;
import service.PostService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/s")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<Post> posts = new ArrayList<>();
        try {
             posts = new PostService().findLike(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        JSONArray ja = new JSONArray();
        for (Post student: posts) {
            ja.put(new JSONObject(student));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);
        resp.setContentType("text/json");
        resp.getWriter().write(jo.toString());
    }
}
