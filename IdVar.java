
public class IdVar extends Ident{

	private String type;
	private int offset;
	
	public IdVar(String t, int o){
		type = t;
		offset = o;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int val) {
		this.offset = offset;
	}
	
}