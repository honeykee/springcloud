package com.honeykee.user.service.repository;

import com.honeykee.user.service.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-01 17:25
 * @since JDK 1.8
 */

@Repository
public interface UserRepository extends JpaRepository< User,Long>, JpaSpecificationExecutor<User> {

    User findByUsernameEquals(String username);

//    User findByUsernameEquals();

}
