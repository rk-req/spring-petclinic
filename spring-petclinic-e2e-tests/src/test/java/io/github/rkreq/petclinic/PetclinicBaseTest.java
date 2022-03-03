package io.github.rkreq.petclinic;

import io.github.rkreq.docker.ChromiumWebDriverContainer;
import io.github.rkreq.petclinic.model.PetClinicPages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = PetClinicApplication.class)
@Testcontainers
public class PetclinicBaseTest {

	@LocalServerPort
	private int port;

	@Container
	private static final ChromiumWebDriverContainer webDriverContainer = new ChromiumWebDriverContainer();
	protected static WebDriver webDriver;
	protected String serviceUrl;
	protected PetClinicPages petClinicPages;

	@BeforeAll
	public static void initWebdriver() {
		webDriver = webDriverContainer.getRemoteWebdriver();

	}
	@BeforeEach
	@SuppressWarnings("HttpUrlsUsage")
	public void open() {
		serviceUrl = String.format("http://%s:%d", "host.docker.internal", port);
		webDriver.get(serviceUrl);
		petClinicPages = new PetClinicPages(webDriver);
	}

}

