package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElementList;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class OwnersPageTable
	extends PageComponent<OwnersPageTable, OwnersPageTable.TableElements, OwnersPageTable.Asserter> {

	public OwnersPageTable(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public enum Header {
		Name(1),
		Address(2),
		City(3),
		Telephone(4),
		Pets(5);

		private final int columnIndex;

		Header(int columnIndex) {
			this.columnIndex = columnIndex;
		}
	}

	public OwnerInformationPage openOwnerInformation(String ownerName) {
		pageElements().cellWithValue(ownerName).click();
		return new OwnerInformationPage(getPageElementFactory());
	}

	public interface TableElements extends PageElements {

		default PageElement tableBody() {
			return by(cssSelector("#owners tbody"), "Owners table");
		}

		default PageElement row(int rowIndex) {
			return by(cssSelector("#owners tbody tr:nth-child(" + rowIndex + ") td"), "Owner table row " + rowIndex);
		}

		default PageElementList headers() {
			return listOf(cssSelector("#owners thead th"), "Owners table headers");
		}

		default PageElementList rowCells(int rowIndex) {
			return row(rowIndex).asList();
		}

		default PageElement cell(int rowIndex, Header tableHeader) {
			return cell(rowIndex, tableHeader.columnIndex);
		}

		default PageElement cell(int rowIndex, int columnIndex) {
			return by(cssSelector("#owners tbody tr:nth-child(" + rowIndex + ") td:nth-child(" + columnIndex + ")"),
				"Owner table cell (" + rowIndex + "," + columnIndex + ")");
		}

		default PageElementList rows() {
			return listOf(cssSelector("#owners > tbody > tr"), "Owner table rows");
		}

		default PageElement cellWithValue(String cellValue) {
			return by(xpath("//table[@id='owners']//tr/td[contains(.,'" + cellValue + "')]"),
				"Cell with value: " + cellValue);
		}
	}

	public class Asserter extends PageElementsAssert<TableElements, Asserter> {

		protected Asserter() {
			super(pageElements(), Asserter.class);
		}

		public Asserter hasNumberOfRows(int expectSize) {
			verifyElements(TableElements::rows).hasSize(expectSize);
			return this;
		}

		public Asserter hasRowValues(int rowIndex, String... expectedValues) {
			verifyElements(table -> table.rowCells(rowIndex))
				.containsOnlyText(expectedValues);
			return this;
		}

		public Asserter hasHeaders(String... expectedValues) {
			verifyElements(TableElements::headers)
				.containsOnlyText(expectedValues);
			return this;
		}

		public Asserter hasCellValue(int rowIndex, Header header, String expectedValue) {
			pageElements().cell(rowIndex, header).verify().hasText(expectedValue);

			return this;
		}

		public Asserter hasCellWithValue(String text) {
			pageElements().cellWithValue(text).isDisplayed();
			return this;
		}
	}

	@Override
	public Asserter verify() {
		return new Asserter();
	}
}
