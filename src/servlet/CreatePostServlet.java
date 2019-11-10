package servlet;

import helper.FreemarkerHelper;
import model.Chat;
import model.Game;
import model.Post;
import model.User;
import repository.GameRepositoryJdbcImpl;
import repository.PostRepositoryJdbcImpl;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/createpost")
public class CreatePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FreemarkerHelper.render(req, resp, "createpost.ftl", null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        resp.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        int requiredPlayers = Integer.parseInt(req.getParameter("requiredPlayers"));
        User user = (User) session.getAttribute("current_user");
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] filename_data = filePart.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        filePart.write(fullpath);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        Date date = new Date(System.currentTimeMillis());
        int post = 0;
        if (fileName.equals("")) {
            try {
                int game = new GameRepositoryJdbcImpl().save(new Game(0, title,"", null));
                post = new PostRepositoryJdbcImpl().save(new Post(
                        0,
                        new GameRepositoryJdbcImpl().findByID(game),
                        user,
                        requiredPlayers,
                        date,
                        null, null));
            } catch (SQLException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            String need_path = "/" + localdir + "/" + filename;
            try {
                int game = new GameRepositoryJdbcImpl().save(new Game(0, title,"", need_path));
                post = new PostRepositoryJdbcImpl().save(new Post(
                        0,
                        new GameRepositoryJdbcImpl().findByID(game),
                        user,
                        requiredPlayers,
                        date,
                        null, null));
            } catch (SQLException |ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                new PostService().addGamer(post, user.getId());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/profile");
        }
    }
}
