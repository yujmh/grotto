package com.wangwenjun.concurrent.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {
	public static void main(String[] args) throws InterruptedException {
		// ①定义两个线程，并保存在threads中
		List<Thread> threads = IntStream.range(1, 3)
				.mapToObj(ThreadJoin::create).collect(Collectors.toList());

		// ②启动这两个线程
		threads.forEach(Thread::start);

		// ③执行这两个线程的join方法
		for (Thread t : threads) {
			t.join();
		}

		// ④main线程循环输出
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "#" + i);
			shortSleep();
		}
	}

	// 构建一个简单的线程，每个线程只是简单的循环输出
	private static Thread create(int seq) {
		return new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "#" + i);
				shortSleep();
			}
		}, String.valueOf(seq));
	}

	private static void shortSleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
