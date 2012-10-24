package org.koala.locust.repository;

import org.koala.locust.domain.CheckIn;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CheckInDao extends PagingAndSortingRepository<CheckIn, Long>,
        JpaSpecificationExecutor<CheckIn> {}
