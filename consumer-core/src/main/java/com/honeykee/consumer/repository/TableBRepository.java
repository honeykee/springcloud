package com.honeykee.consumer.repository;

import com.honeykee.consumer.entity.TableBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-22 19:22
 * @since JDK 1.8
 */
@Repository
public interface TableBRepository extends JpaRepository< TableBEntity,Long >, JpaSpecificationExecutor<TableBEntity> {
}
