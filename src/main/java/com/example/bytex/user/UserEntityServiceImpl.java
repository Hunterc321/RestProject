package com.example.bytex.user;

import com.example.bytex.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public void addNewUser(UserEntity userEntity) throws InvalidDataAccessApiUsageException {
            try
            {
                userEntityRepository.save(userEntity);
            }catch (InvalidDataAccessApiUsageException e)
            {
                throw new ApiRequestException("Data send is null", HttpStatus.BAD_REQUEST);
            }

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
            throw new ApiRequestException("User with id "+ id + " doesnt exist", HttpStatus.NO_CONTENT);
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
                    throw new ApiRequestException( "User with id " + userId + " Not Found", HttpStatus.NOT_FOUND);
                }
        );

    }
}
