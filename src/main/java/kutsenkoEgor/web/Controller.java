package kutsenkoEgor.web;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Department;
import kutsenkoEgor.web.commands.Command;
import kutsenkoEgor.web.commands.CommandsContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_DEPARTMENTS;

@WebServlet(urlPatterns = "/servlet")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        process(req, resp);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        String forward = PAGE_LIST_DEPARTMENTS;

        String commandName = request.getParameter("command");
        System.out.println("used command " + commandName);
        Command command = CommandsContainer.get(commandName);

        if (commandName != null)
            forward = command.execute(request, response);
        System.out.println("forward to " + forward);

        List<Department> list;

        DBManager dbManager = DBManager.getInstance();
        list = dbManager.showAllDepartments();

        request.setAttribute("list", list);

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
