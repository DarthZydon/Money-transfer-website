package com.techelevator.tenmo.services;

import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public class TransferService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String baseUrl) {
        this.baseUrl = baseUrl + "/account" + "/transfers";
    }

    public List getTransfers(Long userId) {

    }

//    Note: Add transaction nature
//    @RequestMapping(method = RequestMethod.POST)
//    public void Transfer(AuthenticatedUser user, Long accountTo, BigDecimal amount) {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(user.getToken());
//
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
