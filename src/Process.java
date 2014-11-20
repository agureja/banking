import java.util.UUID;


public class Process {
	private static UUID processUid;
	protected int value;
	protected int quorumSize;
	
	public Process(UUID pId) {
		processUid = pId;
		quorumSize = 3;
	}
	
	public UUID getUniqueId() {
		
		return processUid;
	}
}
