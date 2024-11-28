package in.mshitlearner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mshitlearner.book.model.Book;
import in.mshitlearner.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Books", description = "Operations related to books")
@RestController
@RequestMapping("/book") 
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping(value="/add")
	@Operation(summary = "Saving the Books", description = "Returns a msg as Saved Successfully for the user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully/Unsuccessfully saved")
            /*,
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
            */
    })
	public String addBook(@RequestBody Book book) {
		String msg = bookService.insertOrUpdateBook(book);
		return msg;
	}
	
	@GetMapping(value="/getBooks")
	@Operation(summary = "Fetching the list of Books", description = "Returns a List of books saved in the DB for the user", security = @SecurityRequirement(name = "bearerAuth"))
	public List<Book> getAllBooks(){
		List<Book> lstBook = bookService.getAllBooks();
		return lstBook;
	}
	
	@DeleteMapping(value = "/deleteBook/{bookId}")
	@Operation(summary = "Deleting the Book", description = "Returns a msg after deleting the specified book for the user", security = @SecurityRequirement(name = "bearerAuth"))
	public String deleteBook(@Parameter(description = "ID of the Book to Delete") @PathVariable Integer bookId) {
		String msg = bookService.deleteBook(bookId);
		return msg;
	}
}
