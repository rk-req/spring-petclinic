package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;
import org.openqa.selenium.By;

public class OwnerInformationPage
	extends PageComponent<OwnerInformationPage, OwnerInformationPage.Elements, OwnerInformationPage.Asserter> {

	public OwnerInformationPage(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public interface Elements extends PageElements {

		default PageElement header() {
			return by(By.cssSelector("div.container > h2 "), "Page name header");
		}
	}

	public class Asserter extends PageElementsAssert<Elements, Asserter> {

		protected Asserter() {
			super(pageElements(), Asserter.class);
		}
	}

	@Override
	public Asserter verify() {
		return new Asserter();
	}
}
