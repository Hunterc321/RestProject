package com.example.bytex.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@JsonIgnoreProperties
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    public UserEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
