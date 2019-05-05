package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Employee;
import kutsenkoEgor.web.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_EMPLOYEES;

public class EditMail extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("command Edit email");

        String newEmail = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("id"));
        int department_id=Integer.parseInt(request.getParameter("department_id"));

        Validator validator = new Validator();
        System.out.println(validator.newEmployeeEmailValidation(request,response));
        if (validator.newEmployeeEmailValidation(request,response)) {
            DBManager.getInstance().changeEmployeeByMail(id, newEmail);
        }
        System.out.println("after validation");

        List<Employee> employeeList;
        employeeList = DBManager.getInstance().selectDepartmentsEmployee(department_id);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("department_id", department_id);

        return PAGE_LIST_EMPLOYEES;
    }
}
