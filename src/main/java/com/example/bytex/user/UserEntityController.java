package com.example.bytex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserEntityController {

    UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){
        List<UserEntity> allUsers = userEntityService.getUsers();

        if(allUsers.size() != 0)
        {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nothing to show");


    }

    @PostMapping
    public ResponseEntity<List<UserEntity>> addNewUser(@RequestBody UserEntity userEntity)
    {
        userEntityService.addNewUser(userEntity);
        return new ResponseEntity<>(userEntityService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping(path = "{userId}")
    public List<UserEntity> deleteUser(@PathVariable("userId") Long id){
        userEntityService.deleteUser(id);
        return userEntityService.getUsers();
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<List<UserEntity>> updateUser(@PathVariable("userId") Long userId,
                                       @RequestBody UserEntity newUserEntity)
    {
        userEntityService.updateUser(userId, newUserEntity);
        return new ResponseEntity<>(userEntityService.getUsers(), HttpStatus.OK);
    }




}
