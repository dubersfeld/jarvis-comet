package com.dub.spring.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PointDistRouter {

  @Bean
  public RouterFunction<ServerResponse> routePointDist(PointDistHandler pointDistHandler) {

	  return RouterFunctions
			  .route(
					  RequestPredicates.GET("/points")
					  .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
					  pointDistHandler::pointDist
					);
  }
}