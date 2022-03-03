package io.github.rkreq.webdriver;

import com.google.common.base.Suppliers;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PageElementList {

	private final WebDriver webDriver;
	private final Supplier<List<WebElement>> webElement;
	private final By locator;
	private final String elementDescription;

	public PageElementList(WebDriver webDriver, By locator, String elementDescription) {
		this.webDriver = webDriver;
		this.locator = locator;
		this.webElement = Suppliers.memoize(() -> webDriver.findElements(locator));
		this.elementDescription = elementDescription;
	}

	public List<WebElement> webElements() {
		return webElement.get();
	}

	public <T> List<T> getElementsProperty(Function<WebElement, T> propertyProvider) {
		return webElements().stream().map(propertyProvider).collect(Collectors.toList());
	}

	public List<String> getText() {
		return getElementsProperty(WebElement::getText);
	}

	public Asserter verify() {
		return new Asserter();
	}

	public class Asserter extends AbstractAssert<Asserter, PageElementList> {

		protected Asserter() {
			super(PageElementList.this, Asserter.class);
		}

		public ListAssert<String> textAssert() {
			return Assertions.assertThat(getText())
				.as("Text of " + elementDescription);
		}

		public Asserter containsOnlyText(String... expectedText) {
			textAssert().containsOnly(expectedText);
			return this;
		}
	}
}
