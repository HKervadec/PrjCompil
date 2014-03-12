
public enum Operande{
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
	
	private Operande(String s){
		this.symbole = s;
	}
}