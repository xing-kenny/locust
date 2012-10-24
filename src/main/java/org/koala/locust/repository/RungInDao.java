package org.koala.locust.repository;

import org.koala.locust.domain.RungIn;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RungInDao extends PagingAndSortingRepository<RungIn, Long>,
        JpaSpecificationExecutor<RungIn> {}
