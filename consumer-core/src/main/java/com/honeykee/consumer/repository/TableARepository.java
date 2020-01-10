package com.honeykee.consumer.repository;

import com.honeykee.consumer.entity.TableAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-22 19:22
 * @since JDK 1.8
 */

@Repository
public interface TableARepository extends JpaRepository< TableAEntity, Long >, JpaSpecificationExecutor<TableAEntity> {


    @Lock( LockModeType.PESSIMISTIC_WRITE )
    List< TableAEntity > findAllByFirstNameAndLastName(String firstName, String lastName);

}
