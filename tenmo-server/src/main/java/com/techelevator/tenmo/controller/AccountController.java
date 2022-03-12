package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.security.Principal;

@RestController
@RequestMapping ("/account")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private final UserDao userDao;
    private final AccountDao accountDao;

    public AccountController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal){
        String username = principal.getName();
        User user = userDao.findByUsername(username);
        Account account = accountDao.findByUserId(user.getId());
        return account.getBalance();

    }
}
