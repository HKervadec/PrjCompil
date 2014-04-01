import java.util.Stack;

public class Iteration{
    private int label_number;
    private Stack stack_label;
    
    public Iteration(){
        this.stack_label = new Stack();
    }
    
    public void putHead(){
        Yaka.yvm.add(new Instruction("FAIRE" + label_number + ":", true));
        this.stack_label.push(this.label_number);
        this.label_number++;
    }
    
    public void putJmp(){
        int label = (int) this.stack_label.peek();
        Yaka.yvm.add(new Instruction("iffaux", "FAIT" + label));
    }
    
    public void goto_end(){
        int label = (int) this.stack_label.pop();
        
        Yaka.yvm.add(new Instruction("goto", "FAIRE" + label));
        Yaka.yvm.add(new Instruction("FAIT" + label + ":", true));
    }
}