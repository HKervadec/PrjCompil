public class Ident{
    public boolean var; // the identificateur is a variable or not
    public String id; // the name of the identificateur
    private Type type; // the type
    private int value; // the value
    
    
    public Ident(boolean var, String id, Type type, int value){
        this.var = var;
        this.id = id;
        this.type = type;
        this.value = value;
    }
    
    
    public int getValue(){
        return this.value;
    }
    
    public Type getType(){
        return this.type;
    }
    
    public String getId(){
        return this.id;
    }
    
    
    public String toString(){
        return this.var + " " + this.id + " " + this.type + " " + this.value;
    }
}