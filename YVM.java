import java.util.ArrayList;
import java.io.PrintWriter;


/**
 * Manage the yvm code.
 * It's a fucking list.
 * And a printer.
 * Read the imports.
 */
public class YVM{
    private ArrayList<Instruction> code_yvm;
    private PrintWriter output;
    public YVMasm asm;
    public static String folder = "yvm\\";
    // public static String folder = "";
    
    public PrintWriter getOutput(){
        return this.output;
    }
    
    
    public YVM(){
        this.code_yvm = new ArrayList<Instruction>();
        this.asm = new YVMasm();
        // this.setOutput("default");
    }
    
    
    /*
     * Add an instruction to himself, and call the translation from YVMasm
     * @param i 
     */    
    public void add(Instruction i){
        this.code_yvm.add(i);
        this.asm.translateToAsm(i);
        // System.out.println(i);
    }
    
    /**
     * List all the instructions in it, from the older to the newest.
     * @return String
     */
    public String toString(){
        String result = "";
        
        for(Instruction i : this.code_yvm){
            result += i + "\n";
        }
        
        return result;
    }
    
    /**
     * Change the output (the .yvm).
     * Does not even manage properly exceptions.
     * @param name 
     */    
    public void setOutput(String name){
        try{
            this.output = new PrintWriter(YVM.folder + name + ".yvm", "UTF-8");
        }catch(Exception e){
            Yaka.errorManager.printError(ErrorSource.YVM,
                                            ErrorType.FILE_NOT_FOUND,
                                            YVM.folder + name + ".asm");
        }
        
        this.asm.setOutput(name);
    }
    
    
    /**
     * Why is this here?
     * Put in himself the "lireEnt offset"
     * The offset correspond to the identifier offset
     * Raise an error if the identifier does not exist.
     * @param name name of the identifier
     */
    public void read(String name){
        Ident id = Yaka.tabIdent.searchIdent(name);
        
        if(id == null){
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.MISSING_IDENTIFIER,
                                            name);
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