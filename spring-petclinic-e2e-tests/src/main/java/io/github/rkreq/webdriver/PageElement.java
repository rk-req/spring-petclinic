package io.github.rkreq.webdriver;

import com.google.common.base.Suppliers;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.BooleanAssert;
import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;
import java.util.function.Supplier;

public class PageElement {

	private final WebDriver webDriver;
	private final Supplier<WebElement> webElement;
	private final By locator;
	private final String elementDescription;
	private final PageElementFactory pageElementFactory;

	public PageElement(WebDriver webDriver, By locator, String elementDescription,
		PageElementFactory pageElementFactory) {
		this.webDriver = webDriver;
		this.locator = locator;
		this.webElement = Suppliers.memoize(() -> webDriver.findElement(locator));
		this.elementDescription = elementDescription;
		this.pageElementFactory = pageElementFactory;
	}

	public WebElement webElement() {
		return webElement.get();
	}

	public String getText() {
		return webElement().getText();
	}

	public PageElement click() {
		webElement().click();
		return this;
	}

	public PageElement withText(String text) {
		webElement().sendKeys(text);
		return this;
	}

	public boolean isDisplayed() {
		return webElement().isDisplayed();
	}

	public String getAttribute(String attributeName) {
		return webElement().getAttribute(attributeName);
	}

	public String getImageSrc() {
		return getAttribute("src");
	}

	public String getTagName() {
		return webElement().getTagName();
	}

	public PageElementList asList() {
		return pageElementFactory.createList(locator, elementDescription);
	}

	public Asserter verify() {
		return new Asserter();
	}

	public class Asserter extends AbstractAssert<Asserter, PageElement> {

		protected Asserter() {
			super(PageElement.this, Asserter.class);
		}

		protected StringAssert assertStringProperty(String propertyName,
			Function<PageElement, String> propertyProvider) {
			return as(() -> elementDescription + " " + propertyName)
				.extracting(propertyProvider, StringAssert::new);
		}

		protected BooleanAssert assertBooleanProperty(String propertyName,
			Function<PageElement, Boolean> propertyProvider) {
			return as(() -> elementDescription + " " + propertyName)
				.extracting(propertyProvider, BooleanAssert::new);
		}

		public StringAssert assertText() {
			return assertStringProperty("text", PageElement::getText);
		}

		public Asserter hasText(String expectedText) {
			assertText().isEqualTo(expectedText);
			return this;
		}

		public StringAssert assertAttribute(String attributeName) {
			return assertStringProperty(" attribute " + attributeName, p -> p.getAttribute(attributeName));
		}

		public Asserter hasAttribute(String attributeName, String expectedValue) {
			assertAttribute(attributeName).isEqualTo(expectedValue);
			return this;
		}

		public Asserter hasTag(String expectedTagName) {
			assertStringProperty("tag name", PageElement::getTagName)
				.isEqualTo(expectedTagName);
			return this;
		}

		public StringAssert assertImageSrc() {
			return hasTag("img").assertAttribute("src");
		}

		public Asserter isDisplayed() {
			assertBooleanProperty("displayed", PageElement::isDisplayed)
				.isTrue();
			return this;
		}
	}
}
