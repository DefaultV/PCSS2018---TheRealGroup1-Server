package Server;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	DataInputStream iStream;
	DataOutputStream oStream;
	List<Player> client_ids;

	public static void main(String[] args) throws IOException {
		//Specifying the serverSocket port number
		int port = 6660;

		//Creating the serversocket
			ServerSocket ourServerSocket = new ServerSocket(port);
	
		// Accepts the request that the client class is sending
		Socket clientSocket = ourServerSocket.accept();
		Scanner serverScanner = new Scanner(clientSocket.getInputStream());
		
		int number = 0, temp;
		temp = number * 2;
		
		PrintStream player = new PrintStream(clientSocket.getOutputStream());
		player.println(temp);
		
		

	}

	void ClientHandler() {
	}

	void CreateLobby() {
	}

	void SetLobby() {
	}

	void ExitLobby() {
	}

}

class Player {

}