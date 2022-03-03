package io.github.rkreq.webdriver.asserters;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.UriAssert;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class PageAsserter extends AbstractAssert<PageAsserter, WebDriver> {

	private final WebDriver webDriver;

	public PageAsserter(WebDriver actual) {
		super(actual, PageAsserter.class);
		this.webDriver = actual;
	}

	public PageAsserter hasPagePath(String expectedPath) {
		getCurrentUrlAssert().hasPath(expectedPath);
		return myself;
	}

	private UriAssert getCurrentUrlAssert() {
		return this.as("Current url path")
			.extracting(w -> URI.create(w.getCurrentUrl()), UriAssert::new);
	}
}
