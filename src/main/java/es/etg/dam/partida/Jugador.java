package es.etg.dam.partida;

import java.net.Socket;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Jugador {

    private final String nombre;
    private final Socket socket;

    @Setter
    private int numero;

    public Jugador(String nombre, Socket socket) {
        this.nombre = nombre;
        this.socket = socket;
        this.numero = NumeroAleatorio.generar();
    }
}