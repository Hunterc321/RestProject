package com.example.bytex.user;

import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

public interface UserEntityService {

    List<UserEntity> getUsers();


    void addNewUser(UserEntity userEntity) throws InvalidDataAccessApiUsageException;

    void deleteUser(Long id);

    void updateUser(Long userId, UserEntity userEntity);
}
