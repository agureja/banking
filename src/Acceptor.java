import java.util.UUID;


public class Acceptor extends Process {

	private ProposalId promise;
	private ProposalId acceptedID;
	
	public Acceptor(UUID processUid) {
		super(processUid);
		promise= new ProposalId(processUid);
		value = 0;
	}
	
	public void receivePrepare(ProposalId proposal) {
		
		if (promise.CompareTo(proposal)<0) {
			promise.setNumber(proposal.getNumber());
			promise.setUniqueId(proposal.getUniqueId());
			Message.sendPromise(proposal, acceptedID, value);
		}
	}

	public void receiveAcceptRequest(UUID uniqueId,ProposalId proposal,	int value) {
		
		if (proposal.CompareTo(promise)>0) {
			promise.setNumber(proposal.getNumber());
			promise.setUniqueId(proposal.getUniqueId());
			
			acceptedID.setNumber(proposal.getNumber());
			acceptedID.setUniqueId(proposal.getUniqueId());
			
			this.value = value;
			
			Message.sendAccepted(acceptedID, this.value);
		}
	}

}
