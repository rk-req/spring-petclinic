package io.github.rkreq.webdriver;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElementList;
import org.openqa.selenium.By;

public interface PageElements {

	PageElementFactory pageElementFactory();

	default PageElement by(By locator, String elementDescription) {
		return pageElementFactory().create(locator,elementDescription);
	}

	default PageElementList listOf(By locator, String elementDescription) {
		return pageElementFactory().createList(locator,elementDescription);
	}
}
