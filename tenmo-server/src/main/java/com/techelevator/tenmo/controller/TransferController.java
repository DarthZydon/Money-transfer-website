package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account/transfer")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private final TransferDao transferDao;
    private final UserDao userDao;
    public TransferController(TransferDao transferDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
    }
    @RequestMapping (method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(Principal principal) {

        User user = userDao.findByUsername(principal.getName());
        List<Transfer> transferList = transferDao.getTransfersByUserId(user.getId());
        return transferList;
    }

}