package com.orbytex.AxiomCore.listners;

import com.orbytex.AxiomCore.ai.AIService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class AxiomCoreListener extends ListenerAdapter {

    private final AIService aiService;

    public AxiomCoreListener(AIService aiService) {
        this.aiService = aiService;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        event.getChannel().sendTyping().queue();

        new Thread(() -> {

            String response = aiService.getResponse(message);

            event.getChannel()
                    .sendMessage(response)
                    .queue();

        }).start();
    }
}