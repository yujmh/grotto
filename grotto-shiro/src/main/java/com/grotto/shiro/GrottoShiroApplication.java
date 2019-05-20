package com.grotto.shiro;

import com.grotto.shiro.spring.QuickStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrottoShiroApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GrottoShiroApplication.class, args);
//		context.getBean(QuickStart.class).run();
	}

}
