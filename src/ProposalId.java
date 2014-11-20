import java.util.UUID;



public class ProposalId {

	private int number;
	private UUID uniqueId;
	public ProposalId(UUID uniqueId) {
		  
		this.uniqueId = uniqueId;
		this.number = 0;
	}
	public ProposalId(UUID uniqueId, int number) {
		  
		this.uniqueId = uniqueId;
		this.number = number;
	}
	public UUID getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueId(UUID uuid) {
		this.uniqueId = uuid;
	}
	
	public void setNumber(int value) {
		number = value;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int CompareTo(ProposalId temp) {
		

		if(this.number > temp.getNumber())
			return 1;
		else if(this.number < temp.getNumber())
			return -1;
		else if (this.number == temp.number) {
		
			if(uniqueId.equals(temp.getUniqueId()))
					return 1;
			else 
				return -1;
		}
		return 0;		
	}
	
}
