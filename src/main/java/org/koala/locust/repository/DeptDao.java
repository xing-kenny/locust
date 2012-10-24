package org.koala.locust.repository;

import org.koala.locust.domain.Dept;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeptDao extends PagingAndSortingRepository<Dept, Long> {

}
