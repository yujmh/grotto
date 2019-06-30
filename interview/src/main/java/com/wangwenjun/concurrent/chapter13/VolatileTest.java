package com.wangwenjun.concurrent.chapter13;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {
	// 使用 volatile 修饰共享资源i
	private static volatile int i = 0;

	private static final CountDownLatch latch = new CountDownLatch(10);

	public static void inc() {
		i++;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int x = 0; x < 1000; x++) {
					inc();
				}
				// 使计数器减1
				latch.countDown();
			}).start();
		}
		latch.await();
		System.out.println(i);
	}
}