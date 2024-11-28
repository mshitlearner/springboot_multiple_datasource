package in.mshitlearner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mshitlearner.book.model.Book;
import in.mshitlearner.book.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public String insertOrUpdateBook(Book book) {
		// TODO Auto-generated method stub
		Book bookResult = bookRepository.save(book);
		if (bookResult.getBookId() > 0)
			return "Save Successfully";
		else
			return "UnSaved Successfully";
	}

	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> lstBook = bookRepository.findAll();
		return lstBook;
	}

	public String deleteBook(Integer bookId) {
		// TODO Auto-generated method stub
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			bookRepository.delete(book.get());
			return "Deleted Successfully";
		}
		return "Deleted unsuccessfully";
	}
}
