/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

import com.ib.HBCore.exceptions.ValidationException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Federico Saravia
 */
public final class Miscelaneos {

    public static String addZeroesBefore(long number, int desiredLength) {
        String stringNumber = String.valueOf(number);
        for (int i = desiredLength - stringNumber.length(); i > 0; i--) {
            stringNumber = "0" + stringNumber;
        }
        return stringNumber;
    }

    public static Calendar setCalendario(Object horario) throws Exception {
        Calendar cal = new GregorianCalendar();
        int dosPuntos;
        int horas;
        int minutos;
        if (cal == null) {
            throw new NullPointerException("Ingrese la fecha correctamente (dd/mm/aaaa");
        }
        try {
            String hora = String.valueOf(horario);
            dosPuntos = hora.indexOf(":");
            horas = Integer.parseInt(hora.substring(0, dosPuntos));
            minutos = Integer.parseInt(hora.substring(dosPuntos + 1));
            if (horas >= 0 && horas < 24 && minutos >= 0 && minutos < 60) {
                cal.set(GregorianCalendar.HOUR_OF_DAY, horas);
                cal.set(GregorianCalendar.MINUTE, minutos);
                cal.set(GregorianCalendar.SECOND, 0);
                cal.set(GregorianCalendar.MILLISECOND, 0);
                cal.set(GregorianCalendar.DST_OFFSET, 0);
            } else {
                throw new Exception("Ingrese una hora valida en el formato de 24 hs");
            }
        } catch (NumberFormatException nfe) {
            throw new Exception("Ingrese la hora correctamente (hh:mm)");
        } catch (NullPointerException npe) {
            throw new Exception("Ingrese la hora correctamente (hh:mm)");
        } catch (StringIndexOutOfBoundsException sbe) {
            throw new Exception("Ingrese la hora correctamente (hh:mm)");
        }
        return cal;

    }

    public static void setCalendario(Calendar cal, Object horario) throws Exception {
        int dosPuntos;
        int horas;
        int minutos;
        if (cal == null) {
            throw new NullPointerException("Ingrese la fecha correctamente (dd/mm/aaaa");
        }
        try {
            String hora = String.valueOf(horario);
            dosPuntos = hora.indexOf(":");
            horas = Integer.parseInt(hora.substring(0, dosPuntos));
            minutos = Integer.parseInt(hora.substring(dosPuntos + 1));
            if (horas >= 0 && horas < 24 && minutos >= 0 && minutos < 60) {
                cal.set(GregorianCalendar.HOUR_OF_DAY, horas);
                cal.set(GregorianCalendar.MINUTE, minutos);
                cal.set(GregorianCalendar.SECOND, 0);
                cal.set(GregorianCalendar.MILLISECOND, 0);
                cal.set(GregorianCalendar.DST_OFFSET, 0);
            } else {
                throw new Exception("Ingrese una hora valida en el formato de 24 hs");
            }
        } catch (NumberFormatException nfe) {
            throw new ValidationException("Ingrese la hora correctamente (hh:mm)");
        } catch (NullPointerException npe) {
            throw new ValidationException("Ingrese la hora correctamente (hh:mm)");
        } catch (StringIndexOutOfBoundsException sbe) {
            throw new ValidationException("Ingrese la hora correctamente (hh:mm)");
        }
    }

    public static Object calendarToFormattedTextField(Calendar cal) {
        StringBuffer horas = new StringBuffer(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
        if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
            horas.insert(0, "0");
        }
        if (cal.get(Calendar.MINUTE) < 10) {
            horas.insert(horas.length() - 1, "0");
        }
        return horas;
    }

    public static String compararFechas(Calendar before, Calendar after, boolean redondear) {
        if (before.before(after) || before.equals(after)) {
            long diferencia = after.getTimeInMillis() - before.getTimeInMillis();
            long segundos = diferencia / 1000;
            double minutos = segundos / 60;
            double hours = minutos / 60;
            long dias = (long) (hours / 24);
            hours -= (dias * 24);
            long horas = (long) hours;
            if (redondear && horas > 0) {
                dias++;
                horas = 0;
            }
            //long dias = segundos / (3600 * 24);

            //int horas = after.get(Calendar.HOUR_OF_DAY) - before.get(Calendar.HOUR_OF_DAY);
            if (dias == 1) {
                if (horas == 1) {
                    return dias + " dia, " + horas + " hora";
                } else {
                    return dias + " dia, " + horas + " horas";
                }
            } else {
                if (horas == 1) {
                    return dias + " dias, " + horas + " hora";
                } else {
                    return dias + " dias, " + horas + " horas";
                }
            }
        } else {
            return "Fecha de inicio es posterior a la fecha de fin";
        }
    }

