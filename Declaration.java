public class Declaration{
	private Type type;
	private int val;
	
	public Declaration(){
	}
	
	public void ajtType(Type t){
		type = t;
	}

	public void ajtVal(int v){
		val = v;
	}

	public IdVar retIdVar(){
		IdVar res = new IdVar(type,0);
		return res;
	}
}

