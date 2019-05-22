package com.wangwenjun.concurrent.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {
	private Thread currentThread;

	private boolean locked = false;

	private final List<Thread> blockedList = new ArrayList<>();

	@Override
	public void lock() throws InterruptedException {
		synchronized (this) {// ①Lock()方法使用同步代码块的方式进行方法同步
			while (locked) { // ②如果当前锁已经被某个线程获得，则该线程将加入阻塞队列，并且使当前线程wait释放对this monitor的所有权
				// 暂存当前线程
				final Thread tempThread = currentThread();
				if (!blockedList.contains(tempThread)) {
					blockedList.add(tempThread);
				}
				try {
					this.wait();
				} catch (InterruptedException e) {
					// 如果当前线程在wait时被中断，则从blockedList中将其删除，避免内存泄露
					blockedList.remove(tempThread);
					// 继续抛出中断异常
					throw e;
				}
			}
			blockedList.remove(currentThread());   // ③
			this.locked = true;                    // ④
			this.currentThread = currentThread();  // ⑤
		}
	}

	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		synchronized (this) {
			if (mills <= 0) {    //①
				throw new IllegalArgumentException("mills " + mills + " less than or equals to 0");
			} else {
				long remainingMills = mills;
				long endMills = currentTimeMillis() + remainingMills;
				while (locked) {
					if (remainingMills <= 0) { //②如果remainingMills小于等于0，则意味着当前线程被其他县城唤醒或者在指定的wait时间到了之后还没有获得锁，这种情况下会抛出超时的异常。
						throw new TimeoutException("can not get the lock during " + mills + " ms.");
					}
					final Thread tempThread = currentThread();
					if (!blockedList.contains(tempThread)) {
						blockedList.add(tempThread);
					}
					try {
						this.wait(remainingMills); //③
					} catch (InterruptedException e) {
						blockedList.remove(tempThread);
						throw e;
					}
					remainingMills = endMills - currentTimeMillis(); //④
				}
				blockedList.remove(currentThread());    // ⑤
				this.locked = true;
				this.currentThread = currentThread();
			}
		}
	}

	@Override
	public void unlock() {
		synchronized (this) {
			if (currentThread == currentThread()) {    //①
				this.locked = false;     //②
				Optional.of(currentThread().getName() + " release the lock monitor.").ifPresent(System.out::println);
				this.notifyAll();        //③
			}
		}
	}

	@Override
	public List<Thread> getBlockedThreads() {
		return Collections.unmodifiableList(blockedList);
	}
}
