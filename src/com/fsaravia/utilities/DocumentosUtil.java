/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

/**
 *
 * @author Federico Saravia
 */
public class DocumentosUtil {

    public static String removerGuiones(String numeroDocumento) {
        if(numeroDocumento == null){
            numeroDocumento = "";
        }
        int index = numeroDocumento.indexOf("-");
        while (index > -1) {
            String nuevoNro = numeroDocumento.substring(0, index);
            if (numeroDocumento.length() > index) {
                nuevoNro = nuevoNro + numeroDocumento.substring(index + 1);
            }
            numeroDocumento = nuevoNro;
            index = numeroDocumento.indexOf("-");
        }
        return numeroDocumento;
    }
}
