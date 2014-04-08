import java.util.Stack;

public class FctManager{
    private FctIdent current_fct;

    private Stack<String> fctCall;

    public FctManager(){
        this.fctCall = new Stack<String>();
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
        int i = this.current_fct.paramSize();
        i *= 2;
        i += 4;

        Yaka.yvm.add(new Instruction("ireturn", i));
    }

    public void pushFct(String name){
        Yaka.yvm.add(new Instruction("reserveRetour"));

        this.fctCall.push(name);
    }

    public void popFct(){
        String name = this.fctCall.pop();

        Yaka.yvm.add(new Instruction("call", name));

        FctIdent f = Yaka.tabIdent.getFunction(name);
        Type t = f.getResultType();
        Yaka.expression.dropType(f.paramSize());
        Yaka.expression.pushType(t);
    }
}