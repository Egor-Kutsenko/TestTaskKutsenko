package kutsenkoEgor.web;

import kutsenkoEgor.db.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static List<Employee> validationControlList = new ArrayList<>();

    public boolean newEmployeeNameValidation(HttpServletRequest request,
                                             HttpServletResponse response) {
        boolean flag = false;
        String nameAfterCheck = "";
        String nameBeforeCheck;

        nameBeforeCheck = request.getParameter("name");

        Pattern p = Pattern.compile("([A-Z][a-z]{1,20})");
        Matcher m = p.matcher(nameBeforeCheck);

        while (m.find()) {
            nameAfterCheck = (m.group(1));
        }

        if (nameBeforeCheck.equals(nameAfterCheck) && nameBeforeCheck.length() > 0) {
            flag = true;
        }

        return flag;
    }

    public boolean newEmployeeEmailValidation(HttpServletRequest request,
                                              HttpServletResponse response) {
        boolean flag = false;
        String emailAfterCheck = "";
        String emailBeforeCheck;

        emailBeforeCheck = request.getParameter("email");

        Pattern p = Pattern.compile("(\\w+@\\w+.\\w+)");
        Matcher m = p.matcher(emailBeforeCheck);

        while (m.find()) {
            emailAfterCheck = (m.group(1));
        }

        if (emailBeforeCheck.equals(emailAfterCheck) && emailBeforeCheck.length() > 0) {
            flag = true;
        }

        return flag;
    }

    public boolean employeeValidation(HttpServletRequest request,
                                      HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        validationControlList.add(new Employee(name, email));

        boolean flag = false;

        if (newEmployeeEmailValidation(request, response) &&
                newEmployeeNameValidation(request, response)) {
            flag = true;
        }

        return flag;
    }


}
