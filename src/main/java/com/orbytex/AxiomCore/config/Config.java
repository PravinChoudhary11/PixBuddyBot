package com.orbytex.AxiomCore.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.context.annotation.Configuration;
import com.orbytex.AxiomCore.listners.AxiomCoreListener;

@Configuration
public class Config {

    private final AxiomCoreListener axiomCoreListener;



    public Config(AxiomCoreListener axiomCoreListener) {
        this.axiomCoreListener = axiomCoreListener;
    }

    @PostConstruct
    public void startBot() throws Exception {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            token = dotenv.get("DISCORD_TOKEN");
        }

        if (token == null || token.isBlank()) {
            throw new RuntimeException("DISCORD_TOKEN not set");
        }

        JDABuilder.createDefault(token)
                .addEventListeners(axiomCoreListener)
                .build();
    }
}