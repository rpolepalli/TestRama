package me.rama.webflux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.rama.webflux.hello.GreetingWebClient;

@SpringBootApplication
public class SpringWebfluxApplication {
	
	private static final Logger log = LogManager.getLogger(SpringWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
		
		GreetingWebClient gwc = new GreetingWebClient();
		log.info(gwc.getResult());
	}

}
