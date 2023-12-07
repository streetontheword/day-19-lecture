package vttp.ssf.Day19.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.Day19.model.Employee;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/home")
    public String home(HttpSession session, Model m){

        Employee employee1 = new Employee(12345, "Michael");
        Employee employee2 = new Employee(12346, "Michelle");
        Employee employee3 = new Employee(12347, "Myra");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        session.setAttribute("employees", employees); //you are creating a memory session 
        return "home";
    }

    @GetMapping("/nextpage")
    public String target(HttpSession session, Model model){ 
        
        List<Employee> employees = (List<Employee>)session.getAttribute("employees");
        model.addAttribute("employees", employees);
        return "target"; 
    }


    
}
