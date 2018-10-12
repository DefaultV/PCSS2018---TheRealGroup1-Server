package Server;
import java.util.List;
import java.lang.Math;

public class Game {
	
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
  public synchronized void Broadcast(String txt){
    //For loops on the clients in the game, then calls sendText() on each
    for(ClientThread ply : playerList){
      System.out.format("%s", txt);
      ply.sendText(txt);
    }
  }
}
