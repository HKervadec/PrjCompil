
public enum Operation{
	plus("+"),
	moins("-"),
	fois("*"),
	div("/"),
	
	inf("<"),
	sup(">"),
	supeg(">="),
	infeg("<="),
	
	egal("="),
	dif("<>"),
	et("et"),
	ou("ou");
	
	String symbole;
	
	private Operation(String s){
		this.symbole = s;
	}
}