package vttp.ssf.Day19.repo;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import vttp.ssf.Day19.config.RedisConfig;
import vttp.ssf.Day19.model.Employee;

@Repository
public class EmployeeRepo {
        //can also insert object if you want 
        //why never use list operations???

    private String hashRef = "employees";
    @Resource(name="redisEmployeeTemplate") //injecting a bean(?)
    private HashOperations<String, String, Employee> hOps;


    //if you are using list operations to access redis data in a list 
    // private ListOperations<String, Employee> lOperations;

    public void saveRecord(Employee e){
        //EmpId is an integer so you need to .toString() it 
        hOps.put(hashRef, e.getEmpId().toString(), e);
       
    }

    public Employee getRecord(String id){
        Employee e = hOps.get(hashRef, id);

        return e; 
    }

    public Map<String, Employee> getAll(){
        Map<String, Employee> mapList = hOps.entries(hashRef);

        return mapList;
    } 

    


    
}
