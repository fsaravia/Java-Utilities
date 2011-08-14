/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

/**
 *
 * @author Federico Saravia
 */
public class UnitConverter {

    public static final double MILIGRAMOS_A_KILOGRAMOS = 0.000001;
    public static final double GRAMOS_A_KILOGRAMOS = 0.001;
    public static final double TONELADAS_A_KILOGRAMOS = 1000.0;
    public static final double KILOGRAMOS_A_MILIGRAMOS = 1000000.0;
    public static final double KILOGRAMOS_A_GRAMOS = 1000.0;
    public static final double KILOGRAMOS_A_TONELADAS = 0.001;
    public static final double MILIMETROS_A_METROS = 0.001;
    public static final double CENTIMETROS_A_METROS = 0.01;
    public static final double PULGADAS_A_METROS = 0.0254;
    public static final double KILOMETROS_A_METROS = 1000;
    public static final double METROS_A_MILIMETROS = 1000;
    public static final double METROS_A_CENTIMETROS = 100;
    public static final double METROS_A_PULGADAS = 39.37008;
    public static final double METROS_A_KILOMETROS = 0.001;
    public static final double MILILITROS_A_LITROS = 0.001;
    public static final double MILIMETROS_CUBICOS_A_LITROS = 0.000001;
    public static final double CENTIMETROS_CUBICOS_A_LITROS = 0.001;
    public static final double METROS_CUBICOS_A_LITROS = 1000;
    public static final double LITROS_A_MILILITROS = 1000;
    public static final double LITROS_A_MILIMETROS_CUBICOS = 1000000;
    public static final double LITROS_A_CENTIMETROS_CUBICOS = 1000;
    public static final double LITROS_A_METROS_CUBICOS = 0.001;

    public static double convertir(int origen, int destino, double valor) {
        return convertir(new UnidadDeMedida(origen), new UnidadDeMedida(destino), valor);
    }
    public static double convertir(UnidadDeMedida origen, UnidadDeMedida destino, double valor) {
        UnitConverter c = new UnitConverter();
        double referente = c.convertirHaciaReferente(valor, origen);
        double valorFinal = c.convertirHaciaDestino(referente, destino);
        return Miscelaneos.redondear(valorFinal, 2);
    }

    private double convertirHaciaReferente(double valor, UnidadDeMedida unidad) {
        int tipoUnidad = unidad.comprobarTipoDeMedida();
        double resultado = 0;
        switch (tipoUnidad) {
            case UnidadDeMedida.UNIDADES_DE_PESO:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.PESO_MILIGRAMOS:
                        resultado = valor * MILIGRAMOS_A_KILOGRAMOS;
                        break;
                    case UnidadDeMedida.PESO_GRAMOS:
                        resultado = valor * GRAMOS_A_KILOGRAMOS;
                        break;
                    case UnidadDeMedida.PESO_KILOGRAMOS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.PESO_TONELADAS:
                        resultado = valor * TONELADAS_A_KILOGRAMOS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_DE_LONGITUD:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.LONGITUD_MILIMETROS:
                        resultado = valor * MILIMETROS_A_METROS;
                        break;
                    case UnidadDeMedida.LONGITUD_CENTIMETROS:
                        resultado = valor * CENTIMETROS_A_METROS;
                        break;
                    case UnidadDeMedida.LONGITUD_PULGADAS:
                        resultado = valor * PULGADAS_A_METROS;
                        break;
                    case UnidadDeMedida.LONGITUD_METROS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.LONGITUD_KILOMETROS:
                        resultado = valor * KILOMETROS_A_METROS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_DE_VOLUMEN:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.VOLUMEN_MILIMETROS_CUBICOS:
                        resultado = valor * MILIMETROS_CUBICOS_A_LITROS;
                        break;
                    case UnidadDeMedida.VOLUMEN_CENTIMETROS_CUBICOS:
                        resultado = valor * CENTIMETROS_CUBICOS_A_LITROS;
                        break;
                    case UnidadDeMedida.VOLUMEN_MILILITROS:
                        resultado = valor * MILILITROS_A_LITROS;
                        break;
                    case UnidadDeMedida.VOLUMEN_LITROS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.VOLUMEN_METROS_CUBICOS:
                        resultado = valor * METROS_CUBICOS_A_LITROS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_CANTIDAD:
                resultado = valor;
                break;
        }
        return resultado;
    }

    private double convertirHaciaDestino(double valor, UnidadDeMedida unidad) {
        int tipoUnidad = unidad.comprobarTipoDeMedida();
        double resultado = 0;
        switch (tipoUnidad) {
            case UnidadDeMedida.UNIDADES_DE_PESO:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.PESO_MILIGRAMOS:
                        resultado = valor * KILOGRAMOS_A_MILIGRAMOS;
                        break;
                    case UnidadDeMedida.PESO_GRAMOS:
                        resultado = valor * KILOGRAMOS_A_GRAMOS;
                        break;
                    case UnidadDeMedida.PESO_KILOGRAMOS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.PESO_TONELADAS:
                        resultado = valor * KILOGRAMOS_A_TONELADAS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_DE_LONGITUD:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.LONGITUD_MILIMETROS:
                        resultado = valor * METROS_A_MILIMETROS;
                        break;
                    case UnidadDeMedida.LONGITUD_CENTIMETROS:
                        resultado = valor * METROS_A_CENTIMETROS;
                        break;
                    case UnidadDeMedida.LONGITUD_PULGADAS:
                        resultado = valor * METROS_A_PULGADAS;
                        break;
                    case UnidadDeMedida.LONGITUD_METROS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.LONGITUD_KILOMETROS:
                        resultado = valor * METROS_A_KILOMETROS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_DE_VOLUMEN:
                switch (unidad.getCodigo()) {
                    case UnidadDeMedida.VOLUMEN_MILIMETROS_CUBICOS:
                        resultado = valor * LITROS_A_MILIMETROS_CUBICOS;
                        break;
                    case UnidadDeMedida.VOLUMEN_CENTIMETROS_CUBICOS:
                        resultado = valor * LITROS_A_CENTIMETROS_CUBICOS;
                        break;
                    case UnidadDeMedida.VOLUMEN_MILILITROS:
                        resultado = valor * LITROS_A_MILILITROS;
                        break;
                    case UnidadDeMedida.VOLUMEN_LITROS:
                        resultado = valor;
                        break;
                    case UnidadDeMedida.VOLUMEN_METROS_CUBICOS:
                        resultado = valor * LITROS_A_METROS_CUBICOS;
                        break;
                }
                break;
            case UnidadDeMedida.UNIDADES_CANTIDAD:
                resultado = valor;
                break;
        }
        return resultado;
    }
}
