package main.admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/curation")
public class curation_servlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("/");
        String message = req.getParameter("message");
        curation_model curationModel= new curation_model(realPath);
        PrintWriter out = resp.getWriter();

        try {
            Set<String> bully = curationModel.checkBully(1, message);
            for (String s:bully
                 ) {
                out.append(s).append("\n");
            }
            Set<String> person = curationModel.extractPerson(message);
            Set<String> location = curationModel.extractLocation(message);
            Set<String> organization = curationModel.extractOrganization(message);

            out.append("person:"+"\n");

            for (String s:person
                    ) {
                out.append(s).append("\n");
            }

            out.append("location:"+"\n");

            for (String s:location
                    ) {
                out.append(s).append("\n");
            }

            out.append("organization:"+"\n");

            for (String s:organization
                    ) {
                out.append(s).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
}
