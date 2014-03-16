public enum ErrorSource{
    COMPILER ("Compiler"), 
    LINEMANAGER ("LineManager");
    
    String message;
    ErrorSource(String message){
        this.message = message;
    }
    
    String getMessage(){
        return this.message;
    }
}