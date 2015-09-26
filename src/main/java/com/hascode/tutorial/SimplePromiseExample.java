package com.hascode.tutorial;

public class SimplePromiseExample {

	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		crawler.promise().progress((i) -> System.out.println("Progress: " + i + "%")).done((keywords) -> {
			System.out.println("Done, " + keywords.size() + " keywords found: ");
			keywords.forEach(System.out::println);
		});
		crawler.crawl("http://www.hascode.com/");
	}
}
