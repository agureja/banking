
public class ProposalId {

	private int number;
	private int uniqueId;
	public ProposalId(int uniqueId) {
		  
		this.uniqueId = uniqueId;
		this.number = 0;
	}
	public void reset() {
		this.number = 0;
	}
	public ProposalId(int uniqueId, int number) {
		  
		this.uniqueId = uniqueId;
		this.number = number;
	}
	public int getUniqueId() {
		return uniqueId;
	}
	
	public void setUniqueId(int id) {
		this.uniqueId = id;
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
		
			if(uniqueId == temp.getUniqueId())
					return 1;
			else 
				return -1;
		}
		return 0;		
	}
	
}
