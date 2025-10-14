package com.api.blog.module.user.domain;

import com.api.blog.module.user.domain.vo.Email;
import com.api.blog.module.user.domain.vo.Name;
import jakarta.persistence.*;

@Entity
@Table(name = "user_tb")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Name name;
    private Email email;



}
