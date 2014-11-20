import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import org.json.*;
import org.json.simple.JSONObject;

public class Message {

	
	static List<String> serverList;
	static {
		serverList.add("Server1");
		serverList.add("Server2");
		serverList.add("Server3");
		serverList.add("Server4");
		serverList.add("Server5");
	}
	public static void sendPrepare(ProposalId newId){
		
		JSONObject obj=new JSONObject();
		obj.put("uid", newId.getUniqueId());
		obj.put("ballot",new Integer(newId.getNumber()));
		sendString(obj.toJSONString());
		
	}
	public static void sendPromise(ProposalId newId, ProposalId oldId, int value) {
	
		JSONObject obj=new JSONObject();
		obj.put("uid", newId.getUniqueId());
		obj.put("ballot",new Integer(newId.getNumber()));
		obj.put("Auid", newId.getUniqueId());
		obj.put("Aballot",new Integer(newId.getNumber()));
		obj.put("value",new Integer(newId.getNumber()));
		sendString(obj.toJSONString());
	}
	public static void sendAccept(ProposalId newId, int value){
		
		JSONObject obj=new JSONObject();
		obj.put("uid", newId.getUniqueId());
		obj.put("ballot",new Integer(newId.getNumber()));
		obj.put("value",new Integer(value));
		
		sendString(obj.toJSONString());	
	}
	public static void sendAccepted(ProposalId newId, int value) {

		JSONObject obj=new JSONObject();
		obj.put("uid", newId.getUniqueId());
		obj.put("ballot",new Integer(newId.getNumber()));
		obj.put("value",new Integer(value));

		sendString(obj.toJSONString());	
	}
	public static String serializeObject(ProposalId item) {
		 
		JSONObject obj=new JSONObject();
		obj.put("uid", item.getUniqueId());
		obj.put("ballot",new Integer(item.getNumber()));
		return obj.toJSONString();
	}
	public static void sendString(String str) throws Exception {
	
		for(int i=0;i<serverList.size();++i) {
				DatagramSocket clientSocket = new DatagramSocket();
				sendRequest(clientSocket,serverList.get(i),"5000", str);
		}
   	 
	}
	 static void sendRequest(DatagramSocket socket,String ipAddress, String port, String msg) throws Exception {
		 
	      InetAddress inetAddress = InetAddress.getByName(ipAddress);
	      byte[] data = new byte[1024];
	      data = msg.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(data, data.length, inetAddress, 5000);
	      socket.send(sendPacket);
	     
	 }
	 String getResponse(DatagramSocket socket) throws Exception{
	 
		 byte[] data = new byte[1024];
		 DatagramPacket dataPacket = new DatagramPacket(data, data.length);
	     socket.receive(dataPacket);
		 return new String(dataPacket.getData());
	 }

	
}
