package org.koala.locust.repository;

import org.koala.locust.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProjectDao  extends PagingAndSortingRepository<Project, Long>
    , JpaSpecificationExecutor<Project> {

    Page<Project> findAll(Pageable pageable);

}
