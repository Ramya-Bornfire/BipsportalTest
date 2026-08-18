package com.bornfire;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.bornfire")
@EnableScheduling
@EnableAsync
public class MerchantApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MerchantApplication.class, args);
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	  @Bean
	    public MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	        // Set the maximum file size to 10MB
	        factory.setMaxFileSize(DataSize.ofMegabytes(10));
	        // Set the maximum request size to 10MB
	        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
	        return factory.createMultipartConfig();
	    }
}
