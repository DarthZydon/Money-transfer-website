package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;

import java.math.BigDecimal;


public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String baseUrl) {
        this.baseUrl = baseUrl + "/account" + "/transfer";
    }

    @RequestMapping
    public Transfer[] getTransfers(AuthenticatedUser user) {

        Long userID = user.getUser().getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        ResponseEntity<Transfer[]> responseArray = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(headers), Transfer[].class);
        if (responseArray != null) {
            return responseArray.getBody();
        } else {
            System.out.println("Sorry, no transfers listed!");
            return responseArray.getBody();
        }

    }

    @RequestMapping
    public void transferSend(AuthenticatedUser user, int userId, BigDecimal amountSent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((user.getToken()));
        Transfer transfer = new Transfer();
        transfer.setAccountFrom((long) userId);
        transfer.setAccountTo((long) userId);
        transfer.setTransferTypeId(2);
        transfer.setAmount(amountSent);
        transfer.setTransferStatusId(2);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        ResponseEntity<Transfer> transferResponse = restTemplate.exchange(baseUrl, HttpMethod.POST,entity, Transfer.class);
        transfer = transferResponse.getBody();

    }

}
