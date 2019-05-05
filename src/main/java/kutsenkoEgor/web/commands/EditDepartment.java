package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kutsenkoEgor.Path.PAGE_LIST_DEPARTMENTS;

public class EditDepartment extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("command Edit department");

        int id = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("newName");
        System.out.println("id ="+id);
        System.out.println("newName="+newName);
        if (newName.length()>0) {
            DBManager dbManager = DBManager.getInstance();
            dbManager.changeDepartment(id, newName);
        }

        return PAGE_LIST_DEPARTMENTS;
    }
}
