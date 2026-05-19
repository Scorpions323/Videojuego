package es.etg.dam.partida;

import es.etg.dam.common.Conexion;
import es.etg.dam.exception.PartidaException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Partida implements Runnable {

    public static final int MAX_JUGADORES = 2;
    private static final String MSG_GANA = "ENHORABUENA";
    private static final String MSG_PIERDE = "GAME OVER";

    private final List<Jugador> jugadores = new ArrayList<>();

    public void registrar(String nombre, Socket socket) {
        jugadores.add(new Jugador(nombre, socket));
    }

    @Override
    public void run() {
        try {
            Jugador ganador = null;
            String mensaje;

            for (int i = 0; i < MAX_JUGADORES; i++) {
                Jugador aux = jugadores.get(i);
                if (ganador == null || aux.getNumero() > ganador.getNumero()) {
                    ganador = aux;
                }
            }

            for (Jugador jugador : jugadores) {
                mensaje = MSG_PIERDE;
                if (jugador == ganador) {
                    mensaje = MSG_GANA;
                }
                Conexion.enviar(mensaje, jugador.getSocket());
            }

        } catch (Exception e) {
            throw new PartidaException(e.getMessage(), e);
        }
    }
}