package org.example;

import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class NamBot{
    public static void main(String[] args) throws LoginException , InterruptedException {
        //declare namBot
        JDA namBot;

        //initialize namBot
        namBot = JDABuilder.createDefault("HIDDEN TOKEN")
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("With your mom"))
                .addEventListeners(new BotListeners(),new BotCommands())
                .build().awaitReady();


        //create slash command and descriptions
        namBot.updateCommands().addCommands(
                Commands.slash("test", "testing the test command"),
                Commands.slash("kick", "kicking a user"),
                Commands.slash("test2","test"),
                Commands.slash("cal","choose your cal (*,/,+,-)")
                        .addOption(OptionType.STRING,"operation","choose operation",true)
                        .addOption(OptionType.INTEGER,"num1","enter num 1",true)
                        .addOption(OptionType.INTEGER,"num2","enter num 2",true),
                Commands.slash("rannum","get a random number from x to y")
                        .addOption(OptionType.INTEGER,"x","from x number",true)
                        .addOption(OptionType.INTEGER,"y","to y number",true),
                Commands.slash("finance","type in money and description")
                        .addOption(OptionType.STRING,"money","amount money spent",true)
                        .addOption(OptionType.STRING,"purposes", "reason of the money spent", true)
        ).queue();

    }

}
