public class ErrorManager{
    private int error_count;
    private int warning_count;
    
    public ErrorManager(){
        /* this.error_count = 0;
        this.warning_count = 0; */
    }
    
    public void printError(ErrorSource source, ErrorType e){
        this.printError(source, e, "");
    }
    
    public void printError(ErrorSource source, ErrorType e, String option){
        this.print("Error", source, e, option);
        
        this.error_count++;
    }
    
    public void printWarning(ErrorSource source, ErrorType e, String option){
        this.print("Warning", source, e, option);
        
        this.warning_count++;
    }
    
    private void print(String t, ErrorSource s, ErrorType e, String o){
        System.err.print(t + " - " + s.getMessage() + "\n");
        System.err.println("* " + e.getMessage());
        
        if(o != ""){
            System.err.println("* " + o);
        }
        
        System.err.println("* " + Yaka.lineManager + "\n");
    }
    
    public void finalPrint(){
        System.err.printf("%d errors, %d warnings\n", this.error_count, this.warning_count);
    }
}