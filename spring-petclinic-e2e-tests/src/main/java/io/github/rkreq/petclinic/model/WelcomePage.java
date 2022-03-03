package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import org.openqa.selenium.By;

public class WelcomePage extends PageComponent<WelcomePage, WelcomePage.WelcomePageElements, WelcomePage.Asserter> {

	public WelcomePage(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () ->pageElementFactory);
	}

	@Override
	public Asserter verify() {
		return new Asserter();
	}

	public interface WelcomePageElements extends PageElements {

		default PageElement welcomeMessage() {
			return by(By.cssSelector("div.container-fluid h2"), "Welcome message");
		}

		default PageElement welcomeImage() {
			return by(By.cssSelector("div.container-fluid img.img-responsive"), "Welcome message");
		}
	}

	public class Asserter extends PageElementsAssert<WelcomePageElements, Asserter> {

		protected Asserter() {
			super(pageElements(), Asserter.class);
		}
	}
}
