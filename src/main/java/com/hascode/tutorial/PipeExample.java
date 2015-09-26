package com.hascode.tutorial;

import java.util.List;

import org.jdeferred.DonePipe;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

public class PipeExample {

	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		final Promise<List<String>, String, Integer> promise = crawler.promise()
				.progress((i) -> System.out.println("Progress: " + i + "%")).done((keywords) -> {
					System.out.println("Done, " + keywords.size() + " keywords found: ");
					keywords.forEach(System.out::println);
				}).fail((msg) -> System.out.println("error: " + msg));
		promise.then(new DonePipe<List<String>, List<String>, Exception, Integer>() {
			@Override
			public Promise<List<String>, Exception, Integer> pipeDone(List<String> result) {
				if (result.size() < 6) {
					System.out.println("more results needed");
					return new DeferredObject<List<String>, Exception, Integer>()
							.reject(new Exception("more results needed"));
				}
				return new DeferredObject<List<String>, Exception, Integer>().resolve(result);
			}

		});
		crawler.crawl("http://www.hascode.com/");
	}
}
