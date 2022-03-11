package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> findAll();

    Transfer getTransferByTransferId(Long transferID);

    int findTransferIDbyUsername(String username);

    List<Transfer> getTransfersByUserId(Long id);


    }
