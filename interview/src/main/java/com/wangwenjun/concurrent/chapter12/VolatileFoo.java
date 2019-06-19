package com.wangwenjun.concurrent.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Tip：Updater线程的每一次修改都会使得Reader线程进行一次输出？
 * 事实上，如果不加volatile的情况下，Reader线程难以感知init_value的改变。
 */
public class VolatileFoo {
	// init_value 的最大值
	final static int MAX = 5;

	// init_value 的初始值
	static volatile int init_value = 0;

	public static void main(String[] args) {
		// 启动一个 Reader 线程，当发现 local_value 和 init_value 不同时，则输出 init_value 被修改的信息
		new Thread(() -> {
			int localValue = init_value;
			while (localValue < MAX) {
				if (init_value != localValue) {
					System.out.printf("The init_value is updated to [%d]\n", init_value);

					// 对 localValue 进行重新赋值
					localValue = init_value;
				}
			}
		}, "Reader").start();

		// 启动 Updater 线程，主要用于对 init_value 的修改，当 local_value >= 5 的时候则退出生命周期
		new Thread(() -> {
			int localValue = init_value;
			while (localValue < MAX) {
				// 修改 init_value
				System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
				init_value = localValue;

				try {
					// 短暂休眠，目的是为了使 Reader 线程能够来得及输出变化内容
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Updater").start();
	}

}
