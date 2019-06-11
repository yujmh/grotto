package com.wangwenjun.concurrent.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassInit {

	static {
		try {
			System.out.println("The ClassInit static code block will be invoke.");
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	// 父类中有静态变量value
	static class Parent {
		static int value = 10;

		static {
			value = 20;
		}
	}

	// 子类使用父类的静态变量为自己的静态变量赋值
	static class Child extends Parent {
		static int i = value;
	}

	public static void main(String[] args) {
		// System.out.println(Child.i);
		IntStream.range(0, 5)
				.forEach(i -> new Thread(ClassInit::new));
	}
}
