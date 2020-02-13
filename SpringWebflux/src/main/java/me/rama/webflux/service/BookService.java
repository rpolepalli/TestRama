/**
 * 
 */
package me.rama.webflux.service;

import me.rama.webflux.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author rpolepalli
 *
 */
public interface BookService {
	public Mono<Book> findById(String id);
	public Flux<Book> findAll();
	public Mono<Book> save(Book book);
	public Mono<Void> deleteById(String id);
}
