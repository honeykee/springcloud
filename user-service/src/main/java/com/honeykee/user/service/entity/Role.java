package com.honeykee.user.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-01 17:09
 * @since JDK 1.8
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role",schema = "spring_user")
public class Role  { //implements GrantedAuthority

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;


//    @Override
//    public String getAuthority() {
//        return this.name;
//    }
}
