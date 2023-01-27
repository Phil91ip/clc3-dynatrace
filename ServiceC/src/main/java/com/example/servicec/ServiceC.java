package com.example.servicec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
@RestController
public class ServiceC {
    Random rand = new Random();
    static final String uriServiceA = "http://servicea"; //"http://localhost:8080";
    static final String uriServiceB = "http://serviceb"; //"http://localhost:8081";
    public static void main(String[] args) {
        SpringApplication.run(ServiceC.class, args);
    }

    @GetMapping("/")
    public String sayHello() {
        return "Service C here!";
    }

    @GetMapping("/doSomething")
    public String callAandB() {
        RestTemplate restTemplate = new RestTemplate();
        String responseA = restTemplate.getForObject(uriServiceA + "/getRandom", String.class);
        String responseB = restTemplate.getForObject(uriServiceB + "/getRandom", String.class);
        return "Response A: " + responseA + " - Response B: " + responseB;
    }

    @GetMapping("/randomGuesser")
    public int getRandomInt() {
        return rand.nextInt(5);
    }
}
