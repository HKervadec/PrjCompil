public class IdConst extends Ident{
	
	private String type;
	private int val;
	
	
	public IdConst(String t,int v){
		type = t;
		val = v;
	}
	
	public int getVal() {
		return val;
	}
	public void setVal(int v) {
		this.val = v;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
