package com.dub.spring.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import com.dub.spring.model.PointDist;

import reactor.core.publisher.Mono;

// basic WebFlux client
public class PointDistWebClient {

	private WebClient client = WebClient.create("http://localhost:9090");

	private Mono<ClientResponse> result = client.get()
			.uri("/points")
			.accept(MediaType.APPLICATION_JSON)
			.exchange();

	public PointDist getResult() {
		return result.flatMap(res -> res.bodyToMono(PointDist.class)).block();
	}
}