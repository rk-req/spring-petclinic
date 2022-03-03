package io.github.rkreq.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageElementFactory {

	private final WebDriver webDriver;

	public PageElementFactory(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public PageElement create(By locator, String elementDescription) {
		return new PageElement(webDriver, locator, elementDescription, this);
	}

	public PageElementList createList(By locator, String elementDescription) {
		return new PageElementList(webDriver,locator,elementDescription);
	}
}
