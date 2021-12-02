package com.contract.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}

@RestController
class SampleController {

	private final SampleRepository sampleRepository;

	public SampleController(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	@GetMapping("/sample")
	public List<SampleResponse> sample() {
		return sampleRepository.getSamples();
	}
}

class SampleResponse {
	private final String name;
	private final Integer age;

	public SampleResponse(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
}

interface SampleRepository {
	public List<SampleResponse> getSamples();
}

@Component
class InMemorySampleRepository implements SampleRepository {

	@Override
	public List<SampleResponse> getSamples() {
		return List.of(
				new SampleResponse("Raul", 30),
				new SampleResponse("Ana", 30),
				new SampleResponse("Henrique", 4)
		);
	}

}