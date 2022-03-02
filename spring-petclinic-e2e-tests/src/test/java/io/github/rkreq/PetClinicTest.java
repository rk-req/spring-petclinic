package io.github.rkreq;

import io.github.rkreq.docker.ChromiumWebDriverContainer;
import org.assertj.core.api.Assertions;
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

import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = PetClinicApplication.class,
	args = "-Dspring.profiles.active=postgres")
@Testcontainers
public class PetClinicTest {

	@LocalServerPort
	private int port;

	@Container
	private static final ChromiumWebDriverContainer webDriverContainer = new ChromiumWebDriverContainer();
	private WebDriver webDriver;
	private String serviceUrl;

	@BeforeEach
	@SuppressWarnings("HttpUrlsUsage")
	public void open() {
		serviceUrl = String.format("http://%s:%d", "host.docker.internal", port);
		webDriver = webDriverContainer.getRemoteWebdriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5))
			.implicitlyWait(Duration.ofSeconds(5))
			.scriptTimeout(Duration.ofSeconds(5));
	}

	@Test
	void test() {
		webDriver.get(serviceUrl);
		Assertions.assertThat(webDriver.getTitle())
			.as("Page title")
			.isEqualTo("PetClinic :: a Spring Framework demonstration");
	}
}
