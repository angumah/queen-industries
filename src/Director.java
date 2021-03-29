import java.lang.reflect.Array;
import java.util.ArrayList;

public class Director extends Manager {
    protected double stockShares;

    public Director (double salary, double stockShares, double bonus,  String name, String department, String title, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, bonus, name, department, title, reports, tier);
        this.stockShares = stockShares;
    }


    public double getCompensation(){
        return this.salary + this.bonus + (this.stockShares * 123.45);
    }


    public void adjustSalary(int amt, Employee employee) throws Exception{
        int updated = -1;
        if (this.reports.contains(employee)){
            double salary = employee.getSalary() + amt;
            employee.salary = salary;
            updated = 1;
        } else {
            for(Object o:reports){
                if(o instanceof Manager) {
                    Manager m = (Manager) o;
                    if(m.reports.contains(employee)){
                        double salary = employee.getSalary() + amt;
                        employee.salary = salary;
                        updated = 1;
                    }

                }

            }
        }
        if(updated < 1){
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        }

    }

}

