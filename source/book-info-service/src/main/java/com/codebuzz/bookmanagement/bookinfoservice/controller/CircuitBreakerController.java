package com.codebuzz.bookmanagement.bookinfoservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    
    @GetMapping("api/book/sampleapi")
    //@Retry(name = "instance1", fallbackMethod = "getFallbackCall")
   // @CircuitBreaker(name = "default", fallbackMethod = "getFallbackCall")
    @RateLimiter(name = "default")
    public String getSampleAPI(){
       /* logger.info("Sample API call invoked");
       ResponseEntity<String> tt = new RestTemplate().getForEntity("http://localhost:8777/myurl",String.class);
        return tt.getBody();*/ 
        logger.info("rate limit");
        return "Sample API call invoked";
    }

    public String getFallbackCall(Exception ex){
        return "This is fallback methdo";
    }
}
