package Server;

public class Lobby{
  private String id;
  private String name;

  public Lobby(String id, String name){
      this.id = id;
      this.name = name;
  }

  public String GetId(){
    return this.id;
  }

  public String GetLobbyName(){
    return this.name;
  }
}
