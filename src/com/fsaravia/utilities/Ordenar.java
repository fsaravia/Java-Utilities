package com.fsaravia.utilities;

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
}
