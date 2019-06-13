package com.wangwenjun.concurrent.chapter10;

public class LoadSimpleClass {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("F:\\classloader2");
		Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.SimpleClass");
		System.out.println(classLoader.getParent());
		aClass.newInstance();
	}
}
