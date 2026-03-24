package com.orbytex.pixbuddy.config;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.context.annotation.Configuration;
import com.orbytex.pixbuddy.listners.PixBuddyListener;

@Configuration
public class Config {

    private final PixBuddyListener pixBuddyListener;

    public Config(PixBuddyListener pixBuddyListener) {
        this.pixBuddyListener = pixBuddyListener;
    }

    @PostConstruct
    public void startBot() throws Exception {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null || token.isBlank()) {
            throw new RuntimeException("DISCORD_TOKEN not set");
        }

        JDABuilder.createDefault(token)
                .addEventListeners(pixBuddyListener)
                .build();
    }
}