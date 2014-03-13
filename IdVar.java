
public class IdVar extends Ident{

	protected int val;
	
	public IdVar(Type t, int v){
		super(t);
		val = v;
	}
	

	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	
	public String toString(){
		return this.type + " | " + this.val + " | ";
	}
	
}