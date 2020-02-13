/**
 * 
 */
package me.rama.webflux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import me.rama.webflux.handler.BookHandler;
import me.rama.webflux.hello.GreetingHandler;

/**
 * @author rpolepalli
 *
 */
@Configuration
public class AppRouter {

	/*
	 * @Bean public RouterFunction<ServerResponse> route(GreetingHandler
	 * greetingHandler) { return RouterFunctions.route(
	 * RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.
	 * TEXT_PLAIN)), greetingHandler::hello); }
	 */
	
	@Bean
    public RouterFunction<ServerResponse> route(BookHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/fbooks").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(RequestPredicates.GET("/fbook/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(RequestPredicates.POST("/fbook").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(RequestPredicates.DELETE("/fbook/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}
