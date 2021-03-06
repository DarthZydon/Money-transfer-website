package com.techelevator.tenmo.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class Transfer {
    private Long transferId;
    private int transferTypeId;
    private int transferStatusId;
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal amount;


    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeID) {
        this.transferTypeId = transferTypeID;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusID) {
        this.transferStatusId = transferStatusID;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
