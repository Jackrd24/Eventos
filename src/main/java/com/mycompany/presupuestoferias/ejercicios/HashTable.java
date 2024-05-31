
package com.mycompany.presupuestoferias.ejercicios;

import java.util.Scanner;
import java.text.DecimalFormat;

public class HashTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el tamaño del arreglo: ");
        int tamanioArreglo = scanner.nextInt();

        System.out.print("Ingrese el número aleatorio (entre 0 y 1): ");
        double numeroAleatorio = scanner.nextDouble();

        String[] tablaClaves = new String[tamanioArreglo];
        int[] tablaIndices = new int[tamanioArreglo];
        int cantidadElementos = 0;

        int opcion = 0;
        while (opcion != 4) {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la clave: ");
                    int clave = scanner.nextInt();
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.next();
                    insertarElemento(tablaClaves, tablaIndices, clave, nombre, cantidadElementos, numeroAleatorio);
                    cantidadElementos++;
                    break;
                case 2:
                    mostrarTablaClaves(tablaClaves, cantidadElementos);
                    break;
                case 3:
                    mostrarTablaIndices(tablaIndices, tablaClaves);
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("");
        System.out.println("---- Menú ----");
        System.out.println("1) Ingresar datos (clave y nombre)");
        System.out.println("2) Ver tabla de claves");
        System.out.println("3) Ver tabla de índices");
        System.out.println("4) Salir");
        System.out.print("Ingrese su opción: ");
    }

    private static void insertarElemento(String[] tablaClaves, int[] tablaIndices, int clave, String nombre, int cantidadElementos, double numeroAleatorio) {
        double multiplicacion = clave * numeroAleatorio;

        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        String parteDecimalFormateada = decimalFormat.format(multiplicacion - (int) multiplicacion);

        double parteDecimal = Double.parseDouble(parteDecimalFormateada);
        int indice = (int) (parteDecimal * tablaClaves.length);

        if (tablaClaves[indice] == null) {
            tablaClaves[indice] = clave + " - " + nombre;
            tablaIndices[indice] = clave;
            System.out.println("Elemento insertado en la posición " + indice);
        } else {
            System.out.println("Colisión detectada en la posición " + indice);
            int indiceColision = resolverColision(tablaClaves, indice);
            if (indiceColision != -1) {
                tablaClaves[indiceColision] = clave + " - " + nombre;
                tablaIndices[indiceColision] = clave;
                System.out.println("Elemento insertado en la posición " + indiceColision);
            } else {
                System.out.println("No se pudo insertar el elemento debido a una colisión.");
            }
        }
    }

    private static int resolverColision(String[] tablaClaves, int indiceInicial) {
        int indiceColision = -1;
        int incremento = 1;
        int indiceActual = indiceInicial;

        while (indiceColision == -1) {
            indiceActual = (indiceInicial + incremento * incremento) % tablaClaves.length;
            if (tablaClaves[indiceActual] == null) {
                indiceColision = indiceActual;
            } else {
                incremento++;
                if (indiceActual == indiceInicial) {
                    break;
                }
            }
        }

        return indiceColision;
    }

    private static void mostrarTablaClaves(String[] tablaClaves, int cantidadElementos) {
        System.out.println("Tabla de Claves:");
        System.out.println("+--------+---------------------------+");
        System.out.println("|  Clave |           Nombre          |");
        System.out.println("+--------+---------------------------+");
        for (int i = 0; i < tablaClaves.length; i++) {
            if (tablaClaves[i] != null) {
                String[] datos = tablaClaves[i].split(" - ");
                String fila = String.format("|%7s |%26s |", datos[0], datos[1]);
                System.out.println(fila);
            }
        }
        System.out.println("+--------+---------------------------+");
    }

    private static void mostrarTablaIndices(int[] tablaIndices, String[] tablaClaves) {
        System.out.println("Tabla de Índices:");
        System.out.println("+----------+--------+---------------------------+");
        System.out.println("| Index    |  Clave |           Nombre          |");
        System.out.println("+----------+--------+---------------------------+");
        for (int i = 0; i < tablaIndices.length; i++) {
            if (tablaIndices[i] != 0) {
                String[] datos = tablaClaves[i].split(" - ");
                String fila = String.format("|%9d |%7s |%26s |", i, datos[0], datos[1]);
                System.out.println(fila);
            }
        }
        System.out.println("+----------+--------+---------------------------+");
    }
}
