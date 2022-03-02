package io.github.rkreq;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.hamcrest.Matchers.equalTo;

@Testcontainers
public class InfrastructureTest {

	@SuppressWarnings("rawtypes")
	@Container
	public static final GenericContainer echoServer =
		new GenericContainer(DockerImageName.parse("ealen/echo-server:latest"))
			.withExposedPorts(80);
	private int port;

	@BeforeEach
	void init() {
		port = echoServer.getMappedPort(80);
	}

	@Test
	void firstTest() {
		request()
			.get("/test?query=param")
			.then()
			.statusCode(200)
			.body("host.hostname", equalTo("localhost"))
			.body("http.method", equalTo("GET"))
			.body("params.0", equalTo("GET"));
	}

	private RequestSpecification request() {
		return RestAssured.given()
			.baseUri("http://localhost:"+port)
			.log().all().then().log().all().given();
	}
}
