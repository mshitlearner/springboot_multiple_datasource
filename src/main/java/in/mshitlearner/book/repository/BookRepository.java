package in.mshitlearner.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mshitlearner.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
