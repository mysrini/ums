package com.example.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class BillingClient {

    private String uri;

    public BillingClient(String uri){
        this.uri = uri;
    }

    RestTemplate restTemplate = new RestTemplate();

    public void billUser(String userId, int amount) {

        Map<String,Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);

        restTemplate.postForEntity(uri, params, String.class);
    }
}
