package Server;
import java.util.List;
import java.lang.Math;
import Client.Player;

enum Emojis {
	lol, talking, shouting, whisper
}

enum Action{
  TALKING,
  JOINING,
  LEAVING
}

public class Game {
	
	Emojis lol;
	Emojis talking;
	Emojis shouting;
	Emojis whisper;
	
	List<ClientThread> playerList;
	boolean playing;
	
	public String GetRules() {
		return "Something";
	}
	
	public List<ClientThread> GetPlayers() {
		return playerList;
	}

  public void SetPlaying(boolean status){
    this.playing = status;
  }
	public boolean isPlaying(){
		return playing;
	}

  public void Broadcast(List<ClientThread> l_ply, String txt){
    for(ClientThread ply : l_ply){
      System.out.format("%s", txt);
      //TODO
      //Send text to player
      //Server.sendtext(txt)
    }
  }

  public void SetPosition(ClientThread ply, int[] pos){
    this.playerList.get(playerList.indexOf(ply)).SetLocation(pos);
    //Update position on map and give info to clients
    //TODO
  }

  public void SetAction(Player ply, Action act){
    String form = "";
    switch(act){
      case TALKING:
        form += String.format("%s is talking...", ply.GetNickName());
        break;
      case JOINING:
        form += String.format("%s is joining...", ply.GetNickName());
        break;
      case LEAVING:
        form += String.format("%s is leaving...", ply.GetNickName());
        break;
      default:
        break;
    }
    Broadcast(playerList, form);
    //Update action on map and give info to clients
    //TODO
  }

  public void SetSaying(Player ply, String text){
    //if range between p1 and p2 <= 5
    //for (Player oply : playerList){
      //if (Math.sqrt(Math.pow((ply.GetLocation[0] - oply.GetLocation[0]), 2))+Math.pow((ply.GetLocation[1] - oply.GetLocation[1]), 2)){
      //TODO euclidean distance to player
      //}
    //}
    //make icon instead TODO
    //Update saying on map and give info to clients
    String form = String.format("%s says: %s", ply.GetNickName(), text);
    Broadcast(playerList, form);
  }
}
