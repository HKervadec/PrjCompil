/**
 * List the errors, and associate the message displayed to the user.
 * Everytime you want to create a new error, you have to put it here, and call
 * the error manager with the appropriate ErrorType.
 */
public enum ErrorType{
    FILE_NOT_FOUND ("File not found."),
    END_OF_FILE ("End of file found."),
    EXPRESSION_ERROR ("Error while parsing the following expression:"),
    MISSING_IDENTIFIER ("Identifier does not exist."),
    UNDEFINED_IDENT ("Identifier hasn't been previously defined."),
    RAPING_CONST ("Trying to change the value of a constant."),
    TYPES_MISMATCH ("Types mismatch."),
    ALREADY_DECLARED ("Identifier already declared."),
    UNEXPECTED_TYPE ("Type is not the one expected."),
    NO_TRANSLATION ("No translation exist."),
    PARAM_ERROR ("Not the right amount of argument. Do you want to buy some?"),
    PARAM_MISMATCH ("Param has not the right type."),
	RETURN_MAIN ("Return is not allowed in the main"),
	MUCH_PARAM ("Too much params.");
    
    String message;
    ErrorType(String message){
        this.message = message;
    }
    
    String getMessage(){
        return this.message;
    }
}