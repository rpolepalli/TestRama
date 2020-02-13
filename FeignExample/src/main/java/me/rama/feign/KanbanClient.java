/**
 * 
 */
package me.rama.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import me.rama.feign.model.Board;
import me.rama.feign.model.Confirmation;
import me.rama.feign.model.User;

/**
 * @author rpole
 *
 */
@FeignClient(name = "KanbanClient", url = "https://kanbanbackend.herokuapp.com/")
public interface KanbanClient {

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User user);

	@DeleteMapping(value = "/unregister", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> unregisterUser(@RequestHeader("X-Auth-Token") String authToken, Confirmation user);

	@PostMapping("/login")
	ResponseEntity<Void> loginUser(@RequestHeader("Authorization") String authHeader);

	@GetMapping("/boards")
	List<Board> listBoards(@RequestHeader("X-Auth-Token") String authHeader);

	@PostMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE)
	Board createBoard(@RequestHeader("X-Auth-Token") String authHeader, Board board);

	@PutMapping(value = "/board/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Board changeBoard(@RequestHeader("X-Auth-Token") String authHeader, @PathVariable("id") Long id, Board board);
}