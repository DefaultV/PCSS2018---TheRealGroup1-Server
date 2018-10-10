package Server;
import java.util.*;
import Client.Client;

public class Lobby{
  private String id;
  private String name;

  List<ClientThread> client_List;

  public Lobby(String id, String name){
    this.client_List = new ArrayList<ClientThread>();
    this.id = id;
    this.name = name;
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
      //Call server & delete this lobby TODO
    }
  }

  public int GetPlayerCount(){
    return this.client_List.size();
  }

  public void InitGame(){
    Game game = new Game(client_List);
    while(game.isPlaying()){
      //Play the game TODO
    }
  }
}
