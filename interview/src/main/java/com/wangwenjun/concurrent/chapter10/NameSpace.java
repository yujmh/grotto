package com.wangwenjun.concurrent.chapter10;

public class NameSpace {
	public static void main(String[] args) throws ClassNotFoundException {
		// 获取系统类加载器
		ClassLoader classLoader = NameSpace.class.getClassLoader();
		Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.Test");
		Class<?> bClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.Test");
		System.out.println(aClass.hashCode());
		System.out.println(bClass.hashCode());
		System.out.println(aClass == bClass);
	}
}
