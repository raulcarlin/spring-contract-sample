package com.contract.consumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
class ConsumerApplicationMockTests {

	@Autowired
	private SampleClient client;

	@Test
	void testSampleRequest() {
		WireMock.stubFor(
				WireMock.get(WireMock.urlEqualTo("/sample"))
						.willReturn(
								WireMock.aResponse()
										.withBody("[{\"name\": \"Raul\",\"age\": 30}," +
												"{\"name\": \"Henrique\",\"age\": 4}]")
										.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
										.withStatus(HttpStatus.OK.value())));

		var response = client.getSamples();

		Assertions.assertEquals(2, response.size());
	}

}
