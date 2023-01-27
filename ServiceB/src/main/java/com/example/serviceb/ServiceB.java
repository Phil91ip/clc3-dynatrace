package com.example.serviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ServiceB {
    Random rand = new Random();
    static final String uriServiceA = "http://localhost:8080";//"http://servicea";
    static final String uriServiceC = "http://localhost:8082";//"http://servicec";

    public static void main(String[] args) {
        SpringApplication.run(ServiceB.class, args);
    }

    @GetMapping("/")
    public String serviceB() throws Exception {
        int rnd = rand.nextInt(3);
        switch (rnd){
            case 0:
                //Random guesser
                return String.format("Random-Guesser result: %d", playRandomGuesser());
            case 1:
                //delay
                TimeUnit.SECONDS.sleep(4);
                return "Service B Delay";
            case 2:
                //exception
                String[] name = {"Philip", "Philipp"};
                return name[2];
            default:
                return "Something strange happened!";
        }
    }

    private int playRandomGuesser()
    {
        int rnd = rand.nextInt(5);
        String result = "-1";
        RestTemplate restTemplate = new RestTemplate();
        int counter = 0;
        while (Integer.parseInt(result) != rnd) {
            result = restTemplate.getForObject(uriServiceC + "/randomGuesser", String.class);
            counter++;
        }
        return counter;
    }

    @GetMapping("/getRandom")
        public int getRandom() {
        return rand.nextInt(100);
    }
}
