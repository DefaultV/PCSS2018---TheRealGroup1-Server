package Server;
import java.net.*;
import java.io.*;
import java.util.*;
import Client.Player;


public class Server {

  DataInputStream iStream;
  DataOutputStream oStream;
  List<Player> client_ids;
  List<Lobby> lobby_list;

  public static void main(String[] args) throws IOException {
    //Specifying the serverSocket port number
    int port = 6690;

    //Creating the serversocket
    ServerSocket ourServerSocket = new ServerSocket(port);
    //System.out.println("Please Enter your username");

    //System.out.println("Please Write /Create Server + Your_ServerName");


    // Accepts the request that the client class is sending
    String clientSentence;
    String serverMessage;
    Socket clientSocket = ourServerSocket.accept();
    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
    clientSentence = in.readUTF();
    out.writeUTF("Welcome " + clientSentence + "!");
    double doubles = in.readDouble();
    int integers = in.readInt();





  }

  void ClientHandler() {
  }
  
  void CreateLobby(ClientThread client, String name) {
    int rnd_id = (int)Math.ceil(Math.random() * 100);
    Lobby lob = new Lobby(Integer.toString(rnd_id), name);
    lob.AddPlayerToList(client);
  }

  void SetLobby(ClientThread client, String lobbyname) {
    GetLobbyByName(lobbyname).AddPlayerToList(client);
  }

  Lobby GetLobbyByName(String lobbyname){
    for(Lobby lob : lobby_list){
      if (lob.GetLobbyName().equals(lobbyname))
        return lob;
    }
    return null;
  }

  void ExitLobby() {
  }
}
