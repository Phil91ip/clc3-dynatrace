package com.example.servicea;
import java.util.Random;

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
    public static void main(String[] args) {


        SpringApplication.run(ServiceA.class, args);
    }

    @GetMapping("/")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        if(rand.nextInt(10) <= 5) {
            return String.format("Hello %s!", callServiceB());
        } else {
            return String.format("Hello %s!", callServiceC());
        }

    }

    private static String callServiceB()
    {
        final String uri = "http://serviceb";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    private static String callServiceC()
    {
        final String uri = "http://servicec";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

}
