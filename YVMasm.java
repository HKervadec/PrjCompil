import java.util.ArrayList;
import java.io.PrintWriter;


/**
 * As YVM, but for asm
 * A string of asm code
 * And a printer.
 * Why do not use inheritance?
 * idk
 */

public class YVMasm{
    private ArrayList<String> code_asm;
    private PrintWriter output;
    
    /**
     * Used for the .DATA
     * Each string is named "mess<id>"
     */
    private int idMess;
    /**
     * Used for the ifs, two avoid label collisions.
     */
    private int idTrue;
    
    public PrintWriter getOutput(){
        return this.output;
    }
    
    
    public YVMasm(){
        this.code_asm = new ArrayList<String>();
        this.idMess = 0;
    }
    
    
    public void setOutput(String name){
        try{
            this.output = new PrintWriter(name + ".asm", "UTF-8");
        }catch(Exception e){}
    }
    
    public String toString(){
        String result = "";
        
        for(String i : this.code_asm){
            result += i + "\n";
        }
        
        return result;
    }
    
    /**
     * Header of the asm program
     */
    private void header(){
        this.code_asm.add("extrn lirent:proc, ecrent:proc");
        this.code_asm.add("extrn ecrbool:proc");
        this.code_asm.add("extrn ecrch:proc, ligsuiv:proc");
        this.code_asm.add("");
        this.code_asm.add(".model SMALL");
        this.code_asm.add(".586");
        this.code_asm.add("");
        this.code_asm.add(".CODE");
        this.code_asm.add("debut:");
        this.code_asm.add("\tSTARTUPCODE");
    }
    
    
    /**
     * Footer of the program
     * 
     */
    public void footer(){
        this.code_asm.add("\tnop");
        this.code_asm.add("\tEXITCODE");
        this.code_asm.add("End debut");
    }
    
    
    /**
     * What have I done?
     * Get an instruction and call the best suited function to generate the 
     * asm code.
     * I will _NOT_ comment each function.
     * @param inst 
     */
    public void translateToAsm(Instruction inst){
        this.code_asm.add("\t; " + inst);
        
        switch(inst.inst){
            case "entete":
                this.header();
                break;
            case "queue":
                this.footer();
                break;
            case "ouvrePrinc":
                this.ouvrePrinc(inst.option1);
                break;
            case "ecrireChaine":
                this.ecrireChaine(inst.option2);
                break;
            case "ecrireEnt":
                this.ecrireEnt();
                break;
            case "lireEnt":
                this.lireEnt(inst.option1);
                break;
            case "aLaLigne":
                this.aLaLigne();
                break;
            case "iload":
                this.iload(inst.option1);
                break;
            case "istore":
                this.istore(inst.option1);
                break;
            case "iconst":
                this.iconst(inst.option1);
                break;
            case "iadd":
                this.iadd();
                break;
            case "isub":
                this.isub();
                break;
            case "imul":
                this.imul();
                break;
            case "idiv":
                this.idiv();
                break;
			case "iinf":
				this.cmp("jge");
				break;
			case "isup":
				this.cmp("jle");
				break;
			case "iinfegal":
				this.cmp("jg");
				break;
			case "isupegal":
				this.cmp("jl");
				break;
			case "iegal":
				this.cmp("jne");
				break;
			case "idiff":
				this.cmp("je");
				break;
			case "ineg":
				this.ineg();
				break;
            default:
                break;
        }  
        this.code_asm.add("");
    }
    
    private void ouvrePrinc(int offset){
        this.code_asm.add("\tmov bp,sp");
        this.code_asm.add("\tsub sp," + offset);
    }
    
    private void ecrireChaine(String s){
        s = addDollar(s);
        
        this.code_asm.add(".DATA");
        this.code_asm.add("\tmess" + this.idMess + " DB " + s);
        this.code_asm.add(".CODE");
        this.code_asm.add("\tlea dx, mess" + this.idMess);
        this.code_asm.add("\tpush dx");
        this.code_asm.add("\tcall ecrch");
        
        this.idMess++;
    }
    
    private void ecrireEnt(){
        this.code_asm.add("\tcall ecrent");
    }
    
    private String addDollar(String s){
        s = s.substring(0, s.length() - 1);
        s += "$\"";
        
        return s;
    }
    
    private void lireEnt(int e){
       this.code_asm.add("\tlea dx,[bp" + e + "]");
       this.code_asm.add("\tpush dx");
       this.code_asm.add("\tcall lirent");
    }
    
    private void aLaLigne(){
        this.code_asm.add("\tcall ligsuiv");
    }
    
    private void iload(int offset){
        this.code_asm.add("\tpush word ptr [bp" + offset + "]");
    }
    
    private void istore(int offset){
        this.code_asm.add("\tpop ax");
        this.code_asm.add("\tmov word ptr [bp" + offset + "], ax");
    }
    
    private void iconst(int value){
        this.code_asm.add("\tpush word ptr " + value);
    }
    
    private void iadd(){
        this.code_asm.add("\tpop bx");
        this.code_asm.add("\tpop ax");
        this.code_asm.add("\tadd ax,bx");
        this.code_asm.add("\tpush ax");
    }
    
    private void isub(){
        this.code_asm.add("\tpop bx");
        this.code_asm.add("\tpop ax");
        this.code_asm.add("\tsub ax, bx");
        this.code_asm.add("\tpush ax");
    }
    
    private void imul(){
        this.code_asm.add("\tpop bx");
        this.code_asm.add("\tpop ax");
        this.code_asm.add("\timul bx");
        this.code_asm.add("\tpush ax");
    }
    
    private void idiv(){
        this.code_asm.add("\tpop bx");
        this.code_asm.add("\tpop ax");
        this.code_asm.add("\tcwd");
        this.code_asm.add("\tidiv bx");
        this.code_asm.add("\tpush ax");
    }
	
	private void cmp(String op){
		this.code_asm.add("\tpop bx");
		this.code_asm.add("\tpop ax");
		this.code_asm.add("\tcmp ax,bx");
		this.code_asm.add("\t"+ op +" FALSE"+this.idTrue);
		this.code_asm.add("\tTRUE"+this.idTrue+":");
		this.code_asm.add("\t\tpush -1");
		this.code_asm.add("\t\tjmp ENDTRUE"+this.idTrue);
		this.code_asm.add("\tFALSE"+this.idTrue+":");
		this.code_asm.add("\t\tpush 0");
		this.code_asm.add("\tENDTRUE"+this.idTrue+":");
		
		this.idTrue++;
	}
	
	private void ineg(){
		this.code_asm.add("\tpop ax");
		this.code_asm.add("\tmov bx,-1");
		this.code_asm.add("\timul bx");
		this.code_asm.add("\tpush ax");
		
	}
}