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
    //Removes the player from the client_list and deletes the Lobby object of there's less or equal to 0 players in the list.
    this.client_List.remove(client);
    if (this.GetPlayerCount() <= 0){
    	serv.DeleteLobby(this.name);
    }
  }

  public int GetPlayerCount(){
    return this.client_List.size();
  }
  
  public ClientThread GetClientByName(String clientName) {
		for (ClientThread cliento : client_List) {
			if (cliento.getThreadName().equals(clientName))
				return cliento;
		}
		return null;
	}

  public void InitGame(){
    //Inits a new Game object and sets the private game variable to it.
    System.out.format("New game initiated ...");
    Game game = new Game(client_List);
    SetGame(game);
  }
}
