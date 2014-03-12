import java.util.*;

public class Expression{

	private final static int TAILLEMAX = 50;
	
	public Type[] tabType;
	public Operande[] tabOp;
	
	private enum ClassOp { alg, dif, eg, log }

	
	private static HashMap< Operande, ClassOp> tableType = new HashMap<Operande,ClassOp>();
	static
	{
		// Classe algébrique
		tableType.put(Operande.plus , ClassOp.alg);
		tableType.put(Operande.moins, ClassOp.alg);
		tableType.put(Operande.div , ClassOp.alg);
		tableType.put(Operande.fois , ClassOp.alg);
		
		// Classe différence
		tableType.put(Operande.inf , ClassOp.dif);
		tableType.put(Operande.sup , ClassOp.dif);
		tableType.put(Operande.infeg , ClassOp.dif);
		tableType.put(Operande.supeg , ClassOp.dif);
		
		// Classe égalité
		tableType.put(Operande.egal , ClassOp.eg);
		tableType.put(Operande.dif , ClassOp.eg);
		
		// Classe logique
		tableType.put(Operande.et , ClassOp.log);
		tableType.put(Operande.ou , ClassOp.log);
	}
	
	private static HashMap< ClassOp, Type[] > tableLicite = new HashMap<ClassOp,Type[]>();
	static {
		Type[] typeAlg =  {Type.entier,Type.erreur};
		tableLicite.put(ClassOp.alg,  typeAlg );
		
	}
	
	

	
	public Expression(){
		tabType = new Type[TAILLEMAX];
		tabOp = new Operande[TAILLEMAX];
	}
	
	
	
}		