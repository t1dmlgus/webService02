package com.t1dmlgus.webService02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing			//JPA Auditing 활성화
@SpringBootApplication
public class WebService02Application {

	public static void main(String[] args) {
		SpringApplication.run(WebService02Application.class, args);
	}

}
