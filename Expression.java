import java.util.Stack;


public class Expression{
    private Stack<Op> stack_ops; //Stack of operator
    private Stack<Ident> stack_ids; //Stack of Identificateur
    private Stack<Type> stack_types; //Stack of Type
    

    /*
	*Constructor
	*/
    public Expression(){
        this.stack_ops = new Stack<Op>();
        this.stack_ids = new Stack<Ident>(); 
        this.stack_types = new Stack<Type>(); 
    }
    
	/*
	* Stack one operator in the stack of operator
	*/
    public void pushOp(Op op){
        this.stack_ops.push(op);
    }
    
	/*
	* Stack one type in the stack of type
	*/
    public void pushType(Type t){
        this.stack_types.push(t);
    }
    
	/*
	* Stack one identificateur in the stack of identificateur 
	* if it doesn't exist, error
	*/
    public void pushId(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            System.err.println("Error: Identifier doesn't exist");
            return;
        }
        
        this.stack_ids.push(id);
    }
	
	/*
	* Pop one operator and add it in the yvm list of instruction
	*/
    
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
    
    /*
	* test the semantic of the expression
	*/

    public Type sanity(Op op){
        
        Type t1 = this.stack_types.pop();
		
		switch(op){ 
			case NOT:
				if(t1 == Type.BOOLEAN){
					return Type.BOOLEAN;
				}else{
					return Type.ERROR;
				}
				
				
			case NEG:
				if(t1 == Type.INTEGER){
					return Type.INTEGER;
				}else{
					return Type.ERROR;
				}
				/*switch(t1){
                    case INTEGER:
                        return Type.INTEGER;
                    case BOOLEAN:
                        return Type.ERROR;
                    default:
                        return Type.ERROR;
                }*/
		}
		
		/* In the case of NOT or NEG you don't have to check the second type */
		
        Type t2 = this.stack_types.pop();
        
        if(t1 != t2){
            return Type.ERROR;
        }
        
        
        switch(op){
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                if(t1 == Type.INTEGER){
					return Type.INTEGER;
				}else{
					return Type.ERROR;
				}
            case LEQ:
            case GRQ:
            case LESS:
            case GRT:
                if(t1 == Type.INTEGER){
					return Type.BOOLEAN;
				}else{
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
                if(t1 == Type.BOOLEAN){
					return Type.BOOLEAN;
				}else{
					return Type.ERROR;
				}
            default:
                return Type.ERROR;
        }
    }
    
	/*
	* Check if the id is a variable and has the same type as in the stack
	*/
    public void popId(){
        Ident id = this.stack_ids.pop();
        
        if(!id.var){
            System.err.println("Error: Trying to change the value of a constant");
            return;
        }
        
        if(id.getType() != this.stack_types.pop()){
            System.err.println("Warning: types mismatch");
        }
        
        Yaka.yvm.add(new Instruction("istore", id.getValue()));
    }
    
    /*
	*test to make the difference between the instruction iload and iconst
	*/
    public void load(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            System.err.println("Error: Identifier doesn't exist");
        }else{
            if(id.var){
                this.loadVar(id.getValue());
            }else{
                this.loadConst(id.getValue());
            }
            
            this.stack_types.push(id.getType());
        }
    }
   
    /*
	* add iload instruction in the yvm
	*/
    public void loadVar(int offset){
        Yaka.yvm.add(new Instruction("iload", offset));
    }
    
	/*
	*	add iconst instruction in the yvm
	*/
    public void loadConst(int value){
        Yaka.yvm.add(new Instruction("iconst", value));
    }
	
    /*
	*	add ineg instruction in the yvm
	*/
    public void neg(){
        Yaka.yvm.add(new Instruction("ineg"));
    }
    
    /*
	* Check the different type in an expression. If a type mismatch --> error
	* at the end, a new stack of type is build
	*/
    public void checkAndClearTypes(){
        int pos = this.stack_types.search(Type.ERROR);
        
        if(pos != -1){
            System.err.println("Error while parsing the expression.");
        }
        
        this.stack_types = new Stack<Type>();
    }
}