package com.konkuk.kureal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class KurealApplication {

	public static void main(String[] args) {
		SpringApplication.run(KurealApplication.class, args);
	}

}
