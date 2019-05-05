package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_EMPLOYEES;

public class ShowEmployees extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("command Show employee");

        int id;
        String departmentName;
        List<Employee> employeeList;

        DBManager dbManager = DBManager.getInstance();

        id = Integer.parseInt(request.getParameter("id"));
        employeeList = dbManager.selectDepartmentsEmployee(id);

        departmentName=dbManager.selectByDepartmentId(id).getDepartmentName();

        request.setAttribute("department_id", id);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("departmentName", departmentName);


        return PAGE_LIST_EMPLOYEES;
    }
}
