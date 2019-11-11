package servlet;

import helper.FreemarkerHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FreemarkerHelper.render(req, resp, "home.ftl", null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
