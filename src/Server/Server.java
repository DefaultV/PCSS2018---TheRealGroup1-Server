package Server;

import java.net.*;
import java.io.*;
import java.util.*;
import Client.Player;

public class Server {

	DataInputStream iStream;
	DataOutputStream oStream;
	List<Player> client_ids;
	List<Lobby> lobby_names;
	static int numberOfClient = 0;

	public static void main(String[] args) throws IOException {
		// Specifying the serverSocket port number
		int port = 1916;
		//172.30.213.186
		String clientSentence;
		String serverMessage;
		new Thread(() -> {
			try {
				ServerSocket ourServerSocket = new ServerSocket(port);

				System.out.println("DnDServer started at " + new Date() + '\n');
				System.out.println(InetAddress.getLocalHost());
				while (true) {
					Socket clientSocket = ourServerSocket.accept();
					System.out.println("A new Player just joined the Server!");
					numberOfClient++;

					InetAddress inetAddress = clientSocket.getInetAddress();
					System.out
							.println("Client" + numberOfClient + "'s host name is " + inetAddress.getHostName() + "\n");
					System.out.println(
							"Client " + numberOfClient + "'s IP Address is " + inetAddress.getHostAddress() + "\n");

					new Thread(new HandleAClient(clientSocket)).start();
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}).start();
	}
}

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
			*/

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Accepts the request that the client class is sending

	// System.out.println("Please Write /Create Server + Your_ServerName");


	void CreateLobby() {
		
	}

	void SetLobby(Player player) {

	}

	void ExitLobby() {
	}
}
