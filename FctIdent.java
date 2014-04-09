// import java.util.ArrayList;
import java.util.Stack;

public class FctIdent{
	private String name;
	private Type resultType;

	private Stack<Type> paramsType;
	private int paramCount;

	public FctIdent(Type t, String s){
		this.name = s;
		this.resultType = t;
		// this.paramsType = new ArrayList<Type>();
		this.paramsType = new Stack<Type>();
		this.paramCount = 0;

		if(s == "main"){
			// Yaka.errorManager.put
		}
	}
	
	public Stack<Type> getParams(){
		// return this.paramsType;
		return (Stack<Type>) this.paramsType.clone();
	}

	public Type getResultType(){
		return this.resultType;
	}

	public void addType(Type t){
		// this.paramsType.add(t);
		this.paramsType.push(t);
		this.paramCount++;
	}

	public int paramSize(){
		// return this.paramsType.size();
		return this.paramCount;
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