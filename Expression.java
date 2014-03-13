import java.util.*;

public class Expression{

	private final static int TAILLEMAX = 50;
	
	public Stack<Type> pileType;
	public Stack<Operation> pileOp;
	
	private enum ClassOp { alg, dif, eg, log }

	
	private static HashMap< Operation, ClassOp> tableType = new HashMap<Operation,ClassOp>();
	static
	{
		// Classe algébrique
		tableType.put(Operation.plus , ClassOp.alg);
		tableType.put(Operation.moins, ClassOp.alg);
		tableType.put(Operation.div , ClassOp.alg);
		tableType.put(Operation.fois , ClassOp.alg);
		
		// Classe différence
		tableType.put(Operation.inf , ClassOp.dif);
		tableType.put(Operation.sup , ClassOp.dif);
		tableType.put(Operation.infeg , ClassOp.dif);
		tableType.put(Operation.supeg , ClassOp.dif);
		
		// Classe égalité
		tableType.put(Operation.egal , ClassOp.eg);
		tableType.put(Operation.dif , ClassOp.eg);
		
		// Classe logique
		tableType.put(Operation.et , ClassOp.log);
		tableType.put(Operation.ou , ClassOp.log);
	}
	
	// déclaration du tableau associatif des opérations licites et illicites
	// clef : Classe d'opération
	// Valeur : Tableau de Type => Type[0] -> les opérandes sont des entiers,
	//							   Type[1] -> Les opérandes sont des booleens.
	
	private static HashMap< ClassOp, Type[] > tableLicite = new HashMap<ClassOp,Type[]>();
	static {
		Type[] typeAlg =  {Type.entier,Type.erreur};
		tableLicite.put(ClassOp.alg,  typeAlg );
		Type[] typeDif = {Type.booleen,Type.erreur};
		tableLicite.put(ClassOp.dif, typeDif);
		Type[] typeEg = {Type.booleen, Type.booleen };
		tableLicite.put( ClassOp.eg, typeEg);
		Type[] typeLog = {Type.erreur,Type.booleen };
		tableLicite.put( ClassOp.log, typeLog);
	}
	
	
	public Expression(){
		pileType = new Stack<Type>();
		pileOp = new Stack<Operation>();
	}
	
	public ClassOp getClassOp(Operation op){
		return tableType.get(op);
	}
	
	
	// Rends pour une opération donné et le type des opérandes le type retourné.
	public Type typeResultatBinaire(Operation op,Type typeOperande){
		Type typeRes;
		if(typeOperande == Type.entier ){
			typeRes = tableLicite.get(getClassOp(op))[0];
		}
		if(typeOperande == Type.booleen) {
			typeRes = tableLicite.get(getClassOp(op))[1];
		}
		else {
			typeRes = Type.erreur;
		}
		return typeRes;
	}
	
	public void ajtType(Type t){
		pileType.push(t);
	}
	
	public void ajtOp(Operation op){
		pileOp.push(op);
	}
	
	
	
	
	
	
	
	
	
}		