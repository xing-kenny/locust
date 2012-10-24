package org.koala.locust.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.koala.locust.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
