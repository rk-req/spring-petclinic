package io.github.rkreq.petclinic;

import io.github.rkreq.petclinic.model.FindOwnerPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetClinicAddOwnerTest extends PetclinicBaseTest {

	private FindOwnerPage findOwnerPage;

	@BeforeEach
	public void init() {
		findOwnerPage = petClinicPages.navigationBar().openFindOwners();
	}

	@Test
	public void shouldAddOwnerAndDisplayItsInformation() {
		String lastName = "Smith" + RandomStringUtils.randomAlphabetic(6);
		findOwnerPage
			.addOwner()
			.addOwner("John", lastName, "5th Avenue", "New York", "123456789")
			.verify()
			.assertOwnerInformation()
			.containsEntry("Name", "John " + lastName)
			.containsEntry("Address", "5th Avenue")
			.containsEntry("City", "New York")
			.containsEntry("Telephone", "123456789");
	}
}
