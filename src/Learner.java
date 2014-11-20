import java.util.HashMap;
import java.util.UUID;


public class Learner extends Process {

	HashMap<Integer,Integer> acceptedValues;
	
	public Learner(UUID processUid) {
		super(processUid);
		acceptedValues = new HashMap<Integer, Integer>();
		value=0;
	}
	
	public void receiveAcceptRequest(UUID uniqueId,ProposalId proposal,	int value) {
		
		if(acceptedValues.containsKey(value)){
			acceptedValues.put(value, acceptedValues.get(value)+1);
		} else {
			acceptedValues.put(value,0);
		}
		if(acceptedValues.get(value) > quorumSize) {
			
			this.value = value;
			acceptedValues.clear();
		}
	}
	
}
