package com.orbytex.AxiomCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxiomCoreApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Axiom Core Early Debug");
        System.out.println("=================================");

        System.out.println("DISCORD_TOKEN: " +
                (System.getenv("DISCORD_TOKEN") != null));

        System.out.println("GEMINI_KEY1: " +
                (System.getenv("SPRING_GEMINI_AXIOMCORE_APIKEY1") != null));

        System.out.println("GEMINI_KEY2: " +
                (System.getenv("SPRING_GEMINI_AXIOMCORE_APIKEY2") != null));

        System.out.println("GEMINI_KEY3: " +
                (System.getenv("SPRING_GEMINI_AXIOMCORE_APIKEY3") != null));

        System.out.println("=================================");

        SpringApplication.run(AxiomCoreApplication.class, args);
    }
}