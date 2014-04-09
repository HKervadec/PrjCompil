import java.util.Stack;

/**
 * Evaluation the expression (generate the appropriated yvm code), and the
 * affectations too.
 */

public class Expression{
    private Stack<Op> stack_ops;
    private Stack<Ident> stack_ids;
    private Stack<Type> stack_types;
    

    /**
     * Just create the stacks.
     */
    public Expression(){
        this.reset();
    }

    public void reset(){
        this.stack_ops = new Stack<Op>();
        this.stack_ids = new Stack<Ident>(); 
        this.stack_types = new Stack<Type>();
    }    
    
    /**
     * Push an operator in the operator stack. 
     * Used in the expression parsing.
     * @param op 
     */
    
    public void pushOp(Op op){
        this.stack_ops.push(op);
    }
    
    
    /**
     * Push an operator in the operator stack. 
     * Used later in the semantic control.
     * @param op 
     */
    public void pushType(Type t){
        this.stack_types.push(t);
    }
    
    
    /**
     * Push an ident in the stack. 
     * Will be later poped and used to write the iload or iconst yvm line.
     * Raise an error if the identifier does not exist.
     * @param name 
     */
    public void pushId(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.MISSING_IDENTIFIER,
                                            name);
            this.stack_ids.push(new Ident());
            return;
        }
        
        this.stack_ids.push(id);
    }
    
    
    /**
     * Pop the first operator on the stack, and add the yvm command according
     * to it.
     * Also push of the type stack the result of the sanity function.
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
    
    
    /**
     * Check if the types on the top of the stack are coherent.
     * It will return the result type of the operation, and an error if the 
     * operation if absurd (for example, adding an integer to a boolean).
     * If the stack is empty (it mean the used fucked up his expression), it 
     * will return an error as well.
     * @see checkAndClearTypes
     * @param op 
     * @return Type
     */
    // FIXME
    public Type sanity(Op op){
        // System.out.println("sanity: " + this.stack_types);
        Type t1 = this.stack_types.pop();
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
		
		
        Type t2 = this.stack_types.pop();
        
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
    
    
    /**
     * Used for affectation
     * Pop an id from the stack, and write the istore value.
     * It does two checks:
     * * Is the identifier a variable, and not a const?
     *   if not, raise an error.
     * * Is the identifier type and the result type (the top of the stack) 
     *   matching? If not, raise an error.
     *
     * Will just add the yvm command "istore offset"
     */    
    public void popId(){
        Ident id = this.stack_ids.pop();
        
        if(!id.var){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.RAPING_CONST,
                                            id.id);
            return;
        }
        
        Type tmp = this.stack_types.peek();
        if(id.getType() != tmp && tmp != Type.ERROR){
            String option = "Expected: " + id.getType() + "\n";
            option += "Got: " + tmp;
            Yaka.errorManager.printWarning(ErrorSource.COMPILER,
                                            ErrorType.TYPES_MISMATCH,
                                            option);
        }
        
        Yaka.yvm.add(new Instruction("istore", id.getValue()));
    }
    
    
    /**
     * Used in the expression evaluation.
     * Load the value of an identifier.
     * Call loadVar if a variable, loadConst otherwise
     * Check if the identifier exist, error if not.
     * @see loadVar
     * @see loadConst
     * @param name 
     */    
    public void load(String name){
        if(Yaka.tabIdent.getFunction(name) != null){
            Yaka.fctManager.pushFct(name);
            return;
        }



        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.MISSING_IDENTIFIER,
                                            name);
            this.stack_types.push(Type.ERROR);
        }else{
            if(id.var){
                this.loadVar(id.getValue());
            }else{
                this.loadConst(id.getValue());
            }
            
            this.stack_types.push(id.getType());
        }
    }
   
    
    /**
     * Just put the "iload offset" in the yvm 
     * @param offset 
     */    
    public void loadVar(int offset){
        Yaka.yvm.add(new Instruction("iload", offset));
    }
    
    
    /**
     * Just put the "iconst value" in the yvm 
     * @param value 
     */    
    public void loadConst(int value){
        Yaka.yvm.add(new Instruction("iconst", value));
    }
    
    
    /**
     * Put "ineg" in the yvm, that's all.
     */
    public void neg(){
        Yaka.yvm.add(new Instruction("ineg"));
    }
    
    
    /**
     * Check if an error is in the stack type, raise an expression error if so.
     * 
     * Also, empty the stack.
     */
    public void checkAndClearTypes(){
        int pos = this.stack_types.search(Type.ERROR);
        
        if(pos != -1){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.EXPRESSION_ERROR);
            
        }
        
        this.stack_types = new Stack<Type>();
    }
    
    public void check(Type expected_result){
        Type result = this.stack_types.pop();
        if(result != expected_result){
            String option = "Expected: " + expected_result + "\n";
            option += "Found: " + result;
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.UNEXPECTED_TYPE,
                                            option);
        }
        
        this.stack_types = new Stack<Type>();
    }

    public void dropType(int n, Stack<Type> st){
        for(int i = 0 ; i < n ; i++){
            try{
                Type exp = st.pop();
                Type got = this.stack_types.pop();

                if(exp != got){
                    String option = "Expected: " + exp + "\n";
                    option += "Got: " + got;
                    Yaka.errorManager.printWarning(ErrorSource.COMPILER,
                                                    ErrorType.PARAM_MISMATCH,
                                                    option);
                }
            }catch(Exception e){
                Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.PARAM_ERROR);
            }
        }
    }
}