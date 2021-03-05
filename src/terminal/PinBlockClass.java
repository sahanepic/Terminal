/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

/**
 *
 * @author sahan_k
 */
public class PinBlockClass {

    private String pin;
    private String card_no;
    private String term_work;
    private String term_master_key;
    private String term_master_c1;
    private String term_master_c2;
    private String term_master_c3;
    private String clear_pin_block;
    private String encrypted_pin_block;
    private String padding_pin;
    private String paddin_cord_no;
    private String dec_term_work;
    private String final_clean_pin_Block;

    public PinBlockClass() {
    }

    public PinBlockClass(String pin, String card_no, String term_work, String term_master_c1, String term_master_c2, String term_master_c3) {
        this.pin = pin;
        this.card_no = card_no;
        this.term_work = term_work;
        this.term_master_c1 = term_master_c1;
        this.term_master_c2 = term_master_c2;
        this.term_master_c3 = term_master_c3;
    }

    public String getEncrypted_pin_block() {
        return encrypted_pin_block;
    }

    public void setEncrypted_pin_block(String encrypted_pin_block) {
        this.encrypted_pin_block = encrypted_pin_block;
    }

    public String getClear_pin_block() {
        return clear_pin_block;
    }

    public void setClear_pin_block(String clear_pin_block) {
        this.clear_pin_block = clear_pin_block;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getTerm_work() {
        return term_work;
    }

    public void setTerm_work(String term_work) {
        this.term_work = term_work;
    }

    public String getTerm_master_c1() {
        return term_master_c1;
    }

    public void setTerm_master_c1(String term_master_c1) {
        this.term_master_c1 = term_master_c1;
    }

    public String getTerm_master_c2() {
        return term_master_c2;
    }

    public void setTerm_master_c2(String term_master_c2) {
        this.term_master_c2 = term_master_c2;
    }

    public String getTerm_master_c3() {
        return term_master_c3;
    }

    public void setTerm_master_c3(String term_master_c3) {
        this.term_master_c3 = term_master_c3;
    }

    public String getFinal_clean_pin_Block() {
        return final_clean_pin_Block;
    }

    public void setFinal_clean_pin_Block(String final_clean_pin_Block) {
        this.final_clean_pin_Block = final_clean_pin_Block;
    }

    public String getTerm_master_key() {
        return term_master_key;
    }

    public void setTerm_master_key(String term_master_key) {
        this.term_master_key = term_master_key;
    }

    public String getDec_term_work() {
        return dec_term_work;
    }

    public void setDec_term_work(String dec_term_work) {
        this.dec_term_work = dec_term_work;
    }

    public String getPadding_pin() {
        return padding_pin;
    }

    public void setPadding_pin(String padding_pin) {
        this.padding_pin = padding_pin;
    }

    public String getPaddin_cord_no() {
        return paddin_cord_no;
    }

    public void setPaddin_cord_no(String paddin_cord_no) {
        this.paddin_cord_no = paddin_cord_no;
    }

    @Override
    public String toString() {
        return "PinBlockClass{" + "pin=" + pin + ", card_no=" + card_no + ", term_work=" + term_work + ", term_master_c1=" + term_master_c1 + ", term_master_c2=" + term_master_c2 + ", term_master_c3=" + term_master_c3 + '}';
    }

}
