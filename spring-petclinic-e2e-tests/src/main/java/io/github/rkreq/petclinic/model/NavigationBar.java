package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElementList;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;

import static org.openqa.selenium.By.cssSelector;

public class NavigationBar extends PageComponent<NavigationBar, NavigationBar.NavigationBarElements,NavigationBar.Asserter> {

	public NavigationBar(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public Asserter verify() {
		return new Asserter();
	}

	public WelcomePage openHome() {
		pageElements().homeLink().click();
		return new WelcomePage(getPageElementFactory());
	}

	public OwnersPageTable openOwners() {
		pageElements().ownersLink().click();
		return new OwnersPageTable(getPageElementFactory());
	}

	public FindOwnerPage openFindOwners() {
		pageElements().findOwnersLink().click();
		return new FindOwnerPage(getPageElementFactory());
	}

	public NavigationBar openVeterinarians() {
		pageElements().veterinariansLink().click();
		return this;
	}

	public NavigationBar openError() {
		pageElements().errorLink().click();
		return this;
	}

	interface NavigationBarElements extends PageElements {

		default PageElementList navigationLinks() {
			return listOf(cssSelector("#main-navbar li.nav-item"), "Navigation bar links");
		}

		default PageElement homeLink() {
			return by(cssSelector("#main-navbar a[title='owners list'].nav-link"), "Nav bar Owners");
		}

		default PageElement ownersLink() {
			return by(cssSelector("#main-navbar a[title='owners list'].nav-link"), "Nav bar Owners");
		}

		default PageElement findOwnersLink() {
			return by(cssSelector("#main-navbar a[title='find owners'].nav-link"), "Nav bar Find Owners");
		}

		default PageElement veterinariansLink() {
			return by(cssSelector("#main-navbar a[title='veterinarians'].nav-link"), "Nav bar veterinarians");
		}

		default PageElement errorLink() {
			return by(cssSelector("#main-navbar a[href='/oups'].nav-link"), "Nav bar errorLink");
		}

		default PageElement activeLink() {
			return by(cssSelector("#main-navbar a.nav-link.active"), "Nav bar active link");
		}
	}

	public class Asserter extends PageElementsAssert<NavigationBar.NavigationBarElements,  Asserter> {

		protected Asserter() {
			super(pageElements(), Asserter.class);
		}

		public Asserter hasNavigationLinks(String... linksNames) {
			pageElements().navigationLinks()
				.verify()
				.containsOnlyText(linksNames);
			return this;
		}

		public Asserter hasActiveNavigationLink(String linkName) {
			return hasText(NavigationBarElements::activeLink, linkName);
		}
	}
}
