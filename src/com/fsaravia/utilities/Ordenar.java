package com.fsaravia.utilities;



import java.util.ArrayList;

public class Ordenar {

    public static void quicksort(int[] a, int izq, int der) {
        int i = izq;
        int j = der;
        int pivote = a[(izq + der) / 2];
        do {
            while (a[i] < pivote) {
                i++;
            }
            while (a[j] > pivote) {
                j--;
            }
            if (i <= j) {
                int aux = a[i];
                a[i] = a[j];
                a[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            quicksort(a, izq, j);
        }
        if (i < der) {
            quicksort(a, i, der);
        }
    }

    public static void quicksort(ArrayList<Integer> a, int izq, int der) {
        int i = izq;
        int j = der;
        int pivote = a.get((izq + der) / 2);
        do {
            while (a.get(i) < pivote) {
                i++;
            }
            while (a.get(j) > pivote) {
                j--;
            }
            if (i <= j) {
                int aux = a.get(i);
                a.set(i, a.get(j));
                a.set(j, aux);
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            quicksort(a, izq, j);
        }
        if (i < der) {
            quicksort(a, i, der);
        }
    }
}
