package kutsenkoEgor.web.commands;

import kutsenkoEgor.db.DBManager;
import kutsenkoEgor.db.entity.Employee;
import kutsenkoEgor.web.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static kutsenkoEgor.Path.PAGE_LIST_EMPLOYEES;

public class AddEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("command Add employee");
        List<Employee> employeeList;
        String date;
        String email;
        int department_id;
        String name;

        department_id = Integer.parseInt(request.getParameter("department_id"));

        Validator validator = new Validator();

        if (validator.employeeValidation(request,response)) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.format(new Date());

            name = request.getParameter("name");
            email = request.getParameter("email");

            DBManager.getInstance().addEmployee(date, email, name, department_id);
        }
            employeeList = DBManager.getInstance().selectDepartmentsEmployee(department_id);

            request.setAttribute("employeeList", employeeList);
            request.setAttribute("department_id", department_id);


        return PAGE_LIST_EMPLOYEES;
    }
}
