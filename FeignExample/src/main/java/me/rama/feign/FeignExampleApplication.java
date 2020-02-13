package me.rama.feign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;

import me.rama.feign.model.Board;
import me.rama.feign.model.Confirmation;
import me.rama.feign.model.User;

@SpringBootApplication
@EnableFeignClients
public class FeignExampleApplication implements ApplicationRunner{
	
	private static final Logger log = LogManager.getLogger(FeignExampleApplication.class);

	@Autowired
	private KanbanClient kanbanClient;
	
	public static void main(String[] args) {
		SpringApplication.run(FeignExampleApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String username = "testme5000";
		String password = "mysecretpassword";

		registerUser(25, username, password);

		final String token = loginUser(username, password);

		Board board = new Board();
		board.setName("my board");
		board = kanbanClient.createBoard(token, board);
		
		board.setName("my board renamed");
		kanbanClient.changeBoard(token, board.getId(), board);
		
		kanbanClient.listBoards(token).forEach(System.out::println);
		
		unregisterUser(password, token);
	}

	private void registerUser(int id, String username, String password) {
		log.info("registering user " + username);
		String userId = kanbanClient.registerUser(new User(id, username, password));
		log.info("new user's id is: " + userId);
	}

	private String loginUser(String username, String password) {
		log.info("login user " + username);

		ResponseEntity<Void> response = kanbanClient.loginUser("Basic " + buildBasicAuth(username, password));

		log.info("User logged in");
		String token = response.getHeaders().getFirst("X-Auth-Token");
		log.info("Token is " + token);
		return token;
	}

	private void unregisterUser(String password, String token) {
		ResponseEntity<Void> response;

		log.info("unregister user");
		response = kanbanClient.unregisterUser(token, new Confirmation(password));
		log.info(response.getStatusCodeValue());
		log.info("user successfully unregistered");
	}

	private String buildBasicAuth(String user, String password) {
		String authString = user + ":" + password;
		log.info("auth string: " + authString);
		byte[] authEncBytes = Base64Utils.encode(authString.getBytes());
		return new String(authEncBytes);
	}
}
