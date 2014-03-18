import java.util.HashMap;

public class TabIdent{
    public static Type lastType;
    private HashMap<String, Ident> table;
    private String id_tmp;
    
    private int offset;
   
    
    public TabIdent(){
        this.table = new HashMap<String, Ident>();
        this.offset = -2;
    }
    
    public String toString(){
        String result = "";
        
        for(String key : this.table.keySet()){
            result += this.table.get(key) + "\n";
        }
        
        return result;
    }
    
    public Ident searchIdent(String key){
        return this.table.get(key);
    }
    
    public boolean existIdent(String key){
        return this.table.containsKey(key);
    }
    
    public void putIdent(String key, Ident id){
        this.table.put(key, id);
    }
    
    
    
    public void setTmpId(String id){
        this.id_tmp = id;
    }
    
    public void setTmpValue(boolean var, Type type, int value){
        Ident id = new Ident(var, this.id_tmp, type, value);
        this.table.put(this.id_tmp, id);
    }
    
    public void setTmpFrom(boolean var, String source){
        if(this.table.containsKey(source)){
            Ident id = new Ident(var, 
                                this.id_tmp, 
                                this.table.get(source).getType(), 
                                this.table.get(source).getValue());
            this.table.put(this.id_tmp, id);
        }else{
            System.err.println("Error: " + source + " has not been previously defined.");
            System.err.println("");
        }
    }
    
    public void addVar(String name){
        Ident id = new Ident(true, name, TabIdent.lastType, this.offset);
        this.offset -= 2;
        
        this.table.put(name, id);
    }
    
    
    public void end(){
        Yaka.yvm.add(new Instruction("ouvrePrinc", -(this.offset+2)));
    }
}