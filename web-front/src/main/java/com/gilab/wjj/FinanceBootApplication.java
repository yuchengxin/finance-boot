package com.gilab.wjj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;

/**
 * Created by yuankui on 10/31/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.gilab.wjj"})
@SpringBootApplication
public class FinanceBootApplication implements Runnable {

	private String[] appArgs;
	public String[] getAppArgs() {
		return appArgs;
	}

	public void setAppArgs(String[] appArgs) {
		this.appArgs = appArgs;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}

	public static void main(String[] args) throws IOException {
		FinanceBootApplication app = new FinanceBootApplication();
		app.setAppArgs(args);
		app.run();
	}

	@Override
	public void run() {
		ApplicationContext context = new SpringApplicationBuilder(FinanceBootApplication.class)
				.initializers()
				.run(getAppArgs());
	}

}
