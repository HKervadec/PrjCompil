public class IdConst extends Ident{
	

	private int offset;
	
	
	public IdConst(Type t,int o){
		super(t);
		offset = o;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
}
