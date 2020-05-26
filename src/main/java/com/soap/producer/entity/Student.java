package com.soap.producer.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Component
@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Column(name = "group_number", nullable = false)
    private Integer groupNumber;

}
