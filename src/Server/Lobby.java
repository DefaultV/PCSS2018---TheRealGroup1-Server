package Server;
import java.util.*;
import Client.Client;

public class Lobby{
  private String id;
  private String name;

  List<Client> client_List;

  public Lobby(String id, String name){
    this.client_List = new List<Client>();
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
  }

  public void InitGame(){
    Game game = new Game();
    while(Game.isPlaying()){
      //Play the game TODO
    }
  }
}
