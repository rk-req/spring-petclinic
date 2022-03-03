package io.github.rkreq.docker;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

public class PetClinicContainer extends GenericContainer<PetClinicContainer> {

	private static final Logger log = LoggerFactory.getLogger(PetClinicContainer.class);
	private static final int SERVER_PORT = 8080;
	public static final String SERVER_NAME = "petclinic";

	public PetClinicContainer() {
		super("rk-req/spring-petclinic:2.6.0-SNAPSHOT");
		withExposedPorts(SERVER_PORT);
		withAccessToHost(true);
		withCreateContainerCmdModifier( cmd -> cmd.withHostName(SERVER_NAME));
		withNetworkAliases(SERVER_NAME);
	}

	@Override
	protected void containerIsStarted(InspectContainerResponse containerInfo,
		boolean reused) {
		log.info("Successfully started PetClinic server in Docker. External Url: {}", getExternalUrl());
		;
	}

	public String getInternalUrl() {
		return "http://" + SERVER_NAME + ":" + SERVER_PORT;
	}

	public String getExternalUrl() {
		return "http://localhost:" + getMappedPort(SERVER_PORT);
	}
}
