public class Iteration{
    private int label_number;
    
    public Iteration(){}
    
    public void putHead(){
        Yaka.yvm.add(new Instruction("FAIRE" + label_number + ":", true));
    }
    
    public void putJmp(){
        Yaka.yvm.add(new Instruction("iffaux", "FAIT" + this.label_number));
    }
    
    public void goto_end(){
        Yaka.yvm.add(new Instruction("goto", "FAIRE" + this.label_number));
        Yaka.yvm.add(new Instruction("FAIT" + label_number + ":", true));
        
        this.label_number++;
    }
}