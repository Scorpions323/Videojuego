package es.etg.dam.partida;

import java.util.Random;

public class NumeroAleatorio {

    private static final int MINIMO = 1;
    private static final int MAXIMO = 100;
    private static final int DESPLAZAMIENTO = 1;

    public static int generar() {
        return new Random().nextInt(MAXIMO - MINIMO + DESPLAZAMIENTO) + MINIMO;
    }
}