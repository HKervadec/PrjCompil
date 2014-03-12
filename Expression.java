import java.util.*;

public class Expression{

	private final static int TAILLEMAX = 50;
	
	public Type[] tabType;
	public Operande[] tabOp;
	
	private enum ClassOp { Alg; }
	
	
	
	private HashMap< Operande, ClassOp> tableType = new HashMap<Operande,ClassOp>();
	
	private void init(){
		/* probleme => besoin d'une méthode d'initilisation = caca.*/
	tableType.put(Operande.dif , ClassOp.Alg);
	}
	
	public Expression(){
		tabType = new Type[TAILLEMAX];
		tabOp = new Operande[TAILLEMAX];
	}
	
	
	
}		