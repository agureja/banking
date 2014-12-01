
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OpenBank {

	private static ArrayList<Integer> log;
	private static HashMap<String, Integer> serverMapping;
	static int counter;
	static int leader = 0;
	static boolean bankOpen = true;
	static {
		serverMapping = new HashMap<String, Integer>();
        serverMapping.put("Server1",1);
		serverMapping.put("Server2",2);
		serverMapping.put("Server3",3);
		serverMapping.put("Server4",4);
		serverMapping.put("Server5",5);
		log = new ArrayList<Integer>();
		log.add(100); //initial balance
		counter=0; //operation number
	}
	
	public static void main(String args[]) {

		String localIP="";
		try {
			localIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
		}
		int id = serverMapping.get("Server1");
		Proposer propser = new Proposer(id);
		Acceptor acceptor = new Acceptor(id);
		Learner learner = new Learner(id);
		System.out.println("Account is logged in!");
		System.out.println("1.Deposit/withdraw(+/-)");
		System.out.println("2.Check balance");
		System.out.println("3.Logout of the Account");
		System.out.println("4. Log in the Account");
		Scanner in = new Scanner(System.in);
		while(true) {
			String input="";
			System.out.println("Which operation do you want to perform");

			input = in.nextLine();
			switch(input) {
			
				case "1":	if(bankOpen == true){ 
								System.out.println("Enter the amount you want to depoit/withdraw:");
								sendToLeaderQueue(Integer.parseInt(in.nextLine()));
						  	} else {						  		
						  		System.out.println("Log in first!");
						  	}
							break;
				case "2":	if(bankOpen == true){ 
								requestUpdate(counter+1);
								System.out.println("Your Balance is : "+ String.valueOf(log.get(counter)));
					 		} else {
					 			System.out.println("Log in first!");
					 		}
							break;
			case "3":
						bankOpen = false;
						System.out.println("You are logged out!");
						break;
					
			case "4":	bankOpen = true;
						System.out.println("You are logged in!");
						break;
			default: 	System.out.println("Invalid entry. Press enter to continue");
					 	System.console().readLine();
			}
			
		}
		//get add, decrement or check balance.
	}
	public static void requestUpdate(int counter) {

		
		//Initiate Paxos
	}
	public static void sendToLeaderQueue(int value) {
		
	}
}
