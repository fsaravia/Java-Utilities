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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

    //TODO: Integrate this two methods below into one
    public static void showDialog(Frame parent, Class<? extends JDialog> cls, Object[] argList) {
        try {
            mostrarOcupado(parent, true);
            Class[] parTypes;
            if (argList == null || argList.length == 0 || argList[0] == null) {
                parTypes = new Class[0];
            } else {
                parTypes = new Class[argList.length];
                for (int i = 0; i < argList.length; i++) {
                    if (argList[i] == null) {
                        throw new IllegalArgumentException("Se intentó crear una clase haciendo reflexión con componentes nulos");
                    }
                    if (argList[i].getClass().getSuperclass() != null && (argList[i].getClass().getSuperclass().equals(javax.swing.JFrame.class))) {
                        parTypes[i] = argList[i].getClass().getSuperclass();
                    } else {
                        parTypes[i] = argList[i].getClass();
                    }
                }
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

    public static void changeMainPanel(JScrollPane scroller, Class<? extends JPanel> cls, Object[] argList) {
        try {
            mostrarOcupado(scroller.getTopLevelAncestor(), true);
            scroller.getViewport().removeAll();
            Class[] parTypes;
            if (argList == null || argList.length == 0 || argList[0] == null) {
                parTypes = new Class[0];
            } else {
                parTypes = new Class[argList.length];
                for (int i = 0; i < argList.length; i++) {
                    if (argList[i] == null) {
                        throw new IllegalArgumentException("Se intentó crear una clase haciendo reflexión con componentes nulos");
                    }
                    if (argList[i].getClass().getSuperclass() != null && (argList[i].getClass().getSuperclass().equals(javax.swing.JFrame.class))) {
                        parTypes[i] = argList[i].getClass().getSuperclass();
                    } else {
                        parTypes[i] = argList[i].getClass();
                    }
                }
            }
            Constructor<? extends JPanel> ct = cls.getConstructor(parTypes);
            JPanel panel = ct.newInstance(argList);
            scroller.getViewport().add(panel);
            scroller.getTopLevelAncestor().validate();
        } catch (InvocationTargetException inve) {
            GUIMensajes.mostrarErrorReportar(scroller.getTopLevelAncestor(), inve.getTargetException());
        } catch (Exception ex) {
            GUIMensajes.mostrarErrorReportar(scroller.getTopLevelAncestor(), ex);
        } finally {
            GUICommons.mostrarOcupado(scroller.getTopLevelAncestor(), false);
        }
    }

    public static void resetPanel(JScrollPane pane) {
        try {
            GUICommons.mostrarOcupado(pane.getTopLevelAncestor(), true);
            if (pane != null) {
                pane.getViewport().removeAll();
                pane.getViewport().add(new JPanel());
                pane.getTopLevelAncestor().validate();
            }
        } catch (Exception e) {
            Logger.getLogger(GUICommons.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mostrarOcupado(pane.getTopLevelAncestor(), false);
        }
    }
//    public static Constructor getPossibleConstructor(Class<? extends JDialog> motherClass, Class[] pars) {
//
//        Constructor[] ctors = motherClass.getConstructors();
//
//        for (Constructor c : ctors) {
//            Class[] cpars = c.getParameterTypes();
//
//            if (cpars.length != pars.length) {
//                continue;
//            }
//
//            boolean found = true;
//
//            for (int i = 0; i < cpars.length; ++i) {
//
//                if (!cpars[i].isAssignableFrom(pars[i])) {
//                    found = false;
//                    break;
//                }
//            }
//            if (found) {
//                return c;
//            }
//        }
//        return null;
//    }
}
