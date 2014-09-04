package Manager;

import java.util.Stack;

import Core.Yaka;
import Else.Type;
import Else.Ident;
import Else.FctIdent;
import Else.Instruction;
import Error.ErrorType;
import Error.ErrorSource;

public class FctManager{
    private FctIdent current_fct;

    private Stack<String> fctCall;
    private Stack<Stack<Type>> fctParams;
	private Stack paramsN; 

    public FctManager(){
        this.fctCall = new Stack<String>();
        this.fctParams = new Stack<Stack<Type>>();
		this.paramsN = new Stack();
    }


    public void reset(){
        Yaka.tabIdent.clear();
        Yaka.variable.reset();
        // Yaka.expression.reset();
    }

    public void setCurrentFct(FctIdent fct){
        this.current_fct = fct;
    }


    public void addType(Type t){
        this.current_fct.addType(t);
    }

    public void setOffset(){
        Yaka.tabIdent.setParamOffset(this.current_fct.paramSize());
    }

    public void putFct(){
        String name = this.current_fct.getName();
        Yaka.tabIdent.putFct(name, this.current_fct);
        Yaka.yvm.add(new Instruction(name + ":", true));
    }

    public void addVar(String name, Type t){
        Ident id = new Ident(true, name, t, 0);
        
        Yaka.tabIdent.putIdent(name, id);
    }

    public void endFct(){
        Yaka.yvm.add(new Instruction("fermeBloc", 
                                    2*this.current_fct.paramSize()));
        Yaka.yvm.add(new Instruction(""));
    }

    public void iReturn(){
		if(this.current_fct.getName().equals("main")){
			Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.RETURN_MAIN);
		}
	
        int i = this.current_fct.paramSize();
        i *= 2;
        i += 4;

        Yaka.yvm.add(new Instruction("ireturn", i));

        Type t = this.current_fct.getResultType();
        Yaka.expression.check(t);
    }

    public void pushFct(String name){
        Yaka.yvm.add(new Instruction("reserveRetour"));

        this.fctCall.push(name);

        FctIdent f = Yaka.tabIdent.getFunction(name);
        this.fctParams.push(f.getParams());
		
		this.paramsN.push(f.paramSize());
    }

    public void popFct(){
        String name = this.fctCall.pop();

        Yaka.yvm.add(new Instruction("call", name));

        FctIdent f = Yaka.tabIdent.getFunction(name);
        Type t = f.getResultType();
        Yaka.expression.dropType(f.paramSize(), f.getParams());
        Yaka.expression.pushType(t);

        this.fctParams.pop();
		
		if((int)this.paramsN.pop() < 0){
			Yaka.errorManager.printError(ErrorSource.COMPILER,
                                        ErrorType.MUCH_PARAM);
		}
    }

    public void popNCheck(){
		// int n = this.paramsN.pop();
		this.paramsN.push((int)this.paramsN.pop() - 1);
       /* try{
            Yaka.expression.check(this.fctParams.peek().pop());
        }catch(Exception e){
            System.out.println(this.fctParams.peek());
        }*/
    }
}