import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Proposer extends Process {

	ProposalId propId;
	ProposalId prevId;
	
	Set<UUID> promiseList;
	
	public Proposer(UUID processUid) {
		super(processUid);
		propId = new ProposalId(processUid); 
		value = 0;
		promiseList = new HashSet<UUID>(); 
	}
	
	public boolean sendPrepare(int value) {
		
		promiseList.clear();
		propId.setNumber(propId.getNumber()+1);
		Message.sendPrepare(propId);
		return true;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean recievePromise(UUID uuid, ProposalId propId, ProposalId prevPropId, int value) {
		
		
		if(prevId == null) {
			
			prevId = new ProposalId(prevPropId.getUniqueId(),prevPropId.getNumber());
		}
		else if(prevId.CompareTo(prevPropId)<0) {
			
			prevId.setUniqueId(prevPropId.getUniqueId());
			prevId.setNumber(prevPropId.getNumber());
			this.value = value;
		} else {
			
			promiseList.add(uuid);
		}
		if(promiseList.size() > quorumSize)
			Message.sendAccept(propId, value);
		return true;
	}
	
}
