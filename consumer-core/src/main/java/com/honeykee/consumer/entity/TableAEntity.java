package com.honeykee.consumer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-22 19:17
 * @since JDK 1.8
 */
@Entity
@Table(name = "table_a", schema = "test")
@Data
public class TableAEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "address", nullable = true, length = 20)
    private String address;

    @Basic
    @Column(name = "first_name", nullable = true, length = 20)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 20)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "b_id", referencedColumnName = "id")
    private TableBEntity tableBByBId;


    @Version
    @Basic
    @Column(name = "version")
    private Long version;


}
