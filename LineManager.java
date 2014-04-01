import java.io.FileReader;
import java.io.BufferedReader;


/**
 * Small class to keep track of the content of the line currently parsed.
 * Got a pretty print (toString), and a function to just retrieve the current
 * line.
 * Very usefull for debugging purposes.
 */
public class LineManager{
    private String fileName;
    private String current_line;
    private int line_number;
    private BufferedReader file;
    
    
    /**
     * Return the current line without any modification
     * @see toString
     * @return String
     */
    public String getLine(){
        return this.current_line;
    }

    
    public LineManager(){

    }
    
    /**
     * Open the file in parameter, and raise an error if the file is not found.
     * If sucess, read the first line.
     * @param fileName 
     */
    public void setFile(String fileName){
        try{
            this.file = new BufferedReader(new FileReader(fileName));
        }catch(Exception e){
            // System.err.println("LineManager: Error, file not found: " + fileName);
            // System.err.println(e.getMessage());
            Yaka.errorManager.printError(ErrorSource.LINEMANAGER, 
                                            ErrorType.FILE_NOT_FOUND, 
                                            fileName);
        }
        /* Maybe for a verbose option */
        // System.out.println("LineManager: " + fileName + " successfully opened");
        
        this.fileName = fileName;
        this.nextLine();
    }
    
    
    /**
     * Read the next line of the file, and increment the line count.
     * Raise an error if the end of file is found.
     */
    public void nextLine(){
        try{
            this.current_line = this.file.readLine();
        }catch(Exception e){
            // System.err.println("LineManager: Error, end of file found.");
            Yaka.errorManager.printError(ErrorSource.LINEMANAGER, 
                                            ErrorType.END_OF_FILE, 
                                            this.fileName);
        }
        
        if(this.current_line == null){
            // System.err.println("LineManager: Error, end of file found.");
            Yaka.errorManager.printError(ErrorSource.LINEMANAGER, 
                                            ErrorType.END_OF_FILE, 
                                            this.fileName);
        }
        
        this.line_number++;
    }
    
    /**
     * Return the lineNumber and the line contents, separated by a '-'
     * @return String
     */
    public String toString(){
        return this.line_number + " - " + this.current_line;
    }
    
    
    /* What is this? Probably some dinosaurs bones. */
    /* public void append(int e){
        this.append(String.valueOf(e));
    }
    
    public void append(String s){
        this.current_line += s + " ";
    } */
}