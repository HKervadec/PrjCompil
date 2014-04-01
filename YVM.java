import java.util.ArrayList;
import java.io.PrintWriter;

public class YVM{
    private ArrayList<Instruction> code_yvm; //List of instructions
    private PrintWriter output; //Allow us to print the file with the instructions
    public YVMasm asm; //For the translation from YVM to asm
    
	//getter
    public PrintWriter getOutput(){
        return this.output;
    }
    
    //Constructor
    public YVM(){
        this.code_yvm = new ArrayList<Instruction>();
        this.asm = new YVMasm();
        this.setOutput("default");
    }
    
	//add an instruction in the list and translate it in the list of ASM instructions
    public void add(Instruction i){
        this.code_yvm.add(i);
        this.asm.translateToAsm(i);
    }
    
	//toString for the printwriter
    public String toString(){
        String result = "";
        
        for(Instruction i : this.code_yvm){
            result += i + "\n";
        }
        
        return result;
    }
    
	//set the name of file
    public void setOutput(String name){
        try{
            this.output = new PrintWriter(name + ".yvm", "UTF-8");
        }catch(Exception e){}
        
        this.asm.setOutput(name);
    }
    
    //search the name of the id and add the function lireEnt in the YVM code
    public void read(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            System.err.println("Error: Identifier doesn't exist");
            return;
        }
        
        if(!id.var){
            System.err.println("Error: Trying to change the value of a constant");
            return;
        }
        
        this.add(new Instruction("lireEnt", id.getValue()));
    }
    
}