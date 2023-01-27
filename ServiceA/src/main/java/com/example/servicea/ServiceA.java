package com.example.servicea;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceA {
    Random rand = new Random();
    static final String uriServiceB = "http://serviceb"; //"http://localhost:8081";
    static final String uriServiceC = "http://servicec"; //"http://localhost:8082";
    static final String uriServiceC2 = "http://servicec/doSomething"; //"http://localhost:8082/doSomething";
    public static void main(String[] args) {
        SpringApplication.run(ServiceA.class, args);
    }

    @GetMapping("/")
    public String sayHello() {
        int rnd = rand.nextInt(3);
        switch (rnd){
            case 0:
                return String.format("%s!", callServiceB());
            case 1:
                return String.format("%s!", callServiceC());
            case 2:
                return String.format("%s!", callServiceC2());
            default:
                return "Something strange happened!";
        }
    }

    private static String callServiceB()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uriServiceB, String.class);
        return result;
    }

    @GetMapping("/getRandom")
    public int getRandom() {
        return rand.nextInt(100);
    }

    private static String callServiceC()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uriServiceC, String.class);
        return result;
    }
    private static String callServiceC2()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uriServiceC2, String.class);
        return result;
    }
}
