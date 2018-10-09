package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread{
	
	private String lastInput;
	private String threadName;
	private Socket socket;
	private Server serv;
	private DataInputStream input;
	private DataOutputStream output;

	public ClientThread(String name, Socket socket, Server serv){
		this.threadName = name;
		this.socket = socket;
		this.serv = serv;
	}
	
	public void run(){
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				lastInput = input.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CommandHandler(lastInput);
			
			if (lastInput.contains("/quit") || lastInput.contains("/q")) {
				break;
			}
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void CommandHandler(String cmd){
	  
		String[] cmdWord;
		int diceTotal;
	  
		cmd = cmd.toLowerCase();
		cmdWord = cmd.split(" ", 2);
	  
		switch (cmdWord[0]) {
			case "/roll" 	:
				diceTotal = multiPartRoll(cmd);
				//TODO send dice total to broadcast
				break;
			case "/approach": 
				//TODO update player pos
				break;
			case "/whisper" :
				//TODO create whisper convo
				break;
			case "/setname" :
				//TODO change player name
				break;
			case "/join" 	:
				//TODO set player to lobby and add to lobby
				break;
			case "/leave" 	:
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
		int diceSize;

		int die = roll.indexOf('d');
	    
		if (die == -1)
			return Integer.parseInt(roll);
		
		if (Character.isDigit(roll.substring(die - 1, die).charAt(0))) {
			diceAmount = Integer.parseInt(roll.substring(die - 1, die));
		} else { 
			diceAmount = 1;
		}
		
		if (roll.substring(die + 1).length() >= 3 && Character.isDigit(roll.substring(die + 1).charAt(2))) {
			diceSize = Integer.parseInt(roll.substring(die + 1, die + 4));
		} else if (roll.substring(die + 1).length() == 2 && Character.isDigit(roll.substring(die + 1).charAt(1))) {
			diceSize = Integer.parseInt(roll.substring(die + 1, die + 3));
		} else {
			diceSize = Integer.parseInt(roll.substring(die + 1, die + 2));
		}
	    
		for (int i=0; i<diceAmount; i++) {
			result += rand.nextInt(diceSize) + 1;
		}
	    
		if (roll.startsWith("-"))
			result = -result;
	    
		return result;
	}

	public void SetLocation(int[] pos){
		//TODO set position of player
	}
}
