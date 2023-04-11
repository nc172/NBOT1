package commands;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jetbrains.annotations.NotNull;

import games.RPSGame;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

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
                        try {
                            result = num1.getAsInt() / num2.getAsInt();
                        } catch (ArithmeticException e) {
                            event.reply("can't divide a number by 0").queue();
                            return; // return after replying
                        }
                        break;
                        default:
                            event.reply("invalid options").queue();
                            break;
                }
                event.reply(num1.getAsString() +" "+ operation.getAsString() +" "+ num2.getAsString()+"\nThe result is: " + result).queue();
                break;
            //generate 2 number from range x to y
            case"rannum":
                Random ranNum = new Random();
                OptionMapping x = event.getOption("x");
                OptionMapping y = event.getOption("y");
                int random = ranNum.nextInt(y.getAsInt() - x.getAsInt()) + x.getAsInt();
                event.reply("A random number from " + x.getAsInt() + " to " + y.getAsInt() + " is: " + random).queue();
                break;
            //write down financial expenses
            case"finance":
                OptionMapping money = event.getOption("money");
                OptionMapping purpose = event.getOption("purposes");
                expense(money.getAsDouble(), purpose.getAsString());
                event.reply("money added").queue();
                break;
            //rpsgame control
            case"rpsgame":
                RPSGame game = new RPSGame();
                OptionMapping playerChoice = event.getOption("playerchoice");
                String computerChoice = game.computerChoice();
                String playerStatus = game.playerStatus(computerChoice,playerChoice.getAsString());
                event.reply("Computer choose " + computerChoice + "\n" + playerStatus).queue();
            default:
                event.reply("invalid options").queue();
                break;
        }
    }

    private void expense(double money, String purpose){
        String excelPath = "finance.xlsx";
        try {
            FileInputStream finance = new FileInputStream(excelPath);
            Workbook workbook = WorkbookFactory.create(finance);

            // Get the sheet in the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Create a new row in the sheet
            int rowCount = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowCount);

            // Create cells in the row and write data to them
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(java.time.LocalDate.now().toString());//time

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(money);//money

            Cell cell3 = row.createCell(2);
            cell3.setCellValue(purpose);//purposes

            // Write the workbook to the same file
            FileOutputStream outputStream = new FileOutputStream(excelPath);
            workbook.write(outputStream);
            workbook.close();
            System.out.println("$"+ money + " for " + purpose +" is added");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}
