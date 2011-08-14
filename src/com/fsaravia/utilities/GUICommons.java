/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;

/**
 *
 * @author Federico Saravia
 */
public class GUICommons {

    public static void mostrarOcupado(Component c, boolean ocupado) {
        Cursor cursor = null;
        if (ocupado) {
            cursor = new Cursor(Cursor.WAIT_CURSOR);
        }
        c.setCursor(cursor);
    }

    public static void showDialog(Frame parent, Class<? extends JDialog> cls, Object[] argList) {
        try {
            mostrarOcupado(parent, true);
            Class[] parTypes = new Class[argList.length];
            for (int i = 0; i < argList.length; i++) {
                if (argList[i] == null) {
                    throw new IllegalArgumentException("Se intentÃ³ crear una clase haciendo reflexiÃ³n con componentes nulos");
                }
                parTypes[i] = argList[i].getClass();
            }
            Constructor<? extends JDialog> ct = cls.getConstructor(parTypes);
            JDialog dialogo = ct.newInstance(argList);
            dialogo.setLocationRelativeTo(parent);
            dialogo.setVisible(true);
        } catch (InvocationTargetException ite) {
            GUIMensajes.mostrarErrorReportar(parent, ite.getTargetException());
        } catch (Exception ex) {
            GUIMensajes.mostrarErrorReportar(parent, ex);
        } finally {
            GUICommons.mostrarOcupado(parent, false);
        }
    }
}
