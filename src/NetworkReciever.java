import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class NetworkReciever extends Thread {
	static ServerSocket socket;

	  public void run() {

		  try {
			socket = new ServerSocket(5000);
				while(true) {
					String recievedMsg ="";
					Socket connectionSocket = socket.accept();
					BufferedReader reciever = 
							new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
					recievedMsg= reciever.readLine();
					//OpenBank.addJobToQueue(recievedMsg);
					System.out.println("Received Msg is: " + recievedMsg);
				}
		  } catch (IOException e) {
			
			e.printStackTrace();
		}
	      
	      
	  }

	
}
