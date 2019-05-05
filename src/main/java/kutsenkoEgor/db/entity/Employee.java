package kutsenkoEgor.db.entity;

public class Employee {
    private int id;
    private String date;
    private String email;
    private String fullName;
    private int departmentName;

    public Employee(String date, String email, String fullName, int departmentName) {
        this.date = date;
        this.email = email;
        this.fullName = fullName;
        this.departmentName = departmentName;
    }

    public Employee(int id, String date, String email, String fullName, int departmentName) {
      this.id=id;
        this.date = date;
        this.email = email;
        this.fullName = fullName;
        this.departmentName = departmentName;
    }

    public Employee(String fullName, String email){
        this.fullName=fullName;
        this.email=email;
    }

    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(int departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", departmentName=" + departmentName +
                '}';
    }
}
