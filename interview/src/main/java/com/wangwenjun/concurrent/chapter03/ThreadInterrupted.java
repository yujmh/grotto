package com.wangwenjun.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
	public static void main(String[] args) {
		// ①判断当前线程是否被中断
		System.out.println("Main thread is interrupted? " + Thread.interrupted());

		// ②中断当前线程
		Thread.currentThread().interrupt();

		// ③判断当前线程是否已经被中断
		System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());

		try {
			// ④当前线程执行可中断方法
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			// ⑤捕获中断信号
			System.out.println("I will be interrupted still.");
		}
	}
}
