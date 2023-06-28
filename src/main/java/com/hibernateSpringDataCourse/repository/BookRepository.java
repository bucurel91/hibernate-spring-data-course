package com.hibernateSpringDataCourse.repository;

import com.hibernateSpringDataCourse.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
//@RepositoryDefinition() anotatia asta se foloseste cind vrem sa definim un repozitoriu propriu si nu a spring frameworkului
public interface BookRepository extends JpaRepository<Book, Long> {
}
