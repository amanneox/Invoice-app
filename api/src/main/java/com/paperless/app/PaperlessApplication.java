package com.paperless.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages={"com.paperless.app.repository", "com.paperless.app.controller","com.paperless.app.service"}
)
@EnableJpaAuditing
public class PaperlessApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperlessApplication.class, args);
	}
}
