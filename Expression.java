import java.util.Stack;


public class Expression{
    private Stack<Op> stack_ops;
    private Stack<Ident> stack_ids;
    private Stack<Type> stack_types;
    

    
    public Expression(){
        this.stack_ops = new Stack<Op>();
        this.stack_ids = new Stack<Ident>(); 
        this.stack_types = new Stack<Type>(); 
        this.init();
    }
    
    public void init(){
        this.stack_types = new Stack<Type>();
    }
    
    public void check(){
        int pos = this.stack_types.search(Type.ERROR);
        
        if(pos != -1){
            Yaka.errorManager.printError(ErrorSource.COMPILER, 
                                ErrorType.EXPRESSION_ERROR,
                                "");
        }
    }
    
    public void pushOp(Op op){
        this.stack_ops.push(op);
    }
    
    public void pushType(Type t){
        this.stack_types.push(t);
    }
    
    public void pushId(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.MISSING_IDENTIFIER,
                                            "");
            return;
        }
        
        this.stack_ids.push(id);
    }
    
    public void popOp(){
        Op op = this.stack_ops.pop();
        
        this.pushType(this.sanity(op));
        
        
        // FIXME
        switch(op){
            case ADD:
                Yaka.yvm.add(new Instruction("iadd"));
                break;
            case SUB:
                Yaka.yvm.add(new Instruction("isub"));
                break;
            case MUL:
                Yaka.yvm.add(new Instruction("imul"));
                break;
            case DIV:
                Yaka.yvm.add(new Instruction("idiv"));
                break;
            case NOT:
                Yaka.yvm.add(new Instruction("inot"));
                break;
            case NEG:
                Yaka.yvm.add(new Instruction("ineg"));
                break;
            case AND:
                Yaka.yvm.add(new Instruction("iand"));
                break;
            case OR:
                Yaka.yvm.add(new Instruction("ior"));
                break;
            case EQ:
                Yaka.yvm.add(new Instruction("iegal"));
                break;
            case LESS:
                Yaka.yvm.add(new Instruction("iinf"));
                break;
            case GRT:
                Yaka.yvm.add(new Instruction("isup"));
                break;
            case LEQ:
                Yaka.yvm.add(new Instruction("iinfegal"));
                break;
            case GRQ:
                Yaka.yvm.add(new Instruction("isupegal"));
                break;
            case NEQ:
                Yaka.yvm.add(new Instruction("idiff"));
                break;
            default:
                System.err.println("Operator unknown");
                break;
        }
        
        // System.out.println(this.stack_types);
        
    }
    
    
    // FIXME
    public Type sanity(Op op){
        // System.out.println("sanity: " + this.stack_types);
        
        
        Type t1;
        try{
            t1 = this.stack_types.pop();
        }catch(Exception e){
            return Type.ERROR;
        }
		switch(op){
			case NOT:
				switch(t1){
                    case INTEGER:
                        return Type.ERROR;
                    case BOOLEAN:
                        return Type.BOOLEAN;
                    default:
                        return Type.ERROR;
                }
			case NEG:
				switch(t1){
                    case INTEGER:
                        return Type.INTEGER;
                    case BOOLEAN:
                        return Type.ERROR;
                    default:
                        return Type.ERROR;
                }
		}
		
		
        Type t2;
        try{
            t2 = this.stack_types.pop();
        }catch(Exception e){
            return Type.ERROR;
        }
        
        if(t1 != t2){
            return Type.ERROR;
        }
        
        
        switch(op){
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                switch(t1){
                    case INTEGER:
                        return Type.INTEGER;
                    case BOOLEAN:
                        return Type.ERROR;
                    default:
                        return Type.ERROR;
                }
            case LEQ:
            case GRQ:
            case LESS:
            case GRT:
                switch(t1){
                    case INTEGER:
                        return Type.BOOLEAN;
                    case BOOLEAN:
                        return Type.ERROR;
                    default:
                        return Type.ERROR;
                }
            case EQ:
            case NEQ:
                switch(t1){
                    case INTEGER:
                        return Type.BOOLEAN;
                    case BOOLEAN:
                        return Type.BOOLEAN;
                    default:
                        return Type.ERROR;
                }
            case AND:
            case OR:
                switch(t1){
                    case INTEGER:
                        return Type.ERROR;
                    case BOOLEAN:
                        return Type.BOOLEAN;
                    default:
                        return Type.ERROR;
                }
            default:
            return Type.ERROR;
        }
    }
    
    public void popId(){
        Ident id = this.stack_ids.pop();
        
        if(!id.var){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.RAPING_CONST,
                                            id.id);
            /* System.err.println("Error: Trying to change the value of a constant");
            System.err.println(""); */
            return;
        }
        
        Type tmp = this.stack_types.pop();
        if(id.getType() != tmp){
            String mess = "Expected: " + id.getType() + "\n";
            mess += "Got: " + tmp;
            Yaka.errorManager.printWarning(ErrorSource.COMPILER,
                                            ErrorType.TYPES_MISMATCH,
                                            mess);
            /* System.err.println("Warning: types mismatch");
            System.err.println(id.id + " = ");
            System.err.println("Expected: " + id.getType());
            System.err.println("Got: " + tmp);
            System.err.println(""); */
        }
        
        Yaka.yvm.add(new Instruction("istore", id.getValue()));
    }
    
    
    public void load(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.MISSING_IDENTIFIER,
                                            name);
        }else{
            if(id.var){
                this.loadVar(id.getValue());
            }else{
                this.loadConst(id.getValue());
            }
            
            this.stack_types.push(id.getType());
        }
    }
   
    
    public void loadVar(int offset){
        Yaka.yvm.add(new Instruction("iload", offset));
    }
    
    public void loadConst(int value){
        Yaka.yvm.add(new Instruction("iconst", value));
    }
    
    public void neg(){
        Yaka.yvm.add(new Instruction("ineg"));
    }
}