package games;

import java.util.Random;

public class RPSGame {
    //variables
    final static String[] options = {"rock","paper","scissors"};

    /**purpose: create random choice from computer (rock,paper,scissor)
     * @return computer choice
     */

    public String computerChoice(){
        Random r = new Random();
        int random = r.nextInt(3);
        String computerChoice =  options[random];
        return computerChoice;
    }

    /**purpose: control player status 
     * @return player status (tie,win,lose)
     * @param computerChoice playerchoice
     */
    public String playerStatus(String computerChoice, String playerChoice){
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
