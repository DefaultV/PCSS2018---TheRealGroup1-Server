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
	  int numberOfClient = 0;

	  public static void main(String[] args) throws IOException {
	    // Specifying the serverSocket port number
	    int port = 1916;
	    //172.30.213.186
	    String clientSentence;
	    String serverMessage;
	    Server serv = new Server();
	    
	      try {
	        ServerSocket ourServerSocket = new ServerSocket(port);

	        System.out.println("DnDServer started at " + new Date() + '\n');
	        System.out.println(InetAddress.getLocalHost());
	        while (true) {
	          Socket clientSocket = ourServerSocket.accept();
	          System.out.println("A new Player just joined the Server!");
	          serv.numberOfClient++;

	          InetAddress inetAddress = clientSocket.getInetAddress();
	          System.out.println("Client" + serv.numberOfClient + "'s host name is " + inetAddress.getHostName() + "\n");
	          System.out.println("Client " + serv.numberOfClient + "'s IP Address is " + inetAddress.getHostAddress() + "\n");

	          //new Thread(new ClientThread("ChickenSalad", clientSocket)).start();
	          new ClientThread("derp", clientSocket,serv).start();
	        }
	      } catch (IOException ex) {
	        System.err.println(ex);
	      }
	      
	      
	  }

	  public void CreateLobby(ClientThread client, String name) {
		    int rnd_id = (int)Math.ceil(Math.random() * 100);
		    Lobby lob = new Lobby(Integer.toString(rnd_id), name, this);
		    lob.AddPlayerToList(client);
		    //client.setLobby(lob);
		  }

		  public void SetLobby(ClientThread client, String lobbyname) {
		    GetLobbyByName(lobbyname).AddPlayerToList(client);
		   // client.setLobby(GetLobbyByName(lobbyname));
		  }

  public Lobby GetLobbyByName(String lobbyname){
    for(Lobby lob : lobby_list){
      if (lob.GetLobbyName().equals(lobbyname))
        return lob;
    }
    return null;
  }
  
  public void DeleteLobby(String lobbyname)
  {
	  lobby_list.remove(GetLobbyByName(lobbyname));
  }
  
  public void showInt(int thisInt)
  {
	  System.out.println(thisInt);
  }
  
  public void showString(String thisString)
  {
	  System.out.println(thisString);
  }
  public void LeaveLobby(ClientThread client, String lobbyname)
  {
	  GetLobbyByName(lobbyname).RemovePlayerFromList(client);
  }
}
/*
class HandleAClient implements Runnable {

  private Socket clientSocket;
  String clientSentence;
  String serverMessage;

  public HandleAClient(Socket socket)

  {
    this.clientSocket = socket;
  }

  public void run() {
    try {
      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

      String inputLine, outputLine;

      while(in != null)
      {
        inputLine = in.readUTF();
        out.writeUTF("This thingy here is printing stuff: " + inputLine);

      }
      /*

         while (true) {
         System.out.println("Please Enter your username");
         clientSentence = in.readUTF();
         out.writeUTF("Welcome " + clientSentence + "!");

         String answer;
         answer = in.readUTF();
         answer = answer.toLowerCase();
         out.writeUTF(answer);
         if (answer.contains("yes")) {
         System.out.println("THIS IS ANSWER:" + answer);

         } else {
         out.writeUTF("Fine then...");
         }



         }


    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  // Accepts the request that the client class is sending

  // System.out.println("Please Write /Create Server + Your_ServerName");

}
*/
