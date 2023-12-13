package by.dorogokupets.cvservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
public class CvServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvServiceApplication.class, args);
	}

}
