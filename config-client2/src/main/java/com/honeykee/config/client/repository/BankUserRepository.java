package com.honeykee.config.client.repository;

import com.honeykee.config.client.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-27 17:47
 * @since JDK 1.8
 */
public interface BankUserRepository extends JpaRepository< BankUser,Long >, JpaSpecificationExecutor<BankUser> {

}
