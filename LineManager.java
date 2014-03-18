import java.io.FileReader;
import java.io.BufferedReader;

public class LineManager{
    private String fileName;
    private String current_line;
    private int line_number;
    private BufferedReader file;
    
    public String getLine(){
        return this.current_line;
    }

    
    public LineManager(){

    }
    
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
        // System.out.println("LineManager: " + fileName + " successfully opened");
        
        this.fileName = fileName;
        this.nextLine();
    }
    
    
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
    
    public String toString(){
        return this.line_number + " - " + this.current_line;
    }
    
    public void append(int e){
        this.append(String.valueOf(e));
    }
    
    public void append(String s){
        this.current_line += s + " ";
    }
}