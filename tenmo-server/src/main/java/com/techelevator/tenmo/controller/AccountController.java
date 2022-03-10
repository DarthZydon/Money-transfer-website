package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping ("/account")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private final UserDao userDao;
    private final AccountDAO accountDAO;

    public AccountController(UserDao userDao, AccountDAO accountDAO) {
        this.userDao = userDao;
        this.accountDAO = accountDAO;
    }

    public BigDecimal getBalance(Principal principal){

        String username = principal.getName();
       User user = userDao.findByUsername(username);
        Account account = accountDAO.findByUser_ID(user.getId());
        return account.getBalance();

    }
}
