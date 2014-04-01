/**
 * Used for the variable declaration.
 */


public class Variable{
    private Type type;
    private int offset;
    
    public Variable(){
        this.offset = -2;
    }
    
    
    /**
     * Set the type used for the next variables declarations. 
     * @param t 
     */
    
    public void setType(Type t){
        this.type = t;
    }

    /**
     * Add a variable in the tab.
     * It will have the type of the lastType variable.
     * The offset is automagically managed.
     * @param name name of the variable.
     */
    public void addVar(String name){
        Ident id = new Ident(true, name, this.type, this.offset);
        this.offset -= 2;
        
        Yaka.tabIdent.putIdent(name, id);
    }
    
    
    /**
     * Add the ouvrePrinc -nbVar*2.
     * Is called at the end of the variables declaration.
     * It is the command used in the yvm to reserve space for the variables.
     */    
    public void end(){
        Yaka.yvm.add(new Instruction("ouvrePrinc", -(this.offset+2)));
    }
}