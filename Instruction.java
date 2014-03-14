public class Instruction{
    public String inst;
    public boolean hasOption1;
    public boolean hasOption2;
    public int option1;
    public String option2;
    
    public Instruction(String inst){
        this.inst = inst;
        this.hasOption1 = false;
    }
    
    public Instruction(String inst, int option){
        this.inst = inst;
        this.hasOption1 = true;
        this.option1 = option;
    }
    
    public Instruction(String inst, String option){
        this.inst = inst;
        this.hasOption2 = true;
        this.option2 = option;
    }
    
    public String toString(){
        if(this.hasOption1){
            return this.inst +  " " + this.option1;
        }else if(this.hasOption2){
            return this.inst +  " " + this.option2;
        }
        
        return this.inst;
    }
}