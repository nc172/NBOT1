package org.example;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListeners extends ListenerAdapter {

    /**
     * get message, if message start with hi then say the greeting
     * @param: event (message) from text channel
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //get author name
        String authorName = event.getAuthor().getName();
        Long authorID = event.getAuthor().getIdLong();
        if (event.getAuthor().isBot()) {
            // Ignore messages from other bots
            return;
        }

        // Get the message content
        String messageContent = event.getMessage().getContentRaw();

        // Check if the message starts with a specific prefix
        if(authorID == 325261632408256512L){
            if (messageContent.startsWith("Hi")) {
                System.out.println("msg from boss:" + messageContent);
                // Send a response message to the same channel
                event.getChannel().sendMessage("Hello, boss!").queue();
            }
        }else{
            if (messageContent.startsWith("hi")) {
                System.out.println("msg from user");
                // Send a response message to the same channel
                event.getChannel().sendMessage("Hello, " + authorName + "!").queue();
            }
        }
        
    }


    /**
     * announce a channel elimination on desired channel (main channel)
     * @param: event(delete action) from any channel
     */
    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {
        String channelName = event.getChannel().getName();
        TextChannel general = event.getGuild().getTextChannelById(895170980970901507L);//get id from general channel

        if(general != null){
            general.sendMessage("the channel \"" + channelName + "\" was deleted").queue();
        }
    }

}
