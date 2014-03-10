
public class IdVar extends Ident{
	private String type;
	private int val;
	
	public IdVar(String t, int v){
		type = t;
		val = v;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	
}