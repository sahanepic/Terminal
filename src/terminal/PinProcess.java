/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author sahan_k
 */
public class PinProcess {

    public static int PINLENTH;
    private PinBlockClass pinBlockClass;

    public PinBlockClass getPinBlockClass() {
        return pinBlockClass;
    }

    public void setPinBlockClass(PinBlockClass pinBlockClass) {
        this.pinBlockClass = pinBlockClass;
    }

    public PinProcess() {
        pinBlockClass = new PinBlockClass("123456",
                "1234123412341234",
                "12341234123412341234123412341234",
                "12341234123412341234123412341234",
                "12341234123412341234123412341234",
                "12341234123412341234123412341234");
        PINLENTH = pinBlockClass.getPin().length();

    }

    public static void main(String[] args) {
        PinProcess pinProcess = new PinProcess();
        System.out.println(pinProcess.getPinBlockClass().toString());
        pinProcess.createClearPinBlock();

        byte[] bb=null;
        try {
             bb = xorBytes(h2b("12345678"), h2b("00000000"));
        } catch (Exception ex) {
             ex.getMessage();
        }
        
        
        System.out.println("The Byte Knolwdge");
        System.out.println(" To Byte Array");
        System.out.println(toByteArray("12345678"));
        System.out.println(toByteArray("12345678"));
        
        System.out.println(" To h2b");
        System.out.println(h2b("12345678")==h2b("12345678"));
        System.out.println(h2b("12345678"));
        System.out.println(" The xor After " + bb);
        
        System.out.println( "Get Bytes");
        String num = "12345678";
        byte[] bbb,ccc ;
         System.out.println( bbb=toByteArray(num) );
        System.out.println(ccc=toByteArray(num));
        
        System.out.println( ccc.equals(bbb));
        
         System.out.println(toHexString(bbb ) );
        System.out.println(toHexString(ccc));
        System.out.println(toHexString(bb));
        
    }

    public void createClearPinBlock() {

        String pad_card = pinBlockClass.getCard_no().substring(3, pinBlockClass.getCard_no().length() - 1);
        pad_card = "0000" + pad_card;
        pinBlockClass.setPaddin_cord_no(pad_card);
        String fs = "FFFFFFFFFFFFFFFF";
        String pad_pin = "0" + PINLENTH + pinBlockClass.getPin() + fs.substring(2 + PINLENTH, fs.length());
        pinBlockClass.setPadding_pin(pad_pin);

        
        
        
        
        System.out.println("Padded pin     " + pinBlockClass.getPadding_pin());
         
        System.out.println("Padded pin     " + pinBlockClass.getPadding_pin());
        System.out.println("Padded Card No " + pinBlockClass.getPaddin_cord_no());

    }

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    public static byte[] h2b(String hex) {
        if ((hex.length() & 0x01) == 0x01) {
            throw new IllegalArgumentException();
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int idx = 0; idx < bytes.length; ++idx) {
            int hi = Character.digit((int) hex.charAt(idx * 2), 16);
            int lo = Character.digit((int) hex.charAt(idx * 2 + 1), 16);
            if ((hi < 0) || (lo < 0)) {
                System.out.println("sahan ");
                throw new IllegalArgumentException();
            }

            bytes[idx] = (byte) ((hi << 4) | lo);
        }
        return bytes;
    }
    
    
    public static String b2h(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];
        for (int idx = 0; idx < bytes.length; ++idx) {
            int hi = (bytes[idx] & 0xF0) >>> 4;
            int lo = (bytes[idx] & 0x0F);
            hex[idx * 2] = (char) (hi < 10 ? '0' + hi : 'A' - 10 + hi);
            hex[idx * 2 + 1] = (char) (lo < 10 ? '0' + lo : 'A' - 10 + lo);
        }
        return new String(hex);
    }
    
     public static byte[] xorBytes(byte[] a, byte[] b) throws Exception {
        if (a.length != b.length) {
            throw new Exception();
        }
        byte[] result = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            int r = 0;
            r = a[i] ^ b[i];
            r &= 0xFF;
            result[i] = (byte) r;
        }
        return result;
    }

}
