public class Instruction{
    public String inst;
    public boolean hasOption;
    public int option;
    
    public Instruction(String inst){
        this.inst = inst;
        this.hasOption = false;
    }
    
    public Instruction(String inst, int option){
        this.inst = inst;
        this.hasOption = true;
        this.option = option;
    }
    
    public String toString(){
        if(this.hasOption){
            return this.inst +  " " + this.option;
        }
        
        return this.inst;
    }
}