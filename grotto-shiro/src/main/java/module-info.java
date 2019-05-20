open module com.grotto.shiro {

	requires java.annotation;

	requires shiro.core;
	requires shiro.spring;

	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.boot.starter.log4j2;
	requires spring.core;
	requires spring.context;
	requires org.apache.logging.log4j;

	exports com.grotto.shiro;
}