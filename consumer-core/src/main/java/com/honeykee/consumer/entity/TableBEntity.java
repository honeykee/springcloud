package com.honeykee.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-22 19:17
 * @since JDK 1.8
 */
@Entity
@Table(name = "table_b", schema = "test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableBEntity {
    public static enum Type { FIRST, SECOND, THIRD };

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = 20)
    private String name;

}
