package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.asserters.PageAsserter;
import org.openqa.selenium.WebDriver;

public class PetClinicPages {

	private final WebDriver webDriver;
	private final PageElementFactory pageElementFactory;

	public PetClinicPages(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.pageElementFactory = new PageElementFactory(webDriver);
	}

	public NavigationBar navigationBar() {
		return new NavigationBar(pageElementFactory);
	}

	public WelcomePage welcomePage() {
		return new WelcomePage(pageElementFactory);
	}

	public PageAsserter pageAsserter() {
		return new PageAsserter(webDriver);
	}

	public OwnersPageTable ownersPageTable() {
		return new OwnersPageTable(pageElementFactory);
	}
}
