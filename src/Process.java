import java.util.UUID;


public class Process {
	private static int processUid;
	protected int value;
	protected int quorumSize;
	
	public Process(int pId) {
		processUid = pId;
		quorumSize = 3;
	}
	
	public int getUniqueId() {
		
		return processUid;
	}
}
