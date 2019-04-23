
package br.com.skep.callBD;
/**
 *
 * @author Scherlandro
 */
public class TratarErros extends Exception{

    
   public TratarErros(String message) {
        super (message);
    }

    public TratarErros(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
