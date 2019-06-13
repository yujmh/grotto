package com.wangwenjun.concurrent.chapter10;

public class ExtClassLoader {
	public static void main(String[] args) {
		// 扩展类加载器所加载的类库可以通过系统属性 java.ext.dirs 获得
		System.out.println(System.getProperty("java.ext.dirs"));
	}
}
