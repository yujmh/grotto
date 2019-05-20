package com.grotto.shiro.spring;


import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.config.ShiroConfiguration;
import org.springframework.context.annotation.*;

/**
 * Configuration of Shiro
 * {@link ShiroBeanConfiguration} Configures Shiro’s lifecycle and events
 * {@link ShiroConfiguration} Configures Shiro Beans (SecurityManager, SessionManager, etc)
 * {@link ShiroAnnotationProcessorConfiguration} Enables Shiro’s annotation processing
 * @author cc
 */
//@Configuration
//@Import({
//		ShiroBeanConfiguration.class,
//		ShiroConfiguration.class,
//		ShiroAnnotationProcessorConfiguration.class
//})
//@ComponentScan("com.grotto.shiro.spring")
public class SpringShiroConfiguration {

	/**
	 * Example hard coded Realm bean.
	 * @return hard coded Realm bean
	 */
	@Bean
	public Realm realm() {
		TextConfigurationRealm realm = new TextConfigurationRealm();

		realm.setUserDefinitions("joe.coder=password,user\n" +
				                 "jill.coder=password,admin");
		realm.setRoleDefinitions("admin=read,write\n" +
				                 "user=read");
		realm.setCachingEnabled(true);

		return realm;
	}
}