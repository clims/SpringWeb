package spring.hiber.web;

import org.springframework.context.ApplicationContext;
import spring.hiber.dao.RocketDAO;
import spring.hiber.model.Rocket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class Hello extends HttpServlet {
    private RocketDAO rocketDAO;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext ctx = ((ApplicationContext) config.getServletContext().getAttribute("springContext"));
        rocketDAO = ctx.getBean(RocketDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().print("<form action=\"/\" method=\"post\">\n" +
                "    <button type=\"submit\">Press it!!!</button>\n" +
                "</form>");
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Rocket> rockets = rocketDAO.getAllRockets();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<table border=\"1px\" cellpading=\"10px\">" +
                "    <tr>\n" +
                "        <th align=\"center\" colspan=\"5\">lol</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>ID</td>\n" +
                "        <td>Name</td>\n" +
                "        <td>Price</td>\n" +
                "        <td>DeltaV</td>\n" +
                "        <td>Weight</td>\n" +
                "    </tr>");
        for (Rocket r : rockets) {
            out.print("<tr>\n" +
                    "        <td>" + r.getId() + "</td>\n" +
                    "        <td>" + r.getName() + "</td>\n" +
                    "        <td>" + r.getPrice() + "</td>\n" +
                    "        <td>" + r.getDeltav() + "</td>\n" +
                    "        <td>" + r.getWeight() + "</td>\n" +
                    "    </tr>");
        }
        out.print("</table>");
        resp.getWriter().close();
    }
}
