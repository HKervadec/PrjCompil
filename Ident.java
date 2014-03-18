/**
 * An identifier is the way we save variables and const.
 * Fuck this I'm not going to explain this, read the code.
 */


public class Ident{
    public boolean var;
    public String id;
    private Type type;
    private int value;
    
    
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