package com.email;

import com.email.model.EnvironmentConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@Configuration
@PropertySource("classpath:application.properties")
public class EmailapiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EmailapiApplication.class, args);
		EnvironmentConfig env = ctx.getBean(EnvironmentConfig.class);
	}

	@Value("${spring.smtp.username}")
	public String userName;

	@Value("${spring.smtp.password}")
	public String password;

	@Bean
	public EnvironmentConfig getDetails() {
		EnvironmentConfig env = new EnvironmentConfig();
		env.setUserName(userName);
		env.setPassword(password);
		return env;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
