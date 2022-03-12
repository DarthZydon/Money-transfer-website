package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String baseUrl) {
        this.baseUrl = baseUrl + "account";
    }
    public BigDecimal getBalance(AuthenticatedUser user){
        BigDecimal balance = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.getToken());
            ResponseEntity<BigDecimal> response =  restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(headers), BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }
}
