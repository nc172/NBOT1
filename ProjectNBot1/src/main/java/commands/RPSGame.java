package commands;

import java.util.Random;

public class RPSGame {
    //variables
    String choice;
    final static String[] options = {"rock","paper","scissors"};

    //contrustor
    public RPSGame(String choice) {
        this.choice = choice;
    }
    

    /**purpose: create random choice from computer (rock,paper,scissor)
     * @return computer choice
     */

    public static String computerChoice(){
        Random r = new Random();
        int random = r.nextInt(3);
        String computerChoice =  options[random];
        return computerChoice;
    }

    /**purpose: control player status 
     * @return player status (tie,win,lose)
     * @param computerchoice,playerchoice
     */
    public static String playerStatus(String computerChoice, String playerChoice){
        String status = null;
        if(computerChoice.equalsIgnoreCase(playerChoice)){
            status = "tie";
        }else if((computerChoice.equalsIgnoreCase("rock") && playerChoice.equalsIgnoreCase("scissors"))||
                (computerChoice.equalsIgnoreCase("paper") && playerChoice.equalsIgnoreCase("rock"))||
                (computerChoice.equalsIgnoreCase("scissors") && playerChoice.equalsIgnoreCase("paper"))
        ){
            status = "you lost";
        }else{
            status = "you win";
        }
        return status;
    }
    
}
