package Manager;

/**
 * Display and count the errors, the warnings as well.
 */

import Core.Yaka;
import Error.ErrorSource;
import Error.ErrorType;

public class ErrorManager{
    private int error_count;
    private int warning_count;
    
    public ErrorManager(){
        /* this.error_count = 0;
        this.warning_count = 0; */
    }
    
    /**
     * Just call printError with an empty option ( ie "" )
     * @param source 
     * @param e 
     */
    public void printError(ErrorSource source, ErrorType e){
        this.printError(source, e, "");
    }
    
    /**
     * Call the print function, and increment the error count.
     * 
     * @see print
     * @param source 
     * @param e 
     * @param option 
     */
    public void printError(ErrorSource source, ErrorType e, String option){
        this.print("Error", source, e, option);
        
        this.error_count++;
    }
    
    /**
     * Call the print function, and increment the warning count.
     * 
     * @see print
     * @param source 
     * @param e 
     * @param option 
     */
    public void printWarning(ErrorSource source, ErrorType e, String option){
        this.print("Warning", source, e, option);
        
        this.warning_count++;
    }
    
    
    /**
     * Will print the error on System.err (very useful the redirect only the
     * error stream.
     * 
     * @param t The text for an error or a warning
     * @param s The error source. Will use the text in the enum the print it
     * @param e The error Type. Will use the message defined in the enum
     * @param o The option. If "", does not print anything
     */
    private void print(String t, ErrorSource s, ErrorType e, String o){
        System.err.print(t + " - " + s.getMessage() + "\n");
        System.err.println("* " + e.getMessage());
        
        if(o != ""){
            System.err.println("* " + o);
        }
        
        System.err.println("* " + Yaka.lineManager + "\n");
    }
    
    
    /**
     * Just print the error and warning count. 
     * Called at the end of the compilation (or earlier if you have no idea what
     * you're doing.
     */
    public void finalPrint(){
        System.err.printf("%d errors, %d warnings\n", this.error_count, this.warning_count);
    }
}