package ru.ulstu.is.IPLabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IpLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpLabsApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World")String name){
		return String.format("Hello %s!", name);
	}

	@GetMapping("/task")
	public String task(@RequestParam(value = "str")String str,
					   @RequestParam(value = "flag")Boolean flag){
		String str2 ="";
		if(flag) {
			str2 = str.toUpperCase();
		}else{
			str2=str.toLowerCase();
		}
		return String.format("Hello %s!", str2);
	}
}

