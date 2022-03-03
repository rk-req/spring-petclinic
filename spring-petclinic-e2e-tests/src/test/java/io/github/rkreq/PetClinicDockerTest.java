package io.github.rkreq;

import io.github.rkreq.docker.ChromiumWebDriverContainer;
import io.github.rkreq.docker.PetClinicContainer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PetClinicDockerTest {

	private static final Network network = Network.SHARED;

	@Container
	private static final PetClinicContainer petClinicContainer = new PetClinicContainer()
		.withNetwork(network);
	@Container
	private static final ChromiumWebDriverContainer webDriverContainer = new ChromiumWebDriverContainer()
		.withNetwork(network)
		;
	private WebDriver webDriver;
	private String serviceUrl;

	@BeforeEach
	public void open() {
		serviceUrl = petClinicContainer.getInternalUrl();
		webDriver = webDriverContainer.getRemoteWebdriver();
	}

	@Test
	void test() {
		webDriver.get(serviceUrl);
		Assertions.assertThat(webDriver.getTitle())
			.as("Page title")
			.isEqualTo("PetClinic :: a Spring Framework demonstration");
	}
}
