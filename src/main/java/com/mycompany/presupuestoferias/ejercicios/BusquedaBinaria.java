
package com.mycompany.presupuestoferias.ejercicios;

import java.util.Arrays;
import java.util.Scanner;

public class BusquedaBinaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una lista de números separados por espacios: ");
        String listaNumeros = scanner.nextLine();

        // Convertir la cadena de números en un arreglo de enteros
        String[] numerosStr = listaNumeros.split(" ");
        int[] numeros = new int[numerosStr.length];
        for (int i = 0; i < numerosStr.length; i++) {
            numeros[i] = Integer.parseInt(numerosStr[i]);
        }

        // Ordenar el arreglo de números de forma ascendente
        Arrays.sort(numeros);
        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));

        System.out.print("Ingrese el número a buscar: ");
        int numeroBuscar = scanner.nextInt();
        System.out.println("");

        // Realizar la búsqueda binaria
        int indice = busquedaBinaria(numeros, numeroBuscar);
        if (indice != -1) {
            System.out.println("");
            System.out.println("El número " + numeroBuscar + " se encuentra en la posición " + (indice+1) + ".");
        } else {
            System.out.println("");
            System.out.println("El número " + numeroBuscar + " no se encuentra en la lista.");
        }
    }

    public static int busquedaBinaria(int[] numeros, int numeroBuscar) {
        int inicio = 0;
        int fin = numeros.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            // Mostrar el estado actual de la búsqueda
            System.out.println("Rango de búsqueda (# Posiciones): [" + (inicio+1) + " - " + (fin+1) + "]");
            System.out.println("Posición media: " + (medio+1) + ", Valor medio: " + numeros[medio]);

            if (numeros[medio] == numeroBuscar) {
                return medio; // El número se encontró en el índice medio
            }

            if (numeros[medio] < numeroBuscar) {
                inicio = medio + 1; // Descartar la mitad izquierda
                System.out.println(numeros[medio] + " es menor que " + numeroBuscar);
                System.out.println("");
            } else {
                fin = medio - 1; // Descartar la mitad derecha
                System.out.println(numeros[medio] + " es mayor que " + numeroBuscar);
                System.out.println("");
            }
        }

        return -1; // El número no se encontró en la lista
    }
}
