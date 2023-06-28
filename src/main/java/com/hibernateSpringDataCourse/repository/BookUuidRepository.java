package com.hibernateSpringDataCourse.repository;

import com.hibernateSpringDataCourse.domain.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
