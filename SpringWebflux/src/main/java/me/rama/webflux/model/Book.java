/**
 * 
 */
package me.rama.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rpolepalli
 *
 */

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private String id;
    private String title;
    private String author;
}
