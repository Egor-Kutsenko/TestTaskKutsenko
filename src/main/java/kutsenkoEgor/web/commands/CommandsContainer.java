package kutsenkoEgor.web.commands;


import java.util.Map;
import java.util.TreeMap;

public class CommandsContainer {



    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {

        commands.put("Remove department", new RemoveDepartment());
        commands.put("Edit department", new EditDepartment());
        commands.put("Show employees", new ShowEmployees());
        commands.put("Add department", new AddDepartment());

        commands.put("Fire an employee", new FireAnEmployee());
        commands.put("Edit mail", new EditMail());
        commands.put("Add employee", new AddEmployee());
        commands.put("Transfer employee", new TransferEmployee());
        commands.put("Back",new Back());

    }


    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
