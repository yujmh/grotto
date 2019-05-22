package com.wangwenjun.concurrent.chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {
	public static void main(String[] args) {
		final EventQueue eventQueue = new EventQueue();

		new Thread(() -> {
			for (; ; ) {
				eventQueue.offer(new EventQueue.Event());
			}
		}).start();

		new Thread(() -> {
			for (; ; ) {
				eventQueue.take();
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
