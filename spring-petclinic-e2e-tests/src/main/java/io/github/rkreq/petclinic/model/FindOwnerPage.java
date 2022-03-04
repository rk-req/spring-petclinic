package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class FindOwnerPage extends PageComponent<FindOwnerPage, FindOwnerPage.Elements, FindOwnerPage.Asserter> {

	public FindOwnerPage(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public OwnerInformationPage findOnlyOneOwner(String ownersLastName) {
		pageElements().lastNameInput().withText(ownersLastName);
		pageElements().findOwnerButton().click();
		return new OwnerInformationPage(getPageElementFactory());
	}

	public AddOwnerPage addOwner() {
		pageElements().addOwnerButton().click();
		return new AddOwnerPage(getPageElementFactory());
	}

	public interface Elements extends PageElements {

		default PageElement lastNameInput() {
			return by(id("lastName"), "Last name input");
		}

		default PageElement findOwnerButton() {
			return by(cssSelector("button[data-id='find-owner-btn']"), "Find owner button");
		}

		default PageElement addOwnerButton() {
			return by(cssSelector("a[data-id='add-owner-btn']"), "Add owner button");
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
