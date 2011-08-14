/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsaravia.utilities;

import java.util.Vector;

/**
 *
 * @author Federico Saravia
 */
public final class UnidadDeMedida {

    public static final int UNIDADES_DE_PESO = 1;
    public static final int UNIDADES_DE_LONGITUD = 2;
    public static final int UNIDADES_DE_VOLUMEN = 3;
    public static final int UNIDADES_CANTIDAD = 4;
    public static final int UNIDADES = 5;
    public static final int PESO_MILIGRAMOS = 6;
    public static final int PESO_GRAMOS = 7;
    public static final int PESO_KILOGRAMOS = 8;
    public static final int PESO_TONELADAS = 9;
    public static final int LONGITUD_MILIMETROS = 10;
    public static final int LONGITUD_CENTIMETROS = 11;
    public static final int LONGITUD_METROS = 12;
    public static final int LONGITUD_KILOMETROS = 13;
    public static final int LONGITUD_PULGADAS = 14;
    public static final int VOLUMEN_MILILITROS = 15;
    public static final int VOLUMEN_LITROS = 16;
    public static final int VOLUMEN_MILIMETROS_CUBICOS = 17;
    public static final int VOLUMEN_CENTIMETROS_CUBICOS = 18;
    public static final int VOLUMEN_METROS_CUBICOS = 19;
    private int codigo;
    private String nombre;
    private String abreviatura;

    public static void comprobarCompatibilidad(int existente, int nueva) throws Exception {
        comprobarCompatibilidad(new UnidadDeMedida(existente), new UnidadDeMedida(nueva));
    }

    public static void comprobarCompatibilidad(UnidadDeMedida existente, UnidadDeMedida nueva) throws Exception {
        int tipoExistente = existente.comprobarTipoDeMedida();
        int tipoNuevo = nueva.comprobarTipoDeMedida();
        if (tipoExistente != tipoNuevo) {
            throw new Exception("Unidades de medida incompatibles");
        }
    }

    public static String getTipoDeUnidad(int tipo) {
        switch (tipo) {
            case UNIDADES_CANTIDAD:
                return "cantidad";
            case UNIDADES_DE_LONGITUD:
                return "longitud";
            case UNIDADES_DE_PESO:
                return "peso";
            case UNIDADES_DE_VOLUMEN:
                return "volumen";
            default:
                throw new IllegalArgumentException("Tipo de unidad de medida inválido");
        }
    }

    public static Vector<UnidadDeMedida> getListaUnidades() {
        Vector<UnidadDeMedida> listaUnidades = new Vector<UnidadDeMedida>(16);
        for (int i = UNIDADES; i <= VOLUMEN_METROS_CUBICOS; i++) {
            listaUnidades.add(new UnidadDeMedida(i));
        }
        return listaUnidades;
    }

    public static Vector<UnidadDeMedida> getListaUnidades(UnidadDeMedida relacion) {
        int tipo = relacion.comprobarTipoDeMedida();
        Vector<UnidadDeMedida> listaUnidades = new Vector<UnidadDeMedida>();
        switch (tipo) {
            case UNIDADES_DE_PESO:
                for (int i = PESO_MILIGRAMOS; i <= PESO_TONELADAS; i++) {
                    listaUnidades.add(new UnidadDeMedida(i));
                }
                return listaUnidades;
            case UNIDADES_DE_LONGITUD:
                for (int i = LONGITUD_MILIMETROS; i <= LONGITUD_PULGADAS; i++) {
                    listaUnidades.add(new UnidadDeMedida(i));
                }
                return listaUnidades;
            case UNIDADES_DE_VOLUMEN:
                for (int i = VOLUMEN_MILILITROS; i <= VOLUMEN_METROS_CUBICOS; i++) {
                    listaUnidades.add(new UnidadDeMedida(i));
                }
                return listaUnidades;
            case UNIDADES_CANTIDAD:
                for (int i = UNIDADES; i <= UNIDADES; i++) {
                    listaUnidades.add(new UnidadDeMedida(i));
                }
                return listaUnidades;
            default:
                throw new IllegalArgumentException();
        }

    }

    public static Vector<UnidadDeMedida> getListaUnidades(int relacion) {
        return getListaUnidades(new UnidadDeMedida(relacion));
    }

    public int comprobarTipoDeMedida() {
        if (this.codigo >= PESO_MILIGRAMOS && this.codigo <= PESO_TONELADAS) {
            return UNIDADES_DE_PESO;
        } else if (this.codigo >= LONGITUD_MILIMETROS && this.codigo <= LONGITUD_PULGADAS) {
            return UNIDADES_DE_LONGITUD;
        } else if (this.codigo >= VOLUMEN_MILILITROS && this.codigo <= VOLUMEN_METROS_CUBICOS) {
            return UNIDADES_DE_VOLUMEN;
        } else if (this.codigo == UNIDADES) {
            return UNIDADES_CANTIDAD;
        }
        throw new RuntimeException("Tipo de unidad de medida no válido");
    }

    public UnidadDeMedida(int unidad) {
        this.codigo = unidad;
        switch (unidad) {
            case PESO_MILIGRAMOS:
                nombre = "Miligramos";
                abreviatura = "mg";
                break;
            case PESO_GRAMOS:
                nombre = "Gramos";
                abreviatura = "g";
                break;
            case PESO_KILOGRAMOS:
                nombre = "Kilogramos";
                abreviatura = "kg";
                break;
            case PESO_TONELADAS:
                nombre = "Toneladas";
                abreviatura = "tn";
                break;
            case LONGITUD_MILIMETROS:
                nombre = "Milímetros";
                abreviatura = "mm";
                break;
            case LONGITUD_CENTIMETROS:
                nombre = "Centimetros";
                abreviatura = "cm";
                break;
            case LONGITUD_METROS:
                nombre = "Metros";
                abreviatura = "m";
                break;
            case LONGITUD_KILOMETROS:
                nombre = "Kilometros";
                abreviatura = "km";
                break;
            case LONGITUD_PULGADAS:
                nombre = "Pulgadas";
                abreviatura = "\'\'";
                break;
            case VOLUMEN_MILILITROS:
                nombre = "Mililitros";
                abreviatura = "ml";
                break;
            case VOLUMEN_LITROS:
                nombre = "Litros";
                abreviatura = "lts";
                break;
            case VOLUMEN_MILIMETROS_CUBICOS:
                nombre = "Milímetros Cúbicos";
                abreviatura = "mm3";
                break;
            case VOLUMEN_CENTIMETROS_CUBICOS:
                nombre = "Centímetros Cúbicos";
                abreviatura = "cm3";
                break;
            case VOLUMEN_METROS_CUBICOS:
                nombre = "Metros Cúbicos";
                abreviatura = "m3";
                break;
            case UNIDADES:
                nombre = "Unidades";
                abreviatura = "unidades";
                break;
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadDeMedida other = (UnidadDeMedida) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.abreviatura == null) ? (other.abreviatura != null) : !this.abreviatura.equals(other.abreviatura)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codigo;
        hash = 97 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 97 * hash + (this.abreviatura != null ? this.abreviatura.hashCode() : 0);
        return hash;
    }
}
