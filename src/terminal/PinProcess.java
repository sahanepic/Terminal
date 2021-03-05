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
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author sahan_k
 */
public class PinProcess {

    public static int PINLENTH;
    private PinBlockClass pinBlockClass;
    private static final String ALGORITHM = "TripleDES";
    private static final String MODE = "ECB";
    private static final String PADDING = "NoPadding";

    /**
     * algorithm/mode/padding
     */
    private static final String TRANSFORMATION = ALGORITHM + "/" + MODE + "/" + PADDING;

    public PinBlockClass getPinBlockClass() {
        return pinBlockClass;
    }

    public void setPinBlockClass(PinBlockClass pinBlockClass) {
        this.pinBlockClass = pinBlockClass;
    }

//    public PinProcess() {
//        pinBlockClass = new PinBlockClass("1234",
//                "1234123412341234",
//                "CE93C61D8D78E6FACE93C61D8D78E6FA",
//                "12341234123412341234123412341234",
//                "12341234123412341234123412341234",
//                "12341234123412341234123412341234");
//        PINLENTH = pinBlockClass.getPin().length();
//        //pinBlockClass.setDec_term_work("12341234123412341234123412341234");
//    }
    public PinProcess(PinBlockClass pp) {
        this.pinBlockClass = pp;
        PINLENTH = pinBlockClass.getPin().length();
    }

//    public static void main(String[] args) {
//        PinProcess pinProcess = new PinProcess();
//        System.out.println(pinProcess.getPinBlockClass().toString());
//        pinProcess.executeProcess();
//
//    }
    public void executeProcess() {
        pinBlockCreation();
        terminalMasterKeyCreation();
        decryptTerminalWorkinKey();
        encryptCleanPinBlock();
        decryptEncryptedCleanBlock();
        dispayFinalOutputs();
    }

    public void pinBlockCreation() {
        String pad_card = pinBlockClass.getCard_no().substring(3, pinBlockClass.getCard_no().length() - 1);
        pad_card = "0000" + pad_card;
        pinBlockClass.setPaddin_cord_no(pad_card);

        String fs = "FFFFFFFFFFFFFFFF";
        String pad_pin = "0" + PINLENTH + pinBlockClass.getPin() + fs.substring(2 + PINLENTH, fs.length());
        pinBlockClass.setPadding_pin(pad_pin);

        try {
            String clear_pin_block = toHexString(xorBytes(toByteArray(pinBlockClass.getPadding_pin()), toByteArray(pinBlockClass.getPaddin_cord_no())));
            pinBlockClass.setClear_pin_block(clear_pin_block);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void terminalMasterKeyCreation() {
        try {
            byte[] xortwokey = (xorBytes(toByteArray(pinBlockClass.getTerm_master_c1()), toByteArray(pinBlockClass.getTerm_master_c2())));
            String xorthreekey = toHexString(xorBytes(xortwokey, toByteArray(pinBlockClass.getTerm_master_c2())));
            pinBlockClass.setTerm_master_key(xorthreekey);

        } catch (Exception ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void decryptTerminalWorkinKey() {
        byte[] temp_master = toByteArray(pinBlockClass.getTerm_master_key());
        byte[] ter_master_key = new byte[24];
        System.arraycopy(temp_master, 0, ter_master_key, 0, 16);
        System.arraycopy(temp_master, 0, ter_master_key, 16, 8);

        Cipher mCipher;

        try {
            mCipher = Cipher.getInstance(TRANSFORMATION);
            mCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(ter_master_key, ALGORITHM));
            byte[] ciper_byte = mCipher.doFinal(toByteArray(pinBlockClass.getTerm_work()));
            pinBlockClass.setDec_term_work(toHexString(ciper_byte));
        } catch (Exception e) {
        }
    }

    public void encryptCleanPinBlock() {
        byte[] temp = toByteArray(pinBlockClass.getDec_term_work());
        byte[] ter_key = new byte[24];
        System.arraycopy(temp, 0, ter_key, 0, 16);
        System.arraycopy(temp, 0, ter_key, 16, 8);

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(ter_key, ALGORITHM));
            byte[] ciper_byte = cipher.doFinal(toByteArray(pinBlockClass.getClear_pin_block()));
            pinBlockClass.setEncrypted_pin_block(toHexString(ciper_byte));
        } catch (NoSuchAlgorithmException ex) {
            ex.getMessage();
        } catch (NoSuchPaddingException ex) {
            ex.getMessage();
        } catch (InvalidKeyException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void decryptEncryptedCleanBlock() {
        byte[] temp_clen = toByteArray(pinBlockClass.getDec_term_work());
        byte[] ter_key_clean = new byte[24];
        System.arraycopy(temp_clen, 0, ter_key_clean, 0, 16);
        System.arraycopy(temp_clen, 0, ter_key_clean, 16, 8);

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(ter_key_clean, ALGORITHM));
            byte[] ciper_byte = cipher.doFinal(toByteArray(pinBlockClass.getEncrypted_pin_block()));
//            System.out.println("The Final Clean text :                  " + toHexString(ciper_byte));
            pinBlockClass.setFinal_clean_pin_Block(toHexString(ciper_byte));
        } catch (NoSuchAlgorithmException ex) {
            ex.getMessage();
        } catch (NoSuchPaddingException ex) {
            ex.getMessage();
        } catch (InvalidKeyException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(PinProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dispayFinalOutputs() {
        System.out.println("Padded pin                              " + pinBlockClass.getPadding_pin());
        System.out.println("Padded Card No                          " + pinBlockClass.getPaddin_cord_no());
        System.out.println("The Clear Pin Block                     " + pinBlockClass.getClear_pin_block());
        System.out.println("The Therminal master Key                " + pinBlockClass.getTerm_master_key());
        System.out.println("The  Decrypted Therminal working Key    " + pinBlockClass.getDec_term_work());
        System.out.println("The Encrypted Pin Block                 " + pinBlockClass.getEncrypted_pin_block());
        System.out.println("The Final Clean text :                  " + pinBlockClass.getFinal_clean_pin_Block());
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
