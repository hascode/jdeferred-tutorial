package com.hascode.tutorial;

public class SimplePromiseExample {

	public static void main(String[] args) {
		System.out.println("## Example 1");
		Multiplier m = new Multiplier(5);
		m.promise().progress(System.out::println).done((res) -> System.out.println("the result is: " + res));
		m.multiplyNTimes(4);

		System.out.println("## Example 2");
		WebCrawler crawler = new WebCrawler();
		crawler.promise().progress((i) -> System.out.println("Progress: " + i + "%")).done((keywords) -> {
			System.out.println("Done, " + keywords.size() + " keywords found: ");
			keywords.forEach(System.out::println);
		});
		crawler.crawl("http://www.hascode.com/");

	}
}
