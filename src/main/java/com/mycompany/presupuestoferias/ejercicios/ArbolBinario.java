package com.mycompany.presupuestoferias.ejercicios;

import java.util.Scanner;

class NodoArbol {
    int dato;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolBinario {
    NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(int dato) {
        raiz = insertarNodo(raiz, dato);
    }

    private NodoArbol insertarNodo(NodoArbol nodo, int dato) {
        if (nodo == null) {
            nodo = new NodoArbol(dato);
            return nodo;
        }

        if (dato < nodo.dato) {
            nodo.izquierdo = insertarNodo(nodo.izquierdo, dato);
        } else if (dato > nodo.dato) {
            nodo.derecho = insertarNodo(nodo.derecho, dato);
        }

        return nodo;
    }

    public void mostrarArbol() {
        mostrarArbol(raiz, 0);
    }

    private void mostrarArbol(NodoArbol nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        mostrarArbol(nodo.derecho, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("   ");
        }

        System.out.println("|__" + nodo.dato);

        mostrarArbol(nodo.izquierdo, nivel + 1);
    }

    public boolean buscar(int dato) {
        return buscarNodo(raiz, dato);
    }

    private boolean buscarNodo(NodoArbol nodo, int dato) {
        if (nodo == null) {
            return false;
        }

        if (dato == nodo.dato) {
            return true;
        } else if (dato < nodo.dato) {
            return buscarNodo(nodo.izquierdo, dato);
        } else {
            return buscarNodo(nodo.derecho, dato);
        }
    }

    public void eliminar(int dato) {
        raiz = eliminarNodo(raiz, dato, false);
    }

    private NodoArbol eliminarNodo(NodoArbol nodo, int dato, boolean eliminado) {
        if (nodo == null) {
            if (!eliminado) {
                System.out.println("El número " + dato + " no existe en el árbol.");
            }
            return null;
        }

        if (dato < nodo.dato) {
            nodo.izquierdo = eliminarNodo(nodo.izquierdo, dato, eliminado);
        } else if (dato > nodo.dato) {
            nodo.derecho = eliminarNodo(nodo.derecho, dato, eliminado);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            nodo.dato = encontrarMinimo(nodo.derecho);
            nodo.derecho = eliminarNodo(nodo.derecho, nodo.dato, true);
            eliminado = true;
        }

        return nodo;
    }

    private int encontrarMinimo(NodoArbol nodo) {
        int minimo = nodo.dato;
        while (nodo.izquierdo != null) {
            minimo = nodo.izquierdo.dato;
            nodo = nodo.izquierdo;
        }
        return minimo;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();

        int opcion = 0;
        do {
            System.out.println("MENU:");
            System.out.println("1) Ingresar datos");
            System.out.println("2) Buscar elemento");
            System.out.println("3) Eliminar elemento");
            System.out.println("4) Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese una lista de números separados por espacios: ");
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    String listaNumeros = scanner.nextLine();
                    String[] numeros = listaNumeros.split(" ");
                    for (String numeroStr : numeros) {
                        int numero = Integer.parseInt(numeroStr);
                        arbol.insertar(numero);
                    }
                    System.out.println("Números insertados correctamente.");
                    break;
                case 2:
                    System.out.print("Ingrese un número a buscar: ");
                    int numeroBuscar = scanner.nextInt();
                    boolean encontrado = arbol.buscar(numeroBuscar);
                    if (encontrado) {
                        System.out.println("El número " + numeroBuscar + " está en el árbol.");
                    } else {
                        System.out.println("El número " + numeroBuscar + " no está en el árbol.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese un número a eliminar: ");
                    int numeroEliminar = scanner.nextInt();
                    arbol.eliminar(numeroEliminar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

            System.out.println("Estado actual del árbol:");
            arbol.mostrarArbol();
            System.out.println("------------------------------------");
        } while (opcion != 4);
    }
}
