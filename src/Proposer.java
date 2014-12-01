import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Proposer extends Process {

	ProposalId propId;
	ProposalId prevIDAcceptedByProposer;
	
	Set<Integer> promiseList;
	
	public Proposer(int processUid) {
		super(processUid);
		propId = new ProposalId(processUid); 
		value = 0;
		//list of user accepting the promise.
		promiseList = new HashSet<Integer>(); 
	}
	
	public void reset() {
		propId.reset();
		prevIDAcceptedByProposer.reset();
		value = 0;
	}
	
	public boolean sendPrepare(int value) {
		
		promiseList.clear();
		propId.setNumber(propId.getNumber()+1); // counter*n+1
		NetworkSender.sendPrepare(propId);
		return true;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean recievePromise(Integer uuid, ProposalId propId, ProposalId prevAcceptedByAcceptor, int value) {
		
		
		if(prevIDAcceptedByProposer == null) {
			
			prevIDAcceptedByProposer = new ProposalId(prevAcceptedByAcceptor.getUniqueId(),prevAcceptedByAcceptor.getNumber());
		}
		else if(prevIDAcceptedByProposer.CompareTo(prevAcceptedByAcceptor)<0) {
			
			prevIDAcceptedByProposer.setUniqueId(prevAcceptedByAcceptor.getUniqueId());
			prevIDAcceptedByProposer.setNumber(prevAcceptedByAcceptor.getNumber());
			this.value = value;
		}
		promiseList.add(uuid);
		if(promiseList.size() >= quorumSize)
			NetworkSender.sendAccept(propId, value);
		return true;
	}
	
}
