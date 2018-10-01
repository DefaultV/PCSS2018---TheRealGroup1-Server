package Server;
import java.util.*;

import Client.Client;

public class Lobby{
  private String id;
  private String name;

  List<Client> client_List;

  public Lobby(String id, String name){
    this.client_List = new ArrayList<Client>();
    this.id = id;
    this.name = name;
  }

  public String GetId(){
    return this.id;
  }

  public String GetLobbyName(){
    return this.name;
  }

  public void AddPlayerToList(Client client){
    this.client_List.add(client);
  }
  public void RemovePlayerFromList(Client client){
    this.client_List.remove(client);
    if (this.GetPlayerCount() <= 0){
      //Call server & delete this lobby TODO
    }
  }

  public int GetPlayerCount(){
    return this.client_List.size()
  }

  public void InitGame(){
    Game game = new Game();
    while(game.isPlaying()){
      //Play the game TODO
    }
  }
}
