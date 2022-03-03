package io.github.rkreq.petclinic;

import io.github.rkreq.petclinic.model.NavigationBar;
import io.github.rkreq.petclinic.model.WelcomePage;
import io.github.rkreq.petclinic.model.WelcomePage.WelcomePageElements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetClinicNavigationTest extends PetclinicBaseTest {

	private NavigationBar navigationBar;
	private WelcomePage welcomePage;

	@BeforeEach
	void init() {
		navigationBar = petClinicPages.navigationBar();
		welcomePage = petClinicPages.welcomePage();
	}

	@Test
	public void shouldStartWithWelcomePage() {
		welcomePage.verify()
			.hasText(WelcomePageElements::welcomeMessage, "Welcome")
			.verifyElement(WelcomePageElements::welcomeImage)
			.assertImageSrc().endsWith("/resources/images/pets.png");

		navigationBar.verify()
			.hasActiveNavigationLink("HOME");
	}

	@Test
	public void shouldContainsNavigationLinks() {
		navigationBar.verify()
			.hasNavigationLinks("HOME", "OWNERS", "FIND OWNERS", "VETERINARIANS", "ERROR");
	}

	@Test
	public void shouldOpenOwnersPage() {
		navigationBar.openOwners()
			.verify()
			.hasPagePath("/owners");
	}
}
