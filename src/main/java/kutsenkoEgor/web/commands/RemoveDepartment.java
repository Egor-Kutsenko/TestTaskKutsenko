package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Department;
import kutsenkoEgor.db.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_DEPARTMENTS;

public class RemoveDepartment extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("command Remove department");
        int id;
        List<Employee> employeeList;
        String message;

        DBManager dbManager = DBManager.getInstance();
        id = Integer.parseInt(request.getParameter("id"));

        employeeList = dbManager.selectDepartmentsEmployee(id);


        if (employeeList.size() > 0) {
            message = "This department cannot be removed while there are workers in it!";
        } else {
            DBManager.getInstance().removeDepartmentById(id);
            message = "Department removed!";
        }

        List<Department> list;

        list = dbManager.showAllDepartments();

        request.setAttribute("list", list);
        request.setAttribute("message", message);

        return PAGE_LIST_DEPARTMENTS;

    }
}
