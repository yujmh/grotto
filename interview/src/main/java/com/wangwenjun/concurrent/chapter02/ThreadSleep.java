package com.wangwenjun.concurrent.chapter02;

public class ThreadSleep {
	public static void main(String[] args) {
		new Thread(() -> {
			long startTime = System.currentTimeMillis();
			sleep(2000L);
			long endTime = System.currentTimeMillis();
			System.out.println(String.format("Total spend %d ms", (endTime - startTime)));
		}).start();

		long startTime = System.currentTimeMillis();
		sleep(3000L);
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Main thread spend %d ms", (endTime - startTime)));
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
