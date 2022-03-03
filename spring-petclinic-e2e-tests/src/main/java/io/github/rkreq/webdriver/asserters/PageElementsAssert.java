package io.github.rkreq.webdriver.asserters;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementList;
import io.github.rkreq.webdriver.PageElements;
import org.assertj.core.api.AbstractAssert;

import java.util.function.Consumer;
import java.util.function.Function;

public class PageElementsAssert<ElementsType extends PageElements, Self extends PageElementsAssert<ElementsType, Self>>
	extends AbstractAssert<Self, ElementsType> {

	private final PageAsserter pageAsserter;

	protected PageElementsAssert(ElementsType elementsType, Class<?> selfType) {
		super(elementsType, selfType);
		pageAsserter = new PageAsserter(actual.pageElementFactory().getWebDriver());
	}

	public Self verifyElement(Function<ElementsType, PageElement> element, Consumer<PageElement.Asserter> verifier) {
		verifier.accept(element.apply(actual).verify());
		return myself;
	}

	public PageElement.Asserter verifyElement(Function<ElementsType, PageElement> element) {
		return element.apply(actual).verify();
	}

	public PageElementList.Asserter verifyElements(Function<ElementsType, PageElementList> element) {
		return element.apply(actual).verify();
	}

	public Self hasPagePath(String expectedPath) {
		pageAsserter.hasPagePath(expectedPath);
		return myself;
	}

	public Self hasText(Function<ElementsType, PageElement> element, String expected) {
		verifyElement(element).hasText(expected);
		return myself;
	}

	public Self isDisplayed(Function<ElementsType, PageElement> element) {
		verifyElement(element).isDisplayed();
		return myself;
	}
}
