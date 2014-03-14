import java.util.ArrayList;
import java.io.PrintWriter;

public class YVM{
    private ArrayList<Instruction> code_yvm;
    private PrintWriter output;
    
    public PrintWriter getOutput(){
        return this.output;
    }
    
    
    public YVM(){
        this.code_yvm = new ArrayList<Instruction>();
        this.setOutput("default.yvm");
    }
    
    public void add(Instruction i){
        this.code_yvm.add(i);
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
            this.output = new PrintWriter(name, "UTF-8");
        }catch(Exception e){}
    }
}