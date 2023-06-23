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
        String authorName = event.getAuthor().getName();
        Long authorID = event.getAuthor().getIdLong();
        if (event.getAuthor().isBot()) {
            return;
        }

        String messageContent = event.getMessage().getContentRaw();

        if(authorID == 325261632408256512L){
            respondToBoss(authorName, messageContent, event.getChannel());
        }else{
            respondToUser(authorName, messageContent, event.getChannel());
        }
    }

    /**
     * Send a greeting message to the channel if the message starts with "hi" and the author is the boss
     * @param: authorName - the name of the author
     * @param: messageContent - the content of the message
     * @param: channel - the channel where the message was received
     */
    private void respondToBoss(String authorName, String messageContent, MessageChannelUnion channel){
        if (messageContent.startsWith("hi")) {
            System.out.println("msg from boss:" + messageContent);
            channel.sendMessage("Hello, boss!").queue();
        }
    }

    /**
     * Send a greeting message to the channel if the message starts with "hi" and the author is not the boss
     * @param: authorName - the name of the author
     * @param: messageContent - the content of the message
     * @param: channel - the channel where the message was received
     */
    private void respondToUser(String authorName, String messageContent, MessageChannelUnion channel){
        if (messageContent.startsWith("hi")) {
            System.out.println("msg from user");
            channel.sendMessage("Hello, " + authorName + "!").queue();
        }
    }

    /**
     * announce a channel elimination on desired channel (main channel)
     * @param: event(delete action) from any channel
     */
    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {
        String channelName = event.getChannel().getName();
        TextChannel general = event.getGuild().getTextChannelById(895170980970901507L);
        if(general != null){
            announceChannelDeletion(channelName, general);
        }
    }

    /**
     * Send a message to the general channel to announce a deleted channel
     * @param: channelName - the name of the deleted channel
     * @param: general - the general channel where the message will be sent
     */
    private void announceChannelDeletion(String channelName, TextChannel general){
        general.sendMessage("the channel \"" + channelName + "\" was deleted").queue();
    }

}
