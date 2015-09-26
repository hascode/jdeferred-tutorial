package com.hascode.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

public class Main {

	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		crawler.promise().progress((i) -> System.out.println("Progress: " + i + "%")).done((keywords) -> {
			System.out.println("Done, " + keywords.size() + " keywords found: ");
			keywords.forEach(System.out::println);
		});
		crawler.crawl("http://www.hascode.com/");
	}

	private static class WebCrawler {
		private final Deferred<List<String>, String, Integer> deferred = new DeferredObject<>();
		private List<String> keywordsFound = new ArrayList<>();

		public void crawl(String url) {
			initializeCrawler(); // fake
			deferred.notify(10);
			fetchSiteContent(url);// fake
			deferred.notify(20);
			filterDuplicateKeywords();// fake
			deferred.notify(80);
			storeKeywords();// fake
			deferred.resolve(keywordsFound);
		}

		public Promise<List<String>, String, Integer> promise() {
			return deferred.promise();
		}

		private void storeKeywords() {
			keywordsFound.add("foo");
			keywordsFound.add("bar");
			keywordsFound.add("baz");
		}

		private void filterDuplicateKeywords() {
		}

		private void fetchSiteContent(String url) {
		}

		private void initializeCrawler() {
		}
	}
}
