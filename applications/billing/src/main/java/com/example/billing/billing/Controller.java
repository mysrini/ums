package com.example.billing.billing;


import com.example.payments.RecurlyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Map;
@RestController
public class Controller {


     RecurlyGateway paymentGateway = new RecurlyGateway();

    @RequestMapping(value="/reoccurringPayment",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> payment(@RequestBody Map<String, Object> params) {

        //HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add("content-type", MediaType.APPLICATION_JSON.toString());

        ArrayList<String> errors=new ArrayList<>();

        int paymentMonthlyAmount = (int) params.get("amount");
        String userId = (String) params.get("userId");

        if(paymentGateway.createReocurringPayment(paymentMonthlyAmount))
            return new ResponseEntity<ArrayList<String>>(errors, HttpStatus.CREATED);
        else {
            errors.add("error1");
            errors.add("error2");
            return new ResponseEntity<ArrayList<String>>(errors, HttpStatus.BAD_REQUEST);
        }

    }
}
