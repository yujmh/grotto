package com.wangwenjun.concurrent.chapter02;

/**
 * 2.6.1 什么是守护线程？
 */
public class DaemonThread {

	public static void main(String[] args) throws InterruptedException {

		// ①main线程开始

		Thread thread = new Thread(() -> {
			while (true){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread.setDaemon(true);	// ②将thread设置为守护线程

		thread.start();	// ③启动thread线程
		Thread.sleep(2000L);
		System.out.println("Main thread finished lifecycle.");
		// ④main线程结束

	}

}
