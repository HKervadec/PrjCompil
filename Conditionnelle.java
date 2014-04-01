public class Conditionnelle{
    private int label_number;
    
    public Conditionnelle(){}
    
    public void putHead(){
        Yaka.yvm.add(new Instruction("iffaux", "SINON" + this.label_number));
    }
    
    public void putElse(){
        Yaka.yvm.add(new Instruction("goto", "FSI" + this.label_number));
        Yaka.yvm.add(new Instruction("SINON" + label_number + ":", true));
    }
    
    public void putEndIf(){
        Yaka.yvm.add(new Instruction("FSI" + label_number + ":", true));
        
        this.label_number++;
    }
}