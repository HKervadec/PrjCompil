package Else;

import java.util.Stack;

import Core.Yaka;

public class Conditionnelle{
    private int label_number;
    private Stack stack_label;
    
    public Conditionnelle(){
        this.stack_label = new Stack();
    }
    
    public void putHead(){
        Yaka.yvm.add(new Instruction("iffaux", "SINON" + this.label_number));
        this.stack_label.push(this.label_number);
        this.label_number++;
    }
    
    public void putElse(){
        int label = (int) this.stack_label.peek();
        Yaka.yvm.add(new Instruction("goto", "FSI" + label));
        Yaka.yvm.add(new Instruction("SINON" + label + ":", true));
    }
    
    public void putEndIf(){
        int label = (int) this.stack_label.pop();

        Yaka.yvm.add(new Instruction("FSI" + label + ":", true));
    }
}