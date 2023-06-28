package com.hibernateSpringDataCourse;

import com.hibernateSpringDataCourse.domain.Book;
import com.hibernateSpringDataCourse.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//Testele deobicei se ruleaza in tranzactii aparte, dupa orce test se curata contextul sau se face rollback la tranzactie
//dar in 99% din cazuri nu avem nevoie de a face rollback, ptr ca ne tebuie sa se ruleze fiecare test pe curat
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//se foloseste ptr a stabili ordinea in care se executa testele
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.hibernateSpringDataCourse.bootstrap"})//asa putem sa adaugam in context runneru cela unde se adauga 2 carti
public class SpringBootJpaSpliceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Order(1)
    @Rollback(value = false)//asta ne va pastra tranzactia si in urmatorul test se va pastra starea bazei de date cu ce s-a adaugat
    //@Commit //face acelasi lucru ca RollBack
    //
    void testJpaSpliceTest() {
        long countBefore = bookRepository.count();

        //Verificarea data va cadea, deoarece @DataJpaTest va ridica spring context cu minim configurari
        //si nu va rula ce adaugam noi in context prin command runner in DataInitializer
//        assertThat(countBefore).isEqualTo(2);

//        assertThat(countBefore).isEqualTo(0);
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("MyBook", "123", "Self", null));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);

    }

    @Test
    @Order(2)
    void testJpaSpliceTransactionTest() {
        long countBefore = bookRepository.count();
//        assertThat(countBefore).isEqualTo(1);
        assertThat(countBefore).isEqualTo(3);
    }
}
