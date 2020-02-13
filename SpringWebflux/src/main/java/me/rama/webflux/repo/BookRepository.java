/**
 * 
 */
package me.rama.webflux.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import me.rama.webflux.model.Book;

/**
 * @author rpole
 *
 */
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
