/**
 * An instruction in the YVM.
 * Each line of a .yvm is an instruction.
 * The options are usefull for the asm translation, otherwise we would just 
 * have used strings.
 */


public class Instruction{
    public String inst;
    public boolean hasOption1;
    public boolean hasOption2;
    public boolean isLabel;
    public int option1;
    public String option2;
    
    public Instruction(String inst){
        this.inst = inst;
        this.hasOption1 = false;
        this.hasOption2 = false;
    }
    
    public Instruction(String inst, boolean label){
        this(inst);
        this.isLabel = label;
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
        if(this.isLabel){
            return this.inst;
        }
    
        if(this.hasOption1){
            return "\t" + this.inst +  " " + this.option1;
        }else if(this.hasOption2){
            return "\t" + this.inst +  " " + this.option2;
        }
        
        return "\t" + this.inst;
    }
}