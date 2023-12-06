package ru.pleshkova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pleshkova.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
