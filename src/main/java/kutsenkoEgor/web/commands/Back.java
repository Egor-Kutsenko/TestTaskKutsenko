package kutsenkoEgor.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static kutsenkoEgor.Path.PAGE_LIST_DEPARTMENTS;

public class Back extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("command Back");

        return PAGE_LIST_DEPARTMENTS;
    }
}
