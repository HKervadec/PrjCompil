import java.util.HashMap;

/**
 * Class managing the identifier of the program.
 * Will memoryze the values of the constants,
 * and the offset of the variables, with also their names & types.*
 */
public class TabIdent{
    private HashMap<String, Ident> table;
   
    
    public TabIdent(){
        this.table = new HashMap<String, Ident>();
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
}