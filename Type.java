
public enum Type{
	booleen,
	entier,
	erreur;
	
	public String toString(){
		switch(this){
		case booleen :
			return "bool";
		case entier :
			return "entier";
		case erreur :
			return "erreur";
		default :
			return "pb";
		}
	}
}