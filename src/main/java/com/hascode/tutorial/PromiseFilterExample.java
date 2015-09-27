package com.hascode.tutorial;

import java.util.Collections;
import java.util.List;

import org.jdeferred.Promise;

public class PromiseFilterExample {

	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		Promise<List<String>, String, Integer> promise = crawler.promise()
				.progress((i) -> System.out.println("Progress: " + i + "%")).done((keywords) -> {
					System.out.println("Done, " + keywords.size() + " keywords found: ");
					keywords.forEach(System.out::println);
				});

		Promise<List<String>, String, Integer> filteredPromise = promise.then((keywords) -> Collections.sort(keywords));
		filteredPromise.done((keywords) -> {
			System.out.println("Done (sorted by filter), " + keywords.size() + " keywords found: ");
			keywords.forEach(System.out::println);
		});

		crawler.crawl("http://www.hascode.com/");
	}
}
