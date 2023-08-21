package com.hibernateSpringDataCourse.dao;

import com.hibernateSpringDataCourse.domain.Author;

public interface AuthorDao {

    Author getById(Long id);
}
