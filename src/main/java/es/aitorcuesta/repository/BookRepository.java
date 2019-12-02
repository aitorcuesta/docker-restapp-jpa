package es.aitorcuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.aitorcuesta.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{		

}
