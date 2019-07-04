package com.wangwenjun.concurrent.chapter13;

public class ThreadCloseable extends Thread {
	// volatile 关键字保证了started线程的可见性
	private boolean started = true;

	@Override
	public void run() {
		while (started) {
			// do work
		}
	}

	public void shutdown() {
		this.started = false;
	}
}
