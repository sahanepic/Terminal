/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import static terminal.PinProcess.toByteArray;
import static terminal.PinProcess.toHexString;

/**
 *
 * @author sahan_k
 */
public class EnDeMain {
    
    
    public static void main(String[] args) {
        
        
         byte[] temp = toByteArray("12341234123412341234123412341234");
        byte[] ter_key = new byte[24];
        System.arraycopy(temp, 0, ter_key  , 0, 16);
        System.arraycopy(temp, 0, ter_key  , 16, 8);
        
        Cipher cipher;
        try {
              cipher = Cipher.getInstance("TripleDES/ECB/NoPadding");
              cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(ter_key, "TripleDES") );
              byte[] ciper_byte = cipher.doFinal(toByteArray("1234123412341234"));
               System.out.println( " The Result "+ toHexString(ciper_byte) );
        } catch (NoSuchAlgorithmException ex) {
             ex.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException ex) {
            ex.printStackTrace();
        } catch (BadPaddingException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
