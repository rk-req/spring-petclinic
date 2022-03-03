package io.github.rkreq.webdriver.components;

import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.PageElementFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

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
}
