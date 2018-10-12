package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {

	private String lastInput;
	private String threadName;
	private String playerName;
	private String playerPos;
	private Socket socket;
	private Server serv;
	private DataInputStream input;
	private DataOutputStream output;
	private Lobby lobby;

	public ClientThread(String name, Socket socket, Server serv) {
		this.threadName = name;
		this.socket = socket;
		this.serv = serv;
	}

	public void run() {

		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.playerName = input.readUTF();
			this.threadName = playerName;
      this.sendText("Hello, " + this.playerName + "!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				lastInput = input.readUTF();
			} catch (Exception e) {
				try {
					System.out.println("Input is closing");
					input.close();
					System.out.println("Output is closing");
					output.close();
					System.out.println("Socket is closing");
					socket.close();
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (lastInput.contains("/quit") || lastInput.contains("/q")) {
				break;
			}

			CommandHandler(lastInput);
		}

		try {
			System.out.println("Input is closing");
			input.close();
			System.out.println("Output is closing");
			output.close();
			System.out.println("Socket is closing");
			socket.close();
			return;
		} catch (IOException e) {
			System.out.println("Exception encountered while closing I/O and Socket");
		}
	}

	void CommandHandler(String cmd) {

		String originalMsg = cmd;
		String[] cmdWord = new String[2];
		String[] ogMsgSplit = new String[3];
		int diceTotal;

		cmd = cmd.toLowerCase();
		cmdWord = cmd.split(" ", 2);
		ogMsgSplit = originalMsg.split(" ", 3);

		switch (cmdWord[0]) {
		case "/roll":
		case "/r":
			try {
				diceTotal = multiPartRoll(cmd);
				lobby.GetGame().Broadcast(this.playerName + " rolled is : " + diceTotal);
			} catch (NumberFormatException e) {
				this.sendText("The roll input could not be understood please try again");
			} catch (NullPointerException ne) {
				this.sendText("The game has not begun yet");
			}
			break;
		case "/whisper":
		case "/w":
			this.sendText(">> " + ogMsgSplit[1] + " " + ogMsgSplit[2]);
			lobby.GetClientByName(ogMsgSplit[1]).sendText(this.playerName + " >> " + ogMsgSplit[2]);
			break;
		case "/setname":
			this.playerName = ogMsgSplit[1];
			this.threadName = this.playerName;
      this.sendText("You changed name to: " + this.playerName);
			break;
		case "/create":
		case "/join":
			if (lobby == null) {
				try {

					if (cmdWord[1].contains(serv.GetLobbyByName(cmdWord[1]).GetLobbyName())) {
						serv.SetLobby(this, cmdWord[1]);
						lobby = serv.GetLobbyByName(cmdWord[1]);
            this.sendText("You joined, " + this.playerName);
					}
				} catch (NullPointerException NE) {
					serv.CreateLobby(this, cmdWord[1]);
					lobby = serv.GetLobbyByName(cmdWord[1]);
          this.sendText("You joined, " + lobby.GetLobbyName());
				}
			}
			break;
		case "/leave":
			serv.LeaveLobby(this, lobby.GetLobbyName());
      this.sendText("You left the lobby");
			lobby = null;
			break;
		case "/help":
			this.sendText("Welcome to help!");
			this.sendText("We will now explain how to use the different commands!");
			this.sendText("All commands can be written with no distinction between upper and lower case letters");
			this.sendText("/Join lobbyName					To Join a lobby, if no lobby with specified name exist, a lobby will be created");
			this.sendText("/Leave 							To leave your current lobby");
			this.sendText("/Roll ndm+modifier				To roll a dice, where n is equal to the number of dice to roll");
			this.sendText("									and m is equal to the number of sides of the dice. n can be ommited or below 99, and 0 < m < 999");
			this.sendText("/Setname desiredName				Changes your nickname to the desiredName");
			this.sendText("/Whisper nameOfPlayer message 	Sends a message privately to a player with the nickname nameOfPlayer");
			this.sendText("/avatar number 1-6 				Changes avatar");
			this.sendText("Thank you for using P3's server miniproject");
			this.sendText("We wish you a nice day!");
			this.sendText("Andreas, Daniel, Gabriel, Jannick, Magnus, Young");
			;
			break;
		case "changepos":
      try{
			  playerPos = cmdWord[1];
			  lobby.GetGame().Broadcast("poschange" + this.threadName + " " + playerPos);
      } catch (NullPointerException e){
        this.sendText("The game has not begun yet");
      }
			break;
		case "startgame":
			try {
			lobby.InitGame();
			} catch (NullPointerException ne)
			{
				this.sendText("You are not in a lobby");
			}
      lobby.GetGame().Broadcast("Game has started!");

			break;
		default:
			try {
				lobby.GetGame().Broadcast(this.playerName + " says: "+ originalMsg);
			} catch (NullPointerException e) {
				this.sendText("The game has not begun yet");
			}
		}
	}

	private int multiPartRoll(String roll) {

		String[] parts = roll.split("(?=[+-])");

		int total = 0;

		for (int i = 0; i < parts.length; i++) {
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

		if (die == -1) {
			try {
				return Integer.parseInt(roll);
			} catch (NumberFormatException e) {
				if (roll.charAt(0) == '+')
					return Integer.parseInt(roll.substring(2));
				else
					return Integer.parseInt(roll.substring(2)) * -1;
			}
		}

		if (Character.isDigit(roll.substring(die - 2, die).charAt(0))) {
			diceAmount = Integer.parseInt(roll.substring(die - 2, die));
		} else if (Character.isDigit(roll.substring(die - 1, die).charAt(0))) {
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

		for (int i = 0; i < diceAmount; i++) {
			result += rand.nextInt(diceSize) + 1;
		}

		if (roll.startsWith("-")) {
			result = -result;
		}

		return result;
	}

	public String getThreadName() {
		return this.threadName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public void sendText(String text) {

		try {
			output.writeUTF(text);
		} catch (Exception e) {
			try {
				System.out.println("Input is closing");
				input.close();
				System.out.println("Output is closing");
				output.close();
				System.out.println("Socket is closing");
				socket.close();
				Thread.currentThread().interrupt();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
