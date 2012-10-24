package org.koala.locust.repository;

import org.koala.locust.domain.Plan;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PlanDao  extends PagingAndSortingRepository<Plan, Long> 
    , JpaSpecificationExecutor<Plan> {

}
