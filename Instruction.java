public class Instruction{

    public String inst; //the name of the instruction
    public boolean hasOption1; //true if it's an instruction without parameters
    public boolean hasOption2; //same if it's an instructions with parameters
    public int option1;	//the parameter is an int
    public String option2; //the parameter is a string
    
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