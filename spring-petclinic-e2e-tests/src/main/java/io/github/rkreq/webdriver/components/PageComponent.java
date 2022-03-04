package io.github.rkreq.webdriver.components;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public abstract class PageComponent<ThisType extends PageComponent<ThisType, ElementsType, AsserterType>,
	ElementsType extends PageElements,
	AsserterType extends PageElementsAssert<ElementsType, AsserterType>> {

	private final PageElementFactory pageElementFactory;
	private final ElementsType elements;

	public PageComponent(PageElementFactory pageElementFactory, ElementsType elements) {
		this.pageElementFactory = pageElementFactory;
		this.elements = elements;
	}

	public PageElementFactory getPageElementFactory() {
		return pageElementFactory;
	}

	public ElementsType pageElements() {
		return elements;
	}

	public WebDriver getWebDriver() {
		return getWebDriver();
	}

	public abstract AsserterType verify();

	@SuppressWarnings("unchecked")
	@NotNull
	protected ThisType myself() {
		return (ThisType) this;
	}

	public ThisType withValue(Function<ElementsType, PageElement> element, String value) {
		element.apply(pageElements()).withText(value);
		return myself();
	}
}
