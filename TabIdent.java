import java.util.HashMap;

/**
 * Class managing the identifier of the program.
 * Will memoryze the values of the constants,
 * and the offset of the variables, with also their names & types.*
 */
public class TabIdent{
    // FIXME, I'M UGLY
    public static Type lastType;
    private HashMap<String, Ident> table;
    private String id_tmp;
    
    private int offset;
   
    
    public TabIdent(){
        this.table = new HashMap<String, Ident>();
        this.offset = -2;
    }
    
    
    /**
     * Will just list the content of the table.
     * Usefull for debugging purpose.
     * @return String
     */    
    public String toString(){
        String result = "";
        
        for(String key : this.table.keySet()){
            result += this.table.get(key) + "\n";
        }
        
        return result;
    }
    
    /**
     * Find the the identifier according to the string parameter
     * @param key 
     * @return Ident the ident, null if not found
     */
    public Ident searchIdent(String key){
        return this.table.get(key);
    }
    
    
    /**
     * Test if a ident if in the tabIdent
     * @param key 
     * @return boolean
     */    
    public boolean existIdent(String key){
        return this.table.containsKey(key);
    }
    
    /**
     * Put a ident in the table.
     * Raise an error with the errormanager if the ident is already
     * in the tab.
     * @param key of the ident
     * @param id the ident himself
     */    
    public void putIdent(String key, Ident id){
        if(this.searchIdent(key) == null){
            this.table.put(key, id);
        }else{
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.ALREADY_DECLARED,
                                            key);
        }
    }
    
    
    /**
     * Used for the const declaration.
     * Fix the name for the next const whose value is set from the functions
     * set setTmpValue or setTmpFrom.
     * @param id 
     */
    public void setTmpId(String id){
        this.id_tmp = id;
    }
    
    
    /**
     * Put an ident in the table (the usuals tests will be done in the putIdent
     * function.
     * The value is a fixed value, for example 3,-5, VRAI
     * The ident will have the nave contained in the id_tmp var.
     * Mainly (and maybe only) used in the const declaration
     * @param var is a var or a const
     * @param type 
     * @param value 
     */
    public void setTmpValue(boolean var, Type type, int value){
        Ident id = new Ident(var, this.id_tmp, type, value);
        // this.table.put(this.id_tmp, id);
        this.putIdent(this.id_tmp, id);
    }
    
    
    /**
     * Put an ident in the table (the usuals tests will be done in the putIdent
     * function.
     * The ident will have the nave contained in the id_tmp var.
     * Mainly (and maybe only) used in the const declaration
     * @param var 
     * @param source : the ident will have the value of the ident source.
     * Raise an error if the source does not exist.
     */
    public void setTmpFrom(boolean var, String source){
        if(this.table.containsKey(source)){
            Ident id = new Ident(var, 
                                this.id_tmp, 
                                this.table.get(source).getType(), 
                                this.table.get(source).getValue());
            // this.table.put(this.id_tmp, id);
            this.putIdent(this.id_tmp, id);
        }else{
            // System.err.println("Error: " + source + " has not been previously defined.");
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.UNDEFINED_IDENT,
                                            source);
        }
    }
    
    
    /**
     * Add a variable in the tab.
     * It will have the type of the lastType variable.
     * The offset is automagically managed.
     * @param name name of the variable.
     */
    public void addVar(String name){
        Ident id = new Ident(true, name, TabIdent.lastType, this.offset);
        this.offset -= 2;
        
        // this.table.put(name, id);
        this.putIdent(name, id);
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