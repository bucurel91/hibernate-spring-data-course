package com.hibernateSpringDataCourse.repository;

import com.hibernateSpringDataCourse.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
