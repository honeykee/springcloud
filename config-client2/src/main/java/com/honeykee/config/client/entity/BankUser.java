package com.honeykee.config.client.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-27 17:10
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "bank_user" )
public class BankUser {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login_name", nullable = false, length = 50)
    private String loginName;

    @Column(name = "login_password", nullable = false, length = 255)
    private String loginPassword;

    @Column(name = "user_id", nullable = true, length = 20)
    private String userId;

    @OneToMany( mappedBy = "bankUser" )
    private List<BankCard> bankCards;

    @Column(name = "user_name", nullable = true, length = 50)
    private String userName;

    @Column(name = "id_card", nullable = true, length = 18)
    private String idCard;

    @Column(name = "user_phone", nullable = true, length = 11)
    private String userPhone;

    @Column(name = "user_email", nullable = true, length = 30)
    private String userEmail;

    @Column(name = "user_type", nullable = true)
    private Byte userType;

    @Column(name = "user_wechat", nullable = true, length = 50)
    private String userWechat;

    @Column(name = "wechat_name", nullable = true, length = 50)
    private String wechatName;

    @Column(name = "user_qq", nullable = true, length = 11)
    private String userQQ;

    @Column(name = "user_gender", nullable = true)
    private Byte userGender;

    @Column(name = "user_birthday", nullable = true)
    private Timestamp userBirthday;

    @Column(name = "user_avatar", nullable = true, length = 255)
    private String userAvatar;

    @Column(name = "status", nullable = true)
    private Byte status;

    @CreationTimestamp
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time", nullable = true)
    private Timestamp updateTime;


}
