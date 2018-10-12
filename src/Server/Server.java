package Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	DataInputStream iStream;
	DataOutputStream oStream;
	List<ClientThread> client_list;
	List<Lobby> lobby_list;
	int numberOfClient = 0;

	public static void main(String[] args) throws IOException {

		int port = Integer.parseInt(args[0]);
		String clientSentence;
		String serverMessage;
		Server serv = new Server();
		ServerSocket ourServerSocket = new ServerSocket(port);

		serv.lobby_list = new ArrayList<Lobby>();
		try {
			System.out.println("DnDServer started at " + new Date() + '\n');
			System.out.println(InetAddress.getLocalHost());
			while (true) {
				Socket clientSocket = ourServerSocket.accept();
				System.out.println("A new Player just joined the Server!");
				serv.numberOfClient++;

				InetAddress inetAddress = clientSocket.getInetAddress();
				System.out.println(
						"Client" + serv.numberOfClient + "'s host name is " + inetAddress.getHostName() + "\n");
				System.out.println(
						"Client " + serv.numberOfClient + "'s IP Address is " + inetAddress.getHostAddress() + "\n");
        //Spawn a new ClientThread to handle each client
				new ClientThread("derp", clientSocket, serv).start();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			ourServerSocket.close();
		}

	}
  //Creates a new lobby and assigns the player to the clientlist inside that lobby, also assigns a random ID as a string
	public void CreateLobby(ClientThread client, String name) {
		int rnd_id = (int) Math.ceil(Math.random() * 100);
		Lobby lob = new Lobby(Integer.toString(rnd_id), name, this);
		this.lobby_list.add(lob);
		lob.AddPlayerToList(client);
		System.out.println(name + " has been created as a new lobby");
		client.setLobby(lob);
	}

	public void SetLobby(ClientThread client, String lobbyname) {
		GetLobbyByName(lobbyname).AddPlayerToList(client);
		System.out.println(client + "has joined: " + lobbyname);

		client.setLobby(GetLobbyByName(lobbyname));
	}

	public Lobby GetLobbyByName(String lobbyname) {
		for (Lobby lob : lobby_list) {
			if (lob.GetLobbyName().equals(lobbyname))
				return lob;
		}
		return null;
	}

	public ClientThread GetClientByName(String clientName) {
		for (ClientThread cliento : client_list) {
			if (cliento.getThreadName().equals(clientName))
				return cliento;
		}
		return null;
	}

	public void DeleteLobby(String lobbyname) {
		lobby_list.remove(GetLobbyByName(lobbyname));
	}

	public void LeaveLobby(ClientThread client, String lobbyname) {
		GetLobbyByName(lobbyname).RemovePlayerFromList(client);
	}
}
