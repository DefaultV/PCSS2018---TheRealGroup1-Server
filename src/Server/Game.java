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
  
  Game(List<ClientThread> client_list){
    this.playerList = client_list;
  }

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

  public void Broadcast(String txt){
    for(ClientThread ply : playerList){
      System.out.format("%s", txt);
      ply.sendText(txt);
      //TODO
      //Send text to player
      //ply.SendText(txt)
    }
  }

  public void BroadcastTo(ClientThread client, String txt){
    //TODO
    //client.UpdateClient_SendText(txt);
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
    //Broadcast(playerList, form);
    //for (ClientThread client : playerList){
    //Update action on map and give info to clients
    //TODO
    //client.UpdateClient_SendAction(Player ply, Action act);
    //}
  }

  public void SetSaying(Player ply, String text){
    String form = String.format("%s says: %s", ply.GetNickName(), text);
    //if range between p1 and p2 <= 5
    //for (ClientThread client : playerList){
      //if (Math.sqrt(Math.pow((ply.GetLocation[0] - oply.GetLocation[0]), 2))+Math.pow((ply.GetLocation[1] - oply.GetLocation[1]), 2) >= 2.0f){
      //BroadcastTo(client, form);
      //}
    //}
    //make icon instead TODO
    //Update saying on map and give info to clients
    //Broadcast(playerList, form);
  }
}
