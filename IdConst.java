public class IdConst extends Ident{
	
	private String type;
	private int offset;
	
	
	public IdConst(String t,int o){
		type = t;
		offset = o;
	}
	
	public int getOffset() {
		return offset;
	}
	
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
