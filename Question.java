class Employee{
    static String companyName  = "ABC";
    String name;
    int EmpID;
    String Dept;
    double basicSalary;

    double hra ;
    double pf;
    double ta;
    double da ;
    double tax;
    double InHandSalary;
    Employee(String n,int id,String dep,double bs){
        this.name=n;
        this.EmpID=id;
        this.Dept=dep;
        this.basicSalary=bs;
        hra = (this.basicSalary)*.25;
        pf = (this.basicSalary)*.12;
        ta  = (this.pf)*.2;
        da  = (this.pf)*.3;
        if(this.basicSalary <= 320000){
            this.tax=0;
        }
        else if( this.basicSalary<=450000){
            this.tax= ((this.basicSalary-320000)*.12);
        }
        else if(this.basicSalary<=699000){
            this.tax= ((this.basicSalary-450000)*.15);
        }
        else{
            this.tax= ((this.basicSalary-700000)*.20);
        }
        this.InHandSalary = this.basicSalary+this.da+this.hra-this.pf-this.tax+this.ta;
    }

    

    // void printInfo(){
    //     System.out.println("Comapay:     "+Employee.companyName);
    //     System.out.println("Name:       "+this.name);
    //     System.out.println("ID:         "+this.EmpID);
    //     System.out.println("basic Salary:        "+this.basicSalary);
    //     System.out.println("Dept:        "+this.Dept);
    //     System.out.println("HRA:        "+this.hra);
    //     System.out.println("PF:          "+this.pf);
    //     System.out.println("TA:             "+this.ta);
    //     System.out.println("TAX:        "+this.tax);
    //     System.out.println("DA:          "+this.da);
    //     System.out.println("InHandSalary         "+(this.basicSalary+this.da+this.hra-this.pf-this.tax+this.ta));
    // }

}



public class Question {

    static void printSlip(Employee emp){
        System.out.println("*******************************SALARY-SLIP*******************************");
        System.out.println("Company Name : "+emp.companyName);
        System.out.println("Employee Name : "+emp.name);
        System.out.println("Employee ID : "+emp.ID);
        System.out.println("Department  : "+emp.Dept);
        System.out.println("Basic Salary : "+emp.basicSalary);
        System.out.println("HRA : "+emp.hra);
        System.out.println("PF : "+emp.pf);
        System.out.println("TA : "+emp.ta);
        System.out.println("DA : "+emp.da);
        System.out.println("TAX : "+emp.tax);
        System.out.println("In-Hand Salary  : "+emp.InHandSalary);
    }
    public static void main(String[] args) {
        Employee emp = new Employee("Asif",12,"Education",340000);
        Employee emp2 = new Employee("Tushar",13,"Teaching",500);
        
        printSlip(emp);

        
    }
}