    /**
     *
     * @param before
     * @param after
     * @return dias,horas, DESCOMPONER EL VALOR!!
     */
    public static double compararFechasNumerico(Calendar before, Calendar after, boolean redondear) {
        before.set(Calendar.DST_OFFSET, 0);
        after.set(Calendar.DST_OFFSET, 0);
        before.set(Calendar.MILLISECOND, 0);
        after.set(Calendar.MILLISECOND, 0);
        if (before.before(after) || before.equals(after)) {
            long diferencia = after.getTimeInMillis() - before.getTimeInMillis();
            long segundos = diferencia / 1000;
            double minutos = segundos / 60;
            double hours = minutos / 60;
            long dias = (long) (hours / 24);
            hours -= (dias * 24);
            long horas = (long) hours;
            if (horas > 0 && redondear) {
                horas = 0;
                dias++;
            }
            String valor = dias + "." + horas;
            return Double.parseDouble(valor);
        } else {
            return 0;
        }
    }

    public static long calcularDiferencia(Calendar estimada, Calendar real) {
        estimada.set(Calendar.DST_OFFSET, 0);
        real.set(Calendar.DST_OFFSET, 0);
        if (estimada.before(real) || estimada.equals(real)) {
            long diferencia = real.getTimeInMillis() - estimada.getTimeInMillis();
            long segundos = diferencia / 1000;
            double minutos = segundos / 60;
            double hours = minutos / 60;
//            long dias = (long) (hours / 24);
//            hours -= (dias * 24);
//            long horas = (long) hours;
//            if (redondear && horas > 0){
//                horas = 0;
//                dias++;
//            }
            return (long) hours;
        } else {
            return 0;
        }
    }

    public static String getMes(int numero) {
        switch (numero) {
            case Calendar.JANUARY:
                return "Enero";
            case Calendar.FEBRUARY:
                return "Febrero";
            case Calendar.MARCH:
                return "Marzo";
            case Calendar.APRIL:
                return "Abril";
            case Calendar.MAY:
                return "Mayo";
            case Calendar.JUNE:
                return "Junio";
            case Calendar.JULY:
                return "Julio";
            case Calendar.AUGUST:
                return "Agosto";
            case Calendar.SEPTEMBER:
                return "Septiembre";
            case Calendar.OCTOBER:
                return "Octubre";
            case Calendar.NOVEMBER:
                return "Noviembre";
            case Calendar.DECEMBER:
                return "Diciembre";
            default:
                throw new RuntimeException("Mes no válido");
        }
    }

    public static int getMes(String nombre) {
        if (nombre.equalsIgnoreCase("Enero")) {
            return Calendar.JANUARY;
        }
        if (nombre.equalsIgnoreCase("Febrero")) {
            return Calendar.FEBRUARY;
        }
        if (nombre.equalsIgnoreCase("Marzo")) {
            return Calendar.MARCH;
        }
        if (nombre.equalsIgnoreCase("Abril")) {
            return Calendar.APRIL;
        }
        if (nombre.equalsIgnoreCase("Mayo")) {
            return Calendar.MAY;
        }
        if (nombre.equalsIgnoreCase("Junio")) {
            return Calendar.JUNE;
        }
        if (nombre.equalsIgnoreCase("Julio")) {
            return Calendar.JULY;
        }
        if (nombre.equalsIgnoreCase("Agosto")) {
            return Calendar.AUGUST;
        }
        if (nombre.equalsIgnoreCase("Septiembre")) {
            return Calendar.SEPTEMBER;
        }
        if (nombre.equalsIgnoreCase("Octubre")) {
            return Calendar.OCTOBER;
        }
        if (nombre.equalsIgnoreCase("Noviembre")) {
            return Calendar.NOVEMBER;
        }
        if (nombre.equalsIgnoreCase("Diciembre")) {
            return Calendar.DECEMBER;
        }
        throw new RuntimeException("Mes no válido");
    }
}
