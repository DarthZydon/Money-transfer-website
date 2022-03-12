package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account/{id}/user")
@PreAuthorize("isAuthenticated")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUsername(String username) {
        User foundUser = userDao.findByUsername(username);
        return foundUser;
    }

    public List<User> allUsers() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    public int getUserIdByUsername(String username) {
        int foundUserId = userDao.findIdByUsername(username);
        return foundUserId;
    }
}
