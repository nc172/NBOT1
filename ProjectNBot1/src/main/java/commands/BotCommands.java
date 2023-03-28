package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BotCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        //initialize commandsName to event name
        String commandsName = event.getName();
        switch (commandsName){
            case "test":
                event.deferReply().queue();
                event.getHook().sendMessage("this is a test").queue();
                break;
            //calculate a simple problem with provided operation and 2 input number
            case"cal":
                int result = 0;
                //asign 3 param from slash command
                OptionMapping operation = event.getOption("operation");
                OptionMapping num1 = event.getOption("num1");
                OptionMapping num2 = event.getOption("num2");
                //switch case for 4 basic operation
                switch (operation.getAsString()){
                    case "+":
                        result = num1.getAsInt() + num2.getAsInt();
                        break;
                    case "-":
                        result = num1.getAsInt() - num2.getAsInt();
                        break;
                    case "*":
                        result = num1.getAsInt() * num2.getAsInt();
                        break;
                    case "/":
                        if (num2.getAsInt() == 0){
                            event.reply("can't divide a number by 0").queue();
                        }else{
                            result = num1.getAsInt() + num2.getAsInt();
                        }
                        break;
                }
                event.reply("The result is: " + result).queue();
                break;
            //generate 2 number from range x to y
            case "rannum":
                Random ranNum = new Random();
                OptionMapping x = event.getOption("x");
                OptionMapping y = event.getOption("y");
                int random = (ranNum.nextInt(y.getAsInt()) + x.getAsInt());
                event.reply("A random number from " + x.getAsInt() + " to " + y.getAsInt() + " is: " + random).queue();
                break;
        }

    }
}
