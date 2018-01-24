package com.example.billing;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingClient {

    private Logger logger = LoggerFactory.getLogger(getClass());
     private String uri;

    public BillingClient(String uri){
        this.uri = uri;
    }

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultMessage")
    public void billUser(String userId, int amount) {
        System.out.println(" **** 1111111 *****");
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("amount", amount);
        System.out.println(" **** 222222 *****" + uri);
        restTemplate.postForEntity(uri, params, String.class);
        System.out.println(" **** 333333 *****");
    }

    public void defaultMessage(String userId, int amount) {
        logger.info("Default Message....");
        logger.info("userId " + userId + "packageId " + amount);
    }
}
