package com.wangwenjun.concurrent.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {
	// ①合作的各大航空公司
	private static List<String> fightCompany = Arrays.asList(
			"CSA", "CEA", "HNA"
	);

	private static List<String> search(String original, String dest) {
		final List<String> result = new ArrayList<>();

		// ②创建查询航班信息的线程列表
		List<FightQueryTask> tasks = fightCompany.stream()
				.map(f -> createSearchTask(f, original, dest))
				.collect(Collectors.toList());

		// ③分别启动这几个线程
		tasks.forEach(Thread::start);

		// ④分别调用每一个线程的join方法，阻塞当前线程
		tasks.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// ⑤在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中
		tasks.stream().map(FightQuery::get).forEach(result::addAll);

		return result;
	}

	private static FightQueryTask createSearchTask(String fight, String original, String dest) {
		return new FightQueryTask(fight, original, dest);
	}

	public static void main(String[] args) {
		List<String> result = search("SH", "BJ");
		System.out.println("====================================================");
		result.forEach(System.out::println);
	}
}
