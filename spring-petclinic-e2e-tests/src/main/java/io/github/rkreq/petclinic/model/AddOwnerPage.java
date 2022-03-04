package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;
import org.openqa.selenium.By;

public class AddOwnerPage extends PageComponent<AddOwnerPage, AddOwnerPage.Elements, AddOwnerPage.Asserter> {

	public AddOwnerPage(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public interface Elements extends PageElements {

		default PageElement firstNameInput() {
			return by(By.id("firstName"), "First name");
		}

		default PageElement lastNameInput() {
			return by(By.id("lastName"), "Last name");
		}

		default PageElement addressInput() {
			return by(By.id("address"), "Last name");
		}

		default PageElement cityInput() {
			return by(By.id("city"), "City");
		}

		default PageElement telephoneInput() {
			return by(By.id("telephone"), "Telephone");
		}

		default PageElement addOwnerButton() {
			return by(By.cssSelector("div.form-group button[type='submit']"), "Add owner button");
		}
	}

	public OwnerInformationPage addOwner(String firstName, String lastName, String address, String city,
		String phone) {
		withValue(Elements::firstNameInput, firstName);
		withValue(Elements::lastNameInput, lastName);
		withValue(Elements::addressInput, address);
		withValue(Elements::cityInput, city);
		withValue(Elements::telephoneInput, phone);
		pageElements().addOwnerButton().click();
		return new OwnerInformationPage(getPageElementFactory());
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
