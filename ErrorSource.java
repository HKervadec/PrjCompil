/**
 * The source of an error is usefull to know where the error come from.
 * Actually, it is used to separate the compilation error, and the managing
 * system.
 * It could probably be used more seriously, with a lot more of sources.
 * For example, the class who called the error.
 * But i'm a lazy fuck.
 */
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