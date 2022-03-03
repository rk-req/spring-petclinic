package io.github.rkreq.petclinic;

import io.github.rkreq.petclinic.model.NavigationBar;
import io.github.rkreq.petclinic.model.OwnerInformationPage;
import io.github.rkreq.petclinic.model.OwnersPageTable;
import io.github.rkreq.petclinic.model.OwnersPageTable.Header;
import io.github.rkreq.petclinic.model.OwnersPageTable.TableElements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetClinicOwnersPageTableTest extends PetclinicBaseTest {

	private NavigationBar navigationBar;
	private OwnersPageTable ownersPageTable;

	@BeforeEach
	void init() {
		navigationBar = petClinicPages.navigationBar();
		navigationBar.openOwners();
		ownersPageTable = petClinicPages.ownersPageTable();
	}

	@Test
	public void shouldShowTable() {
		ownersPageTable.verify()
			.isDisplayed(TableElements::tableBody)
			.hasHeaders("Name", "Address", "City", "Telephone", "Pets");
	}

	@Test
	public void shouldContainProperValuesInTable() {
		ownersPageTable.verify()
			.hasNumberOfRows(5)
			.hasRowValues(1, "George Franklin", "110 W. Liberty St.", "Madison", "6085551023", "Leo")
			.hasCellValue(3, Header.City, "McFarland");
	}

	@Test
	public void shouldOpenOwnerDetails() {
		//given
		ownersPageTable.verify()
			.hasCellWithValue("Harold Davis");
		//when
		var ownerInformationPage = ownersPageTable.openOwnerInformation("Harold Davis");

		//then
		ownerInformationPage.verify()
			.hasText(OwnerInformationPage.Elements::header, "Owner Information");
	}
}
