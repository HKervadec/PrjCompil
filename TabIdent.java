import java.util.HashMap;

public class TabIdent{
    public static Type lastType;
    private HashMap<String, Ident> table;
    private String id_tmp;
    
    private int offset;
   
    //constructor
    public TabIdent(){
        this.table = new HashMap<String, Ident>();
        this.offset = -2;
    }
    //to string
    public String toString(){
        String result = "";
        
        for(String key : this.table.keySet()){
            result += this.table.get(key) + "\n";
        }
        
        return result;
    }
    //search an ident in the table
    public Ident searchIdent(String key){
        return this.table.get(key);
    }
    //check if the ident in parameters is already in the table
    public boolean existIdent(String key){
        return this.table.containsKey(key);
    }
    //put an ident with his key in the table
    public void putIdent(String key, Ident id){
        this.table.put(key, id);
    }
    
	//settter
    public void setTmpId(String id){
        this.id_tmp = id;
    }
    
	//put the id with the value, the type and the var in the table
    public void setTmpValue(boolean var, Type type, int value){
        Ident id = new Ident(var, this.id_tmp, type, value);
        this.table.put(this.id_tmp, id);
    }
    //check if the previous member of the declaration exist and then, 
	//if it's true, put the new constant in the table
    public void setTmpFrom(boolean var, String source){
        if(this.table.containsKey(source)){
            Ident id = new Ident(var, 
                                this.id_tmp, 
                                this.table.get(source).getType(), 
                                this.table.get(source).getValue());
            this.table.put(this.id_tmp, id);
        }else{
            System.err.println("Error: " + source + " has not been previously defined.");
        }
    }
    
	//add a variable in the table
    public void addVar(String name){
        Ident id = new Ident(true, name, TabIdent.lastType, this.offset);
        this.offset -= 2;
        
        this.table.put(name, id);
    }
    
    //put at the and of the const bloc, an yvm instruction : ouvrePrinc
    public void end(){
        Yaka.yvm.add(new Instruction("ouvrePrinc", -(this.offset+2)));
    }
}