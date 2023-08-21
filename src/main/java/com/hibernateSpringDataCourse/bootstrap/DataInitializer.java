package com.hibernateSpringDataCourse.bootstrap;

import com.hibernateSpringDataCourse.domain.Author;
import com.hibernateSpringDataCourse.domain.AuthorUuid;
import com.hibernateSpringDataCourse.domain.Book;
import com.hibernateSpringDataCourse.domain.BookUuid;
import com.hibernateSpringDataCourse.repository.AuthorRepository;
import com.hibernateSpringDataCourse.repository.AuthorUuidRepository;
import com.hibernateSpringDataCourse.repository.BookRepository;
import com.hibernateSpringDataCourse.repository.BookUuidRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = {"local", "default"})
public class DataInitializer implements CommandLineRunner {

    private final BookRepository repository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    private final AuthorRepository authorRepository;

    public DataInitializer(BookRepository repository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse", null);
        Book saveDDD = repository.save(bookDDD);

        Book bookSIA = new Book("Spring in Action", "234234", "Oriely", null);
        Book saveSIA = repository.save(bookSIA);

        repository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Igor");
        authorUuid.setLastName("Bucur");
        authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + authorUuid.getId());

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("Never split the difference");
        bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + bookUuid.getId());

        Author author =  new Author();
        author.setFirstName("Igor");
        author.setLastName("Bucur");

        authorRepository.save(author);
    }
}
