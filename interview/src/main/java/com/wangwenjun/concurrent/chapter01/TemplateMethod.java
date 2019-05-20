package com.wangwenjun.concurrent.chapter01;

/**
 * 模板设计模式
 * <p>
 * 例如，Thread的start0()方法调用run()方法，run()方法由子类实现
 * 父类编写算法结构代码，子类实现逻辑细节。
 * </p>
 */
public class TemplateMethod {

	public final void print(String message) {
		System.out.println("##################################");
		wrapPrint(message);
		System.out.println("##################################");
	}

	protected void wrapPrint(String message) {

	}

	public static void main(String[] args) {
		TemplateMethod t1 = new TemplateMethod() {
			@Override
			protected void wrapPrint(String message) {
				System.out.println("*" + message + "*");
			}
		};
		t1.print("Hello Thread");

		TemplateMethod t2 = new TemplateMethod() {
			@Override
			protected void wrapPrint(String message) {
				System.out.println("+" + message + "+");
			}
		};
		t2.print("Hello Thread");
	}
}
