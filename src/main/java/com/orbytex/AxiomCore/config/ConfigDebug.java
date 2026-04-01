package com.orbytex.AxiomCore.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDebug {

    @Value("${spring.application.name:NOT_FOUND}")
    private String appName;

    @Value("${spring.discord.bot.token:NOT_FOUND}")
    private String discordToken;

    @Value("${spring.gemini.AxiomCore.apikey1:NOT_FOUND}")
    private String geminiKey1;

    @Value("${spring.gemini.AxiomCore.apikey2:NOT_FOUND}")
    private String geminiKey2;

    @Value("${spring.gemini.AxiomCore.apikey3:NOT_FOUND}")
    private String geminiKey3;

    @PostConstruct
    public void printConfig() {

        System.out.println("\n=================================");
        System.out.println("     Axiom Core Config Debug");
        System.out.println("=================================");

        System.out.println("Application Name: " + appName);

        System.out.println("Discord Token: " +
                (discordToken.equals("NOT_FOUND") ? "NOT FOUND" : "FOUND"));

        System.out.println("Gemini API Key 1: " +
                (geminiKey1.equals("NOT_FOUND") ? "NOT FOUND" : "FOUND"));

        System.out.println("Gemini API Key 2: " +
                (geminiKey2.equals("NOT_FOUND") ? "NOT FOUND" : "FOUND"));

        System.out.println("Gemini API Key 3: " +
                (geminiKey3.equals("NOT_FOUND") ? "NOT FOUND" : "FOUND"));

        System.out.println("=================================\n");
    }
}