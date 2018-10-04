package Server;
import java.net.*;
import java.io.*;
import java.util.*;
import Client.Player;


public class Server {

	DataInputStream iStream;
	DataOutputStream oStream;
	List<Player> client_ids;

	public static void main(String[] args) throws IOException {
		//Specifying the serverSocket port number
		int port = 6690;

		//Creating the serversocket
		ServerSocket ourServerSocket = new ServerSocket(port);
		//System.out.println("Please Enter your username");
		
		//System.out.println("Please Write /Create Server + Your_ServerName");
		
		
		
		//if (input.nextLine("Create Server "))
		//{
			//System.out.println("You wrote the write thing");
		//}
		//double radius = input.nextDouble();

		//System.out.println(2 * radius);


	// Accepts the request that the client class is sending
	String clientSentence;
	String serverMessage;
	Socket clientSocket = ourServerSocket.accept();
	DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
	clientSentence = in.readUTF();
	out.writeUTF("Welcome " + clientSentence + "!");
	double annualIR = in.readDouble();
	int numberOfYears = in.readInt();
	double loanAmount = in.readDouble();

	
	


	}

	void ClientHandler() {
	}

	void CreateLobby() {
		
	}

	void SetLobby(Player player) {
		
	}

	void ExitLobby() {
	}
}
