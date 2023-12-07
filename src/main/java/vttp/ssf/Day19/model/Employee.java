package vttp.ssf.Day19.model;

import java.io.Serializable;


//serializable will automatic serialise the object 
//when you send data over the web, it has to be in a serialised format which is a series of strings
//the server that is taking this string has to convert it back to an object 
//

public class Employee implements Serializable{
    

    //why int primitive can work but java Integer cannot work? 

    private String empName; 
    private Integer empId;



    
    public Employee() {
    }


    public Employee(int empId,String empName) {
        this.empName = empName;
        this.empId = empId;
    }

    
    public String getEmpName() { return empName; }

    public void setEmpName(String empName) {this.empName = empName;}

    public Integer getEmpId() {return empId; }

    public void setEmpId(int empId) { this.empId = empId; }


    
   

    
    
}
