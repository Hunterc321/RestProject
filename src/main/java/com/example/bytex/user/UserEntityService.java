package com.example.bytex.user;

import java.util.List;

public interface UserEntityService {

    List<UserEntity> getUsers();


    void addNewUser(UserEntity userEntity) throws IllegalArgumentException;

    void deleteUser(Long id);

    void updateUser(Long userId, UserEntity userEntity);
}
