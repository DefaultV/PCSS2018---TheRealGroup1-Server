package Server;
import java.util.List;

import Client.Player;

enum Emojis {
	lol, talking, shouting, whisper
}

public class Game {
	
	Emojis lol;
	Emojis talking;
	Emojis shouting;
	Emojis whisper;
	
	List<Player> playerList;
	
	public String GetRules() {
		return "Something";
	}
	
	public List<Player> GetPlayers() {
		return playerList;
	}
}