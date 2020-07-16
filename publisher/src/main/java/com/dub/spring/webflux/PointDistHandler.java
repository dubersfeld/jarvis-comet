package com.dub.spring.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.dub.spring.model.GenDist;
import com.dub.spring.model.PointDist;

import reactor.core.publisher.Mono;

@Component
public class PointDistHandler {
	
	private static final int delay = 50000;

	@Autowired
	private GenDist genDist;
	
	public Mono<ServerResponse> pointDist(ServerRequest request) {
	  
		try {
			Thread.sleep(delay);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	 
		// actual random generation of the points distribution
		PointDist dist = genDist.randomize(20);
	  
		return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(dist));
  }

}