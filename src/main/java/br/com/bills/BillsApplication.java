package br.com.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.bills")
public class BillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsApplication.class, args);
	}

}
