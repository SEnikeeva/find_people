package servlet;

import helper.FreemarkerHelper;
import model.User;
import repository.UserRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User usr = (User) session.getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        root.put("user", usr);
        try {
            FreemarkerHelper.render(req, resp, "settings.ftl", root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        String username = req.getParameter("username");
        if (!username.equals("")) {
            user.setUsername(username);
        }
        String password = req.getParameter("password");
        if(!password.equals("")) {
            user.setPassword(password);
        }
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
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.equals("")) {
            user.setProfilePicture("/" + localdir + "/" + filename);
        }
        try {
            new UserRepositoryJdbcImpl().update(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("current_user", user);
        resp.sendRedirect("/profile");

    }
}
