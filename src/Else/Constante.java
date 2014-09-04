package Else;

/**
 * Do I use this? NO
 * Maybe I should.
 */
 
import Core.Yaka;
import Error.ErrorType;
import Error.ErrorSource;

public class Constante {
    private String const_name;
    
    /**
     * Used for the const declaration.
     * Fix the name for the next const whose value is set from the functions
     * set setTmpValue or setTmpFrom.
     * @param id 
     */
    public void setConstName(String name){
        this.const_name = name;
    }
    
    
    /**
     * Put an ident in the table (the usuals tests will be done in the putIdent
     * function.
     * The value is a fixed value, for example 3,-5, VRAI
     * The ident will have the nave contained in the id_tmp var.
     * The type is automagically managed.
     * @param value 
     */
    public void setConstValue(int value){
        this.setConstValue(this.const_name, Type.INTEGER, value);
    }
    
    public void setConstValue(boolean value){        
        if(value){
            this.setConstValue(this.const_name, Type.BOOLEAN, -1);
        }else{
            this.setConstValue(this.const_name, Type.BOOLEAN, 0);
        }
    }
    
    private void setConstValue(String name, Type t, int value){
        Ident id = new Ident(false, name, t, value);
        
        Yaka.tabIdent.putIdent(name, id);
    }
    
    
    /**
     * Put an ident in the table (the usuals tests will be done in the putIdent
     * function.
     * The ident will have the nave contained in the const_name var.
     * @param source : the ident will have the value of the ident source.
     * Raise an error if the source does not exist.
     */
    public void setConstValue(String source){
        if(Yaka.tabIdent.existIdent(source)){
            Ident idSource = Yaka.tabIdent.searchIdent(source);
            Ident id = new Ident(false, 
                                this.const_name, 
                                idSource.getType(), 
                                idSource.getValue());
                                
            Yaka.tabIdent.putIdent(this.const_name, id);
        }else{
            Yaka.errorManager.printError(ErrorSource.COMPILER,
                                            ErrorType.UNDEFINED_IDENT,
                                            source);
        }
    }
}
















