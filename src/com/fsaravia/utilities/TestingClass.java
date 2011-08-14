/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

import java.io.IOException;

/**
 *
 * @author FSB
 */
public class TestingClass {

    public static void main(String[] args) {
        try {
            long numeroFactura = System.in.read();
            String numero = String.valueOf(numeroFactura);
            for (int i = 8 - numero.length(); i > 0; i--) {
                numero = "0" + numero;
            }
            System.out.println(numero);
        } catch (IOException ex) {
            
        }
    }
}
