package org.koala.locust.repository;

import org.koala.locust.domain.SignUp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SignUpDao extends PagingAndSortingRepository<SignUp, Long>,
        JpaSpecificationExecutor<SignUp> {}
