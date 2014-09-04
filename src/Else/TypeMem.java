package Else;

import java.util.Stack;

public class TypeMem{
	private Stack<Type> type;

	public TypeMem(){
		this.type = new Stack<Type>();
	}

	public Type pop(){
		return this.type.pop();
	}

	public Type peek(){
		return this.type.peek();
	}
	
	public void push(Type t){
		this.type.push(t);
	}
}