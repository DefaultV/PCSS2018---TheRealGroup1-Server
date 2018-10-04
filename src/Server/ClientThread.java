package Server;

import java.util.Random;
import Client.Player;

public class ClientThread{
  
	private String threadName;
	private Player player;

	public ClientThread(String name, Player player){
		this.threadName = name;
		this.player = player;
	}

	void CommandHandler(String cmd){
	  
		String[] cmdWord;
		int diceTotal;
	  
		cmd = cmd.toLowerCase();
		cmdWord = cmd.split(" ", 2);
	  
		switch (cmdWord[0]) {
			case "approach" : 
				//TODO update player pos
				break;
			case "whisper" 	:
				//TODO create whisper convo
				break;
			case "roll" 	:
				diceTotal = multiPartRoll(cmd);
				//TODO send dice total to broadcast
				break;
			case "stand" 	:
				break;
			case "setname" 	:
				//TODO change player name
				break;
			case "join" 	:
				//TODO set player to lobby and add to lobby
				break;
			case "leave" 	:
				//TODO remove player from lobby
				break;
		}
	}

	void Heartbeat(){

	}
  
	private int multiPartRoll(String roll) {
	    
		String[] parts = roll.split("(?=[+-])");
	    
		int total = 0;

		for (int i=0; i<parts.length; i++) {
			total += singleRoll(parts[i]);
		}
	    
		return total;
	}
  
	private int singleRoll(String roll) {
		Random rand = new Random();
		int result = 0;
		int diceAmount;

		int die = roll.indexOf('d');
	    
		if (die == -1)
			return Integer.parseInt(roll);
		
		if (Character.isDigit(roll.substring(die -1, die).charAt(0))) {
			diceAmount = Integer.parseInt(roll.substring(die - 1, die));
		} else { 
			diceAmount = 1;
		}
		int diceSize = Integer.parseInt(roll.substring(die + 1, die +2));
	    
		for (int i=0; i<diceAmount; i++) {
			result += rand.nextInt(diceSize) + 1;
		}
	    
		if (roll.startsWith("-"))
			result = -result;
	    
		return result;
	}
}