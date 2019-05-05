package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static kutsenkoEgor.Path.PAGE_LIST_DEPARTMENTS;

public class AddDepartment extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("command Add department");
        String name=request.getParameter("name");

        DBManager dbManager=DBManager.getInstance();
        if (name.length()>0&&name.length()<30) {
            dbManager.addDepartment(new Department(name));
        }

        return PAGE_LIST_DEPARTMENTS;
    }
}
