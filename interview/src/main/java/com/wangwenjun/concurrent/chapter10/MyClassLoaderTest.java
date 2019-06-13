package com.wangwenjun.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
	public static void main(String[] args) throws
			ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException, InvocationTargetException {


		// 知识点: 双亲委托机制(父委托)
		// 需要删除HelloWorld.class才可以使用MyClassLoader进行类加载
		/*
		// 声明一个MyClassLoader
		MyClassLoader classLoader = new MyClassLoader();
		// 使用MyClassLoader加载HelloWorld
		Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.HelloWorld");

		System.out.println(aClass.getClassLoader());
		 */

		// ① 注释
		/*
		Object helloWorld = aClass.newInstance();
		System.out.println(helloWorld);
		Method welcomeMethod = aClass.getMethod("welcome");
		String result = (String) welcomeMethod.invoke(helloWorld);
		System.out.println("Result:" + result);
		 */

		// 不删除HelloWorld.class的情况下，使用MyClassLoader进行类加载
		// 方法1: 使用ExtClassLoader作为MyClassLoader的父加载器
		/*
		ClassLoader extClassLoader = MyClassLoaderTest.class.getClassLoader().getParent();
		MyClassLoader classLoader = new MyClassLoader("F:\\classloader1", extClassLoader);
		Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.HelloWorld");
		System.out.println(aClass.getClassLoader());
		 */
		// 方法2: 直接使用根加载器对该类进行加载
		MyClassLoader classLoader = new MyClassLoader("F:\\classloader1", null);
		Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.HelloWorld");
		System.out.println(aClass.getClassLoader());
	}
}
