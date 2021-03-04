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
public class TestMain {
    
    public static void main(String[] args) {
        String name = "sahan bcs";
        System.out.println("The Name :"+name) ;
//        System.out.println("The Name Bytes :"+name.getBytes().toString()) ;
          byte[] bytes = new byte[name.length()/2];
          System.out.println("The   Bytes Length :"+bytes.toString()) ;
                  
    }
}
