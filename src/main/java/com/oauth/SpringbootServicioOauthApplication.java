package com.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
public class SpringbootServicioOauthApplication /*implements CommandLineRunner*/{

	@Value("${herokuNotIdle.url}")
	private String herokuNotIdle_url;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncode;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOauthApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Scheduled(cron = "${herokuNotIdle.cronExpression}")
	public void herokuNotIdle() {
		System.out.println(" INIT - Heroku not idle execution ");
		ResponseEntity<String> response = restTemplate().exchange(herokuNotIdle_url, HttpMethod.GET, null, String.class);
		System.out.println(response);
		System.out.println(" FINISH - Heroku not idle execution");
	}

	
//	@Override
//	public void run(String... args) throws Exception {
//		String password = "12345";
//		
//		for (int i = 0; i < 4; i++) {
//			String passwordBCrypt = passwordEncode.encode(password);
//			System.out.println(passwordBCrypt);
//		}
//		
//	}

}
