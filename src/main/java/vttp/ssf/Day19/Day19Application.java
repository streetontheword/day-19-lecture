package vttp.ssf.Day19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.Day19.model.Bag;
import vttp.ssf.Day19.model.Employee;
import vttp.ssf.Day19.repo.EmployeeRepo;

@SpringBootApplication
public class Day19Application implements CommandLineRunner {

	@Autowired
	EmployeeRepo empRepo;

	//this is a different example to illustrate qualifier 
	@Autowired @Qualifier("totebag") Bag bag; 


	public static void main(String[] args) {
		SpringApplication.run(Day19Application.class, args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... args) throws Exception {
		

		// this path is being hardcoded
		String pathFileName =
		"/Users/joanna/Desktop/dataForWorkshopDay19/employees.json";

		File file = new File(pathFileName);
		InputStream is = new FileInputStream(file); //can also use fileReader if you
		// want

		StringBuilder resultStringBuilder = new StringBuilder();

		InputStreamReader isr = new InputStreamReader(is);

		//Buffered reader reads line by line
		try(BufferedReader br = new BufferedReader(isr)){ //the try catch block will
		// auto close

		String line = "";
		while ((line=br.readLine()) != null) {
		resultStringBuilder.append(line);

		}

		}
		String data = resultStringBuilder.toString(); //this is a string

		System.out.println(data);

		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(data); //what is this ?????

		JSONArray jsonArray = (JSONArray)object;

		System.out.println("size of jsonArray " + jsonArray.size());
		System.out.println("jsonArrray list of object: " + jsonArray);
		// [{"employee":{"employeeName":"Dennis
		// Yap","employeeId":12345}},{"employee":{"employeeName":"Darren
		// Low","employeeId":12346}},{"employee":{"employeeName":"Derrick
		// Tan","employeeId":12347}}]

		// create an empty list
		List<Employee> employees = new ArrayList<>();

		System.out.println("------------------------A loop-----------------------------");
		jsonArray.forEach(emp -> {
		// System.out.println(emp);

		Employee emp1 = parseEmployeeObjectToJsonObject((JSONObject) emp);
		employees.add(emp1);
		});

		System.out.println("list of employees: " + employees);

		for(Employee e: employees){
		System.out.println(e.getEmpId() + ">>>>>" + e.getEmpName());
		empRepo.saveRecord(e);
		}

		//key is the employee id and value is the employee object
		Map<String, Employee> mapList = empRepo.getAll();
		System.out.println(mapList);

		Employee empRetrieved1 = empRepo.getRecord("12345");
		System.out.println("Retrieved employee: " + empRetrieved1.getEmpName() );

		Employee empRetrieved2 = empRepo.getRecord("12346");
		System.out.println("Retrieved employee: " + empRetrieved2.getEmpName() );

		Employee empRetrieved3 = empRepo.getRecord("12347");
		System.out.println("Retrieved employee: " + empRetrieved3.getEmpName() );

		bag.showBagType();	

		}

		

		private Employee parseEmployeeObjectToJsonObject(JSONObject jsonEmployee){
		Employee employee = new Employee();
		//convert from object to json object
		JSONObject jsonEmployeeObject = (JSONObject)jsonEmployee.get("employee");
		//this is base on the file
		System.out.println("this is a JSON object" + jsonEmployeeObject);

		System.out.println("name of employees: " +
		(jsonEmployeeObject.get("employeeName")));

		// jsonEmployeeObject.get("employeeId");
		// jsonEmployeeObject.get("employeeName");

		employee.setEmpName((jsonEmployeeObject.get("employeeName")).toString());
		employee.setEmpId(Integer.parseInt(jsonEmployeeObject.get("employeeId").toString()));

		return employee;
		}



		//this uses the jakarta glassfish 
		//thus, need to use JsonReader 
		// String pathFileName = "/Users/joanna/Desktop/dataForWorkshopDay19/employee2.json";

		// File file = new File(pathFileName);
		// InputStream is = new FileInputStream(file);
		// JsonReader jsonReader = Json.createReader(is);
		// JsonArray jsonArray = jsonReader.readArray(); //store a list of the values

		// System.out.println("jsonArray: " + jsonArray);

		// List<Employee> list = new ArrayList<>();

		// // for (int i=0; i<jsonArray.size(); i++){
		// // JsonValue jsonValue = jsonArray.get(i);
		// // System.out.println("json array: " + jsonValue);
		// for (JsonValue jsonValue: jsonArray){
		// 	JsonObject jsonObject = jsonValue.asJsonObject(); //this will cast it as a JSON object 

		// 	Employee emp = new Employee();
		// 	emp.setEmpId(Integer.valueOf(jsonObject.get("employeeId").toString()));
		// 	emp.setEmpName(jsonObject.get("employeeName").toString());
		// 	list.add(emp);
		// }

		// for(Employee e: list){
		// System.out.println(e.getEmpId() + ">>>>" + e.getEmpName());
		
		// }
		


		// }


		
		


		
	
	}

	


