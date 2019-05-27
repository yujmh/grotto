package com.wangwenjun.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

public class ThreadHook {
	public static void main(String[] args) {
		// 为应用程序注入钩子线程
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("The hook thread 1 is running.");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 1 will exit.");
			}
		});

		// 钩子线程可注册多个
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("The hook thread 2 is running.");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The hook thread 2 will exit.");
			}
		});

		System.out.println("The program will is stopping.");
	}
}
