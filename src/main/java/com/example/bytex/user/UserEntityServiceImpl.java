package com.example.bytex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userEntityRepository.findAll();

    }

    @Override
    public void addNewUser(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        boolean existsInDatabase = userEntityRepository.existsById(id);

        if(existsInDatabase)
        {
            userEntityRepository.deleteById(id);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User with id "+ id + " doesnt exist");
        }


    }

    @Override
    public void updateUser(Long userId, UserEntity userEntity) {

        UserEntity newUserEntity = userEntityRepository.findById(userId).map(
                user -> {
                    user.setName(userEntity.getName());
                    user.setAge(userEntity.getAge());

                    return userEntityRepository.save(user);
                }).orElseThrow( () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " Not Found");
                }
        );

    }
}
