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


@MultipartConfig
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FreemarkerHelper.render(req, resp, "registration.ftl", null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
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
        if (fileName.equals("")) {
            try {
                new UserRepositoryJdbcImpl().save(new User(
                        0,
                        req.getParameter("username"),
                        req.getParameter("password"),
                        null
                ));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("e.printStackTrace();");
            }
        } else {
            String need_path = "/" + localdir + "/" + filename;
            try {
                new UserRepositoryJdbcImpl().save(new User(
                        0,
                        req.getParameter("username"),
                        req.getParameter("password"),
                        need_path
                ));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("e.printStackTrace();");
            }
        }
        try {
            session.setAttribute("current_user", new UserRepositoryJdbcImpl().findByName(req.getParameter("username")));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.print("500");
        }
        resp.sendRedirect("/profile");
        }

}
