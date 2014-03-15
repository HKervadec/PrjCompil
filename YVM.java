import java.util.ArrayList;
import java.io.PrintWriter;

public class YVM{
    private ArrayList<Instruction> code_yvm;
    private PrintWriter output;
    public YVMasm asm;
    
    public PrintWriter getOutput(){
        return this.output;
    }
    
    
    public YVM(){
        this.code_yvm = new ArrayList<Instruction>();
        this.asm = new YVMasm();
        this.setOutput("default");
    }
    
    public void add(Instruction i){
        this.code_yvm.add(i);
        this.asm.translateToAsm(i);
        // System.out.println(i);
    }
    
    public String toString(){
        String result = "";
        
        for(Instruction i : this.code_yvm){
            result += i + "\n";
        }
        
        return result;
    }
    
    public void setOutput(String name){
        try{
            this.output = new PrintWriter(name + ".yvm", "UTF-8");
        }catch(Exception e){}
        
        this.asm.setOutput(name);
    }
    
    
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
    
    // public void translate(){
        // for(Instruction i : this.code_yvm){
            // this.asm.translateToAsm(i);
        // }
    // }
}