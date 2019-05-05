package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_EMPLOYEES;

public class TransferEmployee extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("command Transfer employee");

        int department_id;
        String newDepartment;
        int newId;
        int id;

        id = Integer.parseInt(request.getParameter("id"));
        newDepartment = request.getParameter("new department");
        department_id=Integer.parseInt(request.getParameter("department_id"));

        DBManager dbManager = DBManager.getInstance();

        newId=dbManager.selectByDepartmentName(newDepartment).getId();

        DBManager.getInstance().changeEmployeeByDepartment(id,newId);

        List<Employee> employeeList;
        employeeList = DBManager.getInstance().selectDepartmentsEmployee(department_id);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("department_id", department_id);

        return PAGE_LIST_EMPLOYEES;
    }
}
