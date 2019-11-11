package servlet;

import helper.FreemarkerHelper;
import model.Chat;
import model.Message;
import model.User;
import repository.MessageRepositoryJdbcImpl;
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

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> root = new HashMap<>();
        List<Message> messages;
        Chat chat = new Chat(Integer.parseInt(req.getParameter("chatId")));
        try {
           messages = chat.getConversation();
           root.put("messages", messages);
           root.put("chatId", chat.getId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FreemarkerHelper.render(req, resp, "chat.ftl", root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String new_message = req.getParameter("new_message");
        User sender = (User) session.getAttribute("current_user");
        int chat = Integer.parseInt(req.getParameter("chatId"));
        Message message = new Message(0, sender, new_message, new Date(System.currentTimeMillis()));
        try {
            int message_id = new MessageRepositoryJdbcImpl().save(message);
            new ChatService().addMessage(chat, message_id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/chat?chatId=" + chat);
    }
}
