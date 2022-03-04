package io.github.rkreq.petclinic.model;

import io.github.rkreq.webdriver.PageElement;
import io.github.rkreq.webdriver.PageElementFactory;
import io.github.rkreq.webdriver.PageElementList;
import io.github.rkreq.webdriver.PageElements;
import io.github.rkreq.webdriver.asserters.PageElementsAssert;
import io.github.rkreq.webdriver.components.PageComponent;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.MapAssert;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OwnerInformationPage
	extends PageComponent<OwnerInformationPage, OwnerInformationPage.Elements, OwnerInformationPage.Asserter> {

	public OwnerInformationPage(PageElementFactory pageElementFactory) {
		super(pageElementFactory, () -> pageElementFactory);
	}

	public interface Elements extends PageElements {

		default PageElement header() {
			return by(By.cssSelector("div.container > h2 "), "Owner Info Page name header");
		}

		default PageElementList ownerInformationTableHeaders() {
			return listOf(By.cssSelector("table[data-id='owner-information-table'] tbody th"),
				"Owner info table headers");
		}

		default PageElementList ownerInformationTableValues() {
			return listOf(By.cssSelector("table[data-id='owner-information-table'] tbody td"),
				"Owner info table values");
		}
	}

	public Map<String, String> getOwnerInformation() {
		var headers = pageElements().ownerInformationTableHeaders().getText();
		var values = pageElements().ownerInformationTableValues().getText();
		Assertions.assertThat(headers).hasSameSizeAs(values);
		return IntStream.range(0, headers.size())
			.mapToObj(i -> Map.entry(headers.get(i), values.get(i)))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public class Asserter extends PageElementsAssert<Elements, Asserter> {

		protected Asserter() {
			super(pageElements(), Asserter.class);
		}

		public MapAssert<String, String> assertOwnerInformation() {
			return Assertions.assertThat(getOwnerInformation())
				.as("Owner information");
		}
	}

	@Override
	public Asserter verify() {
		return new Asserter();
	}
}
