package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_EMPLOYEES;

public class FireAnEmployee extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("command Fire an employee");

        int id = Integer.parseInt(request.getParameter("id"));
        int department_id=Integer.parseInt(request.getParameter("department_id"));

        DBManager.getInstance().removeEmployeeById(id);

                List<Employee> employeeList;
        employeeList = DBManager.getInstance().selectDepartmentsEmployee(department_id);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("department_id", department_id);

        return PAGE_LIST_EMPLOYEES;
    }
}
