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
import org.springframework.web.bind.annotation.RequestBody;
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
    private final AccountDao accountDao;
    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }
    @RequestMapping (method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(Principal principal) {

        User user = userDao.findByUsername(principal.getName());
        List<Transfer> transferList = transferDao.getTransfersByUserId(user.getId());
        return transferList;
    }
    @RequestMapping (method = RequestMethod.POST)
    public Transfer createTransfer (@RequestBody Transfer transfer, Principal principal){
        User user = userDao.findByUsername(principal.getName());
        Account principalAccount = accountDao.findByUserId(user.getId());
        Account otherAccount = accountDao.findByUserId(transfer.getAccountTo());
//        TODO adjust both accounts by transfer amount
//        use accountDao to update both accounts (method)
        transfer.setAccountFrom(principalAccount.getAccountId());
        transfer.setAccountTo(otherAccount.getAccountId());
//        use transferDao to create new transfer record
//        update transfer with new transferId
        return transfer;
    }

}