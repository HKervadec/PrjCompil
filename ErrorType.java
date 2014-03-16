public enum ErrorType{
    FILE_NOT_FOUND ("File not found."),
    EXPRESSION_ERROR ("Error while parsing the following expression:"),
    MISSING_IDENTIFIER ("Identifier doesn't exist."),
    UNDEFINED_IDENT ("Identifier hasn't been previously defined"),
    RAPING_CONST ("Trying to change the value of a constant"),
    TYPES_MISMATCH ("Types mismatch"),
    ALREADY_DECLARED ("Identifier already declared");
    
    String message;
    ErrorType(String message){
        this.message = message;
    }
    
    String getMessage(){
        return this.message;
    }
}