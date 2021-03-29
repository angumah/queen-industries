import java.util.ArrayList;

public class Employee implements Comparable {
    protected final int ENGINEERING = 0;
    protected final int Marketing = 1;
    protected final int SALES = 2;

    protected int tier;
    protected double salary;
    protected String name;
    protected String department;
    protected String title;

    public Employee(double salary, String name, String department, String title){
        this.tier = Company.EMPLOYEE;
        this.salary = salary;
        this. name = name;
        this.department = department;
        this.title = title;
    }

    public int getTier(){
        return this.tier;
    }
    public String getDepartment(){
        return this.department;
    }

    public double getSalary(){
        return this.salary;
    }

    public String getName(){
        return this.name;
    }

    public String getTitle(){
        return this.title;
    }
    public double getCompensation(){
        return this.salary;
    }


    @Override
    public int compareTo(Object o) {
        Employee e = (Employee)o;
        String department = e.department;
        String thisDepartment = this.department;



        return 0;
    }
}
