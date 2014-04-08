import java.util.ArrayList;

public class FctIdent{
	private String name;
	private Type resultType;
	private ArrayList<Type> paramsType;

	public FctIdent(Type t, String s){
		this.name = s;
		this.resultType = t;
		this.paramsType = new ArrayList<Type>();

		if(s == "main"){
			// Yaka.errorManager.put
		}
	}

	public Type getResultType(){
		return this.resultType;
	}

	public void addType(Type t){
		this.paramsType.add(t);
	}

	public int paramSize(){
		return this.paramsType.size();
	}

	public String getName(){
		return this.name;
	}

	public String toString(){
		String result = resultType + " " + name + "\n\t";
		for(Type t : paramsType){
			result += t + " ";
		}
		return result + "\n";
	}
}