package Server;
import java.util.*;
import Client.Client;

public class Lobby{
  private String id;
  private String name;
  private Server serv;

  List<ClientThread> client_List;

  public Lobby(String id, String name, Server serv){
    this.client_List = new ArrayList<ClientThread>();
    this.id = id;
    this.name = name;
    this.serv = serv;
  
  }

  public String GetId(){
    return this.id;
  }

  public String GetLobbyName(){
    return this.name;
  }

  public void AddPlayerToList(ClientThread client){
    this.client_List.add(client);
  }
  public void RemovePlayerFromList(ClientThread client){
    this.client_List.remove(client);
    if (this.GetPlayerCount() <= 0){
    	serv.DeleteLobby(this.name);
    }
  }

  public int GetPlayerCount(){
    return this.client_List.size();
  }

  public void InitGame(){
    Game game = new Game();
    while(game.isPlaying()){
      //Play the game TODO
    }
  }
}
