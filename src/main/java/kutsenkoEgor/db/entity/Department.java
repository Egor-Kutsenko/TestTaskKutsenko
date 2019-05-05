package kutsenkoEgor.db.entity;

public class Department {
    private String DepartmentName;
    private int id;

    public Department(String departmentName) {
        DepartmentName = departmentName;
    }

    public Department(String departmentName, int id) {
        DepartmentName = departmentName;
        this.id=id;
    }


    public Department(){}

    public String getDepartmentName() {
        return DepartmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.DepartmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "DepartmentName='" + DepartmentName + '\'' +
                ", id=" + id +
                '}';
    }
}
