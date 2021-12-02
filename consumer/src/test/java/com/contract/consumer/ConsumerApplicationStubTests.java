package com.contract.consumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.contract:producer:+:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ConsumerApplicationStubTests {

    @Autowired
    private SampleClient client;

    @Test
    void testSampleRequest() {
        var response = client.getSamples();

        assertThat(response.get(0).getName()).isEqualTo("Raul");
        assertThat(response.size()).isEqualTo(3);
    }

}
