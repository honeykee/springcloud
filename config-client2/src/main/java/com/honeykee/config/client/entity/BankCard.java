package com.honeykee.config.client.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-27 17:10
 * @since JDK 1.8
 */

@Data
@Entity
@Table(name = "bank-card" )
public class BankCard {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

//    @Column(name = "user_id", nullable = false, length = 20)
//    private String userId;

    @Column(name = "card_id", nullable = true, length = 20)
    private String cardId;

    @Column(name = "card_no", nullable = true, length = 20)
    private String cardNo;

    @Column(name = "card_balance", nullable = true, precision = 2)
    private BigDecimal cardBalance;

    @Column(name = "card_type", nullable = true)
    private Byte cardType;

    @Column(name = "status", nullable = true)
    private Byte status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private BankUser bankUser;

}
