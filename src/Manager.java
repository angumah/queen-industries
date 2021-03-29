import java.lang.reflect.Array;
import java.util.ArrayList;

public class Manager extends Employee {
    protected double bonus;
    protected ArrayList<Employee> reports;

    public Manager(double salary, double bonus, String name, String department, String title, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, name, department, title);

        for(Employee employee: reports){
            if(employee.tier >= tier){
                throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
            }
        }
        this.tier = tier;
        this.bonus = bonus;
        this. reports = reports;
    }


    public double getCompensation(){
        return this.salary + this.bonus;
    }
    public void hire(Employee employee) throws Exception {
        int tier = employee.getTier();
        checkTier(tier, "hire");
            Company company = null;
            try {
                company = Company.getInstance();
            } catch (Exception e){

            }
            ArrayList<Employee> staff = (ArrayList<Employee>)company.getStaff();
            staff.add(employee);
            company.setStaff(staff);
            this.reports.add(employee);
            System.out.println("LOG : new Employee hired" + " (" +  employee.name + ", " + employee.department + ", " + employee.title + ")");
    }

    public void fire(Employee employee) throws Exception{
        int tier = employee.getTier();
        checkTier(tier, "fire");
        checkReport(employee);
        if(employee.getDepartment().equals(this.department)){
            Company company = null;
            try {
                company = Company.getInstance();
            } catch (Exception e){

            }
            ArrayList<Employee> staff = (ArrayList<Employee>)company.getStaff();
            staff.remove(employee);
            company.setStaff(staff);


            System.out.println("LOG : existing Employee fired" + " (" +  employee.name + ", " + employee.department + ", " + employee.title + ")");
            if(employee.tier == Company.MANAGER) {
                Manager m = (Manager)employee;
                System.out.println("LOG : reports re-assigned " + reAssign(m, company));
            }
            this.reports.remove(employee);
        }




    }


    protected void checkTier(int tier, String action) throws Exception{
        if(tier >= this.tier){
            throw new Exception("ERROR: cannot " + action + " an Employee of an equal or greater tier.");
        }
    }

    protected void checkReport(Employee emp) throws Exception{
        boolean doesReport = reports.contains(emp);
        if (!doesReport){
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
    }

    protected String reAssign(Manager manager, Company company){

        ArrayList<Employee> reports = manager.reports;
        String reportString = "[";
        for(int i = 0; i < reports.size(); i++){
            Employee current = reports.get(i);
            current.tier = Company.MANAGER;
            String name = current.name+", ";
            String department = current.department + ", ";
            String title;
            if (i == reports.size()-1){
                title = current.title;
            } else {
                title = current.title + ", ";
            }
            reportString += name + department + title;
        }

        ArrayList directors = company.getDirectors();

        for(Object o: directors){
            Director d = (Director)o;

            if(d.reports.contains(manager)) {
               for(Employee e: manager.reports){
                   d.reports.add(e);
               }
            }

        }
        return reportString + "]";
    }
}
