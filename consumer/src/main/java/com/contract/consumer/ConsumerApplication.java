package com.contract.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

}

@RestController
class OtherSample {

	private SampleClient sampleClient;

	public OtherSample(SampleClient sampleClient) {
		this.sampleClient = sampleClient;
	}

	@GetMapping("/othersample")
	public List<SampleResponse> otherSample() {
		return sampleClient.getSamples();
	}
}

@Component
class SampleClient {

	private final RestTemplate restTemplate;

	public SampleClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<SampleResponse> getSamples() {
		var response = restTemplate.exchange(
						"http://localhost:8080/sample",
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<SampleResponse>>() {
						});

		return response.getBody();
	}
}

class SampleResponse {
	private String name;
	private Integer age;

	public SampleResponse(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public SampleResponse() {}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
}