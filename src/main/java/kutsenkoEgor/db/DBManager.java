package kutsenkoEgor.db;

import kutsenkoEgor.db.entity.Department;
import kutsenkoEgor.db.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager instance;

    private static final String CONNECTION_URL = "jdbc:mysql://localhost/TestDatabase?serverTimezone=UTC";

    private static final String USER_NAME = "user1";
    private static final String USER_PASS = "pass1";


    private static final String SQL_ADD_DEPARTMENT = "INSERT INTO Departments VALUES(default, ?)";
    private static final String SQL_ADD_EMPLOYEE = "INSERT INTO employees VALUES(default, ?,?,?,?)";


    private static final String SQL_SHOW_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String SQL_SHOW_DEPARTMENTS_EMPLOYEES = "SELECT * FROM EMPLOYEES WHERE department_id=?";
    private static final String SQL_SHOW_DEPARTMENT_BY_NAME = "SELECT * FROM departments WHERE department_name=?";
    private static final String SQL_SHOW_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id=?";


    private static final String SQL_CHANGE_EMPLOYEE_EMAIL = "UPDATE employees SET email=?" +
            " WHERE id=?";
    private static final String SQL_CHANGE_EMPLOYEE_DEPARTMENT = "UPDATE employees SET department_id=?" +
            " WHERE id=?";
    private static final String SQL_CHANGE_DEPARTMENT = "UPDATE departments SET department_name=?" +
            " WHERE id=?";

    private static final String SQL_REMOVE_DEPARTMENT_BY_ID = "DELETE FROM departments where id=?";


    private static final String SQL_REMOVE_EMPLOYEE_BY_ID = "DELETE FROM employees where id=?";
    private static final String SQL_REMOVE_EMPLOYEE_BY_DEPARTMENT_NAME = "DELETE FROM employees where department_id=?";

    private DBManager() {

    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public boolean addDepartment(Department department) {
        boolean result = false;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_ADD_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
            prstmt.setString(prepareStatementParam, department.getDepartmentName());

            if (prstmt.executeUpdate() > 0) {
                rs = prstmt.getGeneratedKeys();
            }

            if (rs.next()) {
                int departmentID = rs.getInt(1);
                department.setId(departmentID);
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con,prstmt,rs);
        }
        return result;
    }

    public boolean changeDepartment(int id, String departmentName) {
        return changeDepartmen(id, departmentName, SQL_CHANGE_DEPARTMENT);
    }

    private boolean changeDepartmen(int id, String departmentName, String sqlChangeDepartment) {
        boolean result = false;
        int prepareStatementParam = 1;
        Connection con = null;
        PreparedStatement prstmt = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(sqlChangeDepartment);

            prstmt.setString(prepareStatementParam++, departmentName);
            prstmt.setInt(prepareStatementParam, id);

            prstmt.executeUpdate();

            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(con);
            close(prstmt);
        }

        return result;
    }

    public boolean removeDepartmentById(int id) {
        return removeDepartmenById(id, SQL_REMOVE_DEPARTMENT_BY_ID);
    }

    private boolean removeDepartmenById(int id, String sqlRemoveDepartmentById) {
        boolean result = false;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(sqlRemoveDepartmentById);

            prstmt.setInt(prepareStatementParam, id);
            prstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(prstmt);
        }
        return result;
    }

    public List<Department> showAllDepartments() {

        List<Department> listOfDepartment = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(SQL_SHOW_ALL_DEPARTMENTS);

            while (rs.next()) {
                listOfDepartment.add(extractDepartment(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(con);
            close(stmt);
            close(rs);
        }

        return listOfDepartment;
    }

    public Department selectByDepartmentName(String departmentName) {
        Department department = null;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_SHOW_DEPARTMENT_BY_NAME);

            prstmt.setString(prepareStatementParam, departmentName);
            rs = prstmt.executeQuery();
            rs.next();

            department = extractDepartment(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(con,prstmt,rs);
        }

        return department;
    }

    public Department selectByDepartmentId(int id) {
        Department department = null;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_SHOW_DEPARTMENT_BY_ID);

            prstmt.setInt(prepareStatementParam, id);
            rs = prstmt.executeQuery();
            rs.next();

            department = extractDepartment(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(con,prstmt,rs);
        }

        return department;
    }

    public boolean addEmployee(String date, String email, String fullName, int departmentName) {
        boolean result = false;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_ADD_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            prstmt.setString(prepareStatementParam++, date);
            prstmt.setString(prepareStatementParam++, email);
            prstmt.setString(prepareStatementParam++, fullName);
            prstmt.setInt(prepareStatementParam, departmentName);

            prstmt.executeUpdate();

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con,prstmt,rs);
        }
        return result;
    }

    public boolean changeEmployeeByMail(int id, String newEmail) {
        return changeDepartmen(id, newEmail, SQL_CHANGE_EMPLOYEE_EMAIL);
    }

    public boolean changeEmployeeByDepartment(int department_id1, int department_id2) {
        boolean result = false;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_CHANGE_EMPLOYEE_DEPARTMENT);

            prstmt.setInt(prepareStatementParam++, department_id2);
            prstmt.setInt(prepareStatementParam, department_id1);

            prstmt.executeUpdate();

            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(con);
            close(prstmt);
        }

        return result;
    }

    public boolean removeEmployeeById(int id) {
        return removeDepartmenById(id, SQL_REMOVE_EMPLOYEE_BY_ID);
    }

    public boolean removeEmployeeBy(Employee employee) {
        boolean result = false;
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_REMOVE_EMPLOYEE_BY_DEPARTMENT_NAME);

            prstmt.setInt(prepareStatementParam, employee.getDepartmentName());
            prstmt.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con);
            close(prstmt);
        }

        return result;
    }

    public List<Employee> selectDepartmentsEmployee(int id) {
        List<Employee> employeeList = new ArrayList<>();
        int prepareStatementParam = 1;

        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prstmt = con.prepareStatement(SQL_SHOW_DEPARTMENTS_EMPLOYEES);

            prstmt.setInt(prepareStatementParam, id);

            rs = prstmt.executeQuery();

            while (rs.next()) {
                employeeList.add(extractEmployee(rs));
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con,prstmt,rs);
        }
        return employeeList;
    }

    private Department extractDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt(1));
        department.setDepartmentName(rs.getString(2));
        return department;
    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt(1));
        employee.setDate(rs.getString(2));
        employee.setEmail(rs.getString(3));
        employee.setFullName(rs.getString(4));
        employee.setDepartmentName(rs.getInt(5));
        return employee;
    }

    private void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Connection was not open");
        }
    }

    private void close(PreparedStatement prstmt) {
        try {
            if (prstmt != null) {
                prstmt.close();
            }
        } catch (SQLException ex) {
            System.err.println("PrepareStatement was not open");
        }
    }

    private void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.err.println("PrepareStatement was not open");
        }
    }

    private void close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println("Statement was not open");
        }
    }

    private void close(Connection con, PreparedStatement prstmt, ResultSet rs){
        close(con);
        close(prstmt);
        close(rs);
    }

    public static void main(String[] args) {
//        DBManager db = new DBManager();
//        List<Department> list = new ArrayList<>();
//        list=db.showAllDepartments();
//        for (Department el:list) {
//            System.out.print(el.getId()+" | "+ el.getDepartmentName());
//            System.out.println();
//        }
//        System.out.println(list.toString());
        DBManager db = new DBManager();

        System.out.println(db.selectByDepartmentId(2));
    }

}
