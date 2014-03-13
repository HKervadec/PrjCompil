import java.util.*;

public class TabIdent{
	
	private HashMap<String,Ident> table;
	
	
	public TabIdent(int taille){
		table = new HashMap<String,Ident>(taille);
	}
	
	public Ident chercheIdent(String clef){
		return table.get(clef);
	}
	
	public boolean existeIdent(String clef){
		return table.containsKey(clef);
	}
	
	public void rangeIdent(String clef, Ident id){
		table.put(clef,id);
	}
	
	public Type getTypeIdent(String nomVar){
		return table.get(nomVar).getType();
	}
	
	public void affichage(){
		Set<String> listecle = table.keySet();
		Iterator<String> i  = listecle.iterator();
		String clef;
		while(i.hasNext() ){
			clef = i.next();
			System.out.println( clef + " | " + chercheIdent(clef).toString() );
		}
	}
	
	

}