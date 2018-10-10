package Server;
import java.util.*;
import Client.Client;

public class Lobby{
  private String id;
  private String name;
  private Server serv;
  private Game game;

  List<ClientThread> client_List;

  public Lobby(String id, String name, Server serv){
    System.out.format("New lobby spawned: ");
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

  public void SetGame(Game game){
    this.game = game;
  }

  public Game GetGame(){
    return this.game;
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
    System.out.format("New game initiated ...");
    Game game = new Game(client_List);
    while(game.isPlaying()){
      //Play the game TODO
    }
  }
}
