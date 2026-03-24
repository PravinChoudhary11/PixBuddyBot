package com.orbytex.pixbuddy.listners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PixBuddyListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        if (message.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("PixBuddy is alive 🚀").queue();
        }

        if (message.equalsIgnoreCase("!help")) {
            event.getChannel().sendMessage("""
                    🤖 PixBuddy Help
                    
                    !help - Show commands
                    !ping - Check bot status
                    
                    """).queue();
        }
    }
}