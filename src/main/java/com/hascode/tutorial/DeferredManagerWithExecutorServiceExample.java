package com.hascode.tutorial;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;

public class DeferredManagerWithExecutorServiceExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		DeferredManager dm = new DefaultDeferredManager(executorService);

		WebCrawler crawl1 = new WebCrawler();
		WebCrawler crawl2 = new WebCrawler();
		WebCrawler crawl3 = new WebCrawler();

		Promise<List<String>, String, Integer> promise1 = crawl1.promise();
		Promise<List<String>, String, Integer> promise2 = crawl2.promise();
		Promise<List<String>, String, Integer> promise3 = crawl3.promise();

		dm.when(promise1, promise2, promise3).progress((p) -> {
			System.out.println(
					"progress-stats done: " + p.getDone() + " failed: " + p.getFail() + " total: " + p.getTotal());
		}).done((r) -> {
			System.out.println("All promises completed: ");
			r.forEach((o) -> {
				System.out.println("result: " + o.getResult());
			});
		});

		crawl1.crawl("http://www.hascode.com/");
		crawl2.crawl("http://www.java.net/");
		crawl3.crawl("http://www.micha-kops.com/");
	}
}
