package vttp.ssf.Day19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day19Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Day19Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//this path is being hardcoded 
		String pathFileName = "/Users/joanna/Desktop/dataForWorkshopDay19/employees.json";
		
		File file = new File(pathFileName);
		InputStream is = new FileInputStream(file); //can also use fileReader if you want

		StringBuilder resultStringBuilder = new StringBuilder();

		InputStreamReader isr = new InputStreamReader(is);

		try(BufferedReader br = new BufferedReader(isr)){ //the try catch block will auto close 

			String line = "";
			while ((line=br.readLine()) != null) {
				resultStringBuilder.append(line);

				
			}

		}
		String data = resultStringBuilder.toString();
		
		System.out.println(data);




	}

	



}
