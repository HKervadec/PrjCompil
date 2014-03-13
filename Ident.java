public class Ident{
    public static int BOOLEAN = 0;
    public static int ENTIER = 1;

    private boolean var;
    private String id;
    private int type;
    private int value;
    
    
    public Ident(boolean var, String id, int type, int value){
        this.var = var;
        this.id = id;
        this.type = type;
        this.value = value;
    }
    
    
    public int getValue(){
        return this.value;
    }
    
    public int getType(){
        return this.type;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String toString(){
        return this.var + " " + this.id + " " + this.type + " " + this.value;
    }
}