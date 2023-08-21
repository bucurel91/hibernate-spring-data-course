package com.hibernateSpringDataCourse;

import com.hibernateSpringDataCourse.domain.BookNatural;
import com.hibernateSpringDataCourse.repository.BookNaturalRepository;
import com.hibernateSpringDataCourse.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//aceasta anotare o punem ca spring sa nu ne faca override la proprietati, by default el configureaza h2database
@ComponentScan(basePackages = {"com.hibernateSpringDataCourse.bootstrap"})//asa putem sa adaugam in context runneru cela unde se adauga 2 carti
public class MySQLIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookNaturalRepository bookNaturalRepository;
    @Test
    void testMySQL() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("MyAuthor");
        BookNatural saved = bookNaturalRepository.save(bookNatural);
        BookNatural fetched = bookNaturalRepository.getReferenceById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }

}
