package com.contract.producer;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(properties = "server.port = 0",
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProducerApplicationTests {

	@MockBean
	private SampleRepository sampleRepository;

	@Autowired
	private SampleController sampleController;

	@LocalServerPort
	private int testPort;

	@BeforeEach
	void configTest() {
		Mockito.when(sampleRepository.getSamples())
				.thenReturn(List.of(
						new SampleResponse("Raul", 30),
						new SampleResponse("Ana", 30),
						new SampleResponse("Henrique", 4)
				));

		RestAssured.baseURI = "http://localhost:" + testPort;
		RestAssuredMockMvc.standaloneSetup(sampleController);
	}

}
