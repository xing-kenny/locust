package org.koala.locust.repository;

import org.koala.locust.domain.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseDao extends PagingAndSortingRepository<Course, Long> 
    , JpaSpecificationExecutor<Course> {
}
