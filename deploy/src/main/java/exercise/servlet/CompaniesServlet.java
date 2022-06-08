package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        String company = request.getParameter("search");
        PrintWriter pw = response.getWriter();
        List<String> companies = getCompanies();

        if (company == null || company == "") {
            companies.stream().forEach(x -> pw.write(x + "\n"));
        } else {
            List<String> findedCompanies = companies.stream().filter(x -> x.contains(company)).toList();
            if (findedCompanies.isEmpty()) {
                pw.write("Companies not found");
            } else {
                findedCompanies.stream().forEach(x -> pw.write(x + "\n"));
            }
        }
        pw.close();
        // END
    }
}
