package helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class FreemarkerHelper {

    private static Configuration cfg = null;

    public static Configuration getConfig(HttpServletRequest req) throws ClassNotFoundException {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setServletContextForTemplateLoading(req.getServletContext(), "/WEB-INF/templates");
            cfg.setDefaultEncoding("utf-8");
            cfg.setOutputEncoding("utf-8");
            Class.forName("freemarker.template.TemplateException");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return cfg;
    }

    public static void render(HttpServletRequest request,
                              HttpServletResponse response,
                              String path,
                              Map<String, Object> root) throws IOException, ClassNotFoundException {


        Configuration cfg = FreemarkerHelper.getConfig(request);
        try {
            Template tmpl = cfg.getTemplate(path);
            try {
                response.setContentType("text/html;charset=utf-8");
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
