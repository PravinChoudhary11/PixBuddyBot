package com.orbytex.pixbuddy.config;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.orbytex.pixbuddy.listners.PixBuddyListener;

@Configuration
public class Config {

    @Value("${spring.discord.bot.token}")
    private String token;

    private final PixBuddyListener pixBuddyListener;

    public Config(PixBuddyListener pixBuddyListener) {
        this.pixBuddyListener = pixBuddyListener;
    }

    @PostConstruct
    public void startBot() throws Exception {
        JDABuilder.createDefault(token)
                .addEventListeners(pixBuddyListener)
                .build();
    }
}