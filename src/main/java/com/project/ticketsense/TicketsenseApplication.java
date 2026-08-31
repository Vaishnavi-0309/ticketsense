package com.project.ticketsense;

import com.project.ticketsense.config.GeminiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(GeminiProperties.class)
public class TicketsenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsenseApplication.class, args);
	}

}
