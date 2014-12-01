import java.util.UUID;


public class Acceptor extends Process {

	private ProposalId promise;
	private ProposalId acceptedID;
	
	public Acceptor(int processUid) {
		super(processUid);
		promise= new ProposalId(processUid);
		value = 0;
	}
	
	public void reset() {
		
		promise.reset();
		acceptedID.reset();
	}
	
	public void receivePrepare(ProposalId proposal) {
		
		if (promise.CompareTo(proposal)<0) {
			promise.setNumber(proposal.getNumber());
			promise.setUniqueId(proposal.getUniqueId());
			NetworkSender.sendPromise(proposal, acceptedID, value);
		}
	}

	public void receiveAcceptRequest(int uniqueId,ProposalId proposal,	int value) {
		
		if (proposal.CompareTo(promise)>0) {
			promise.setNumber(proposal.getNumber());
			promise.setUniqueId(proposal.getUniqueId());
			
			acceptedID.setNumber(proposal.getNumber());
			acceptedID.setUniqueId(proposal.getUniqueId());
			
			this.value = value;
			
			NetworkSender.sendAccepted(acceptedID, this.value);
		}
	}

}
