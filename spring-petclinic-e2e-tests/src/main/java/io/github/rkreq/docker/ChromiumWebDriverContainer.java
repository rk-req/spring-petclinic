package io.github.rkreq.docker;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

public class ChromiumWebDriverContainer extends GenericContainer<ChromiumWebDriverContainer> {

	private static final Logger log = LoggerFactory.getLogger(ChromiumWebDriverContainer.class);

	private static final long GB = 1024 * 1024 * 1024;
	private URI seleniumUrl;
	private String vncUrl;
	private String noVncUrl;
	private final ChromeOptions capabilities = new ChromeOptions();

	public ChromiumWebDriverContainer() {
		super("seleniarm/standalone-chromium:4.1.2-20220227");
		setExposedPorts(List.of(4444, 5900, 7900));
		setShmSize(2 * GB);
		setEnv(List.of("VNC_NO_PASSWORD=1"));
	}

	@Override
	protected void containerIsStarting(InspectContainerResponse containerInfo) {
		super.containerIsStarting(containerInfo);
		seleniumUrl = URI.create("http://localhost:" + getMappedPort(4444));
		vncUrl = "vnc://localhost:" + getMappedPort(5900);
		noVncUrl = "http://localhost:" + getMappedPort(7900);
	}

	@Override
	protected void containerIsStarted(InspectContainerResponse containerInfo) {
		log.info("Webdriver started. Selenium url: {}", seleniumUrl);
		log.info("VNC url: {}", vncUrl);
		log.info("NO VNC url: {}", noVncUrl);
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(URI.create(noVncUrl+"/?autoconnect=true"));
			} catch (IOException e) {
				throw new RuntimeException(e);

			}
		}
	}

	public WebDriver getRemoteWebdriver() {
		try {
			return new RemoteWebDriver(seleniumUrl.toURL(), capabilities);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
