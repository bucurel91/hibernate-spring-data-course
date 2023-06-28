package com.hibernateSpringDataCourse.repository;

import com.hibernateSpringDataCourse.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
