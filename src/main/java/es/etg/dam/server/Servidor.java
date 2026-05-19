package es.etg.dam.server;

import es.etg.dam.exception.ServidorException;
import es.etg.dam.common.Conexion;
import es.etg.dam.partida.Partida;
import es.etg.dam.util.LogUtil;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final String HOST = "localhost";
    public static final int PUERTO = 8080;

    private static final String LOG = "Servidor.log";
    private static final String MSG_INICIO = "Servidor escuchando en puerto %d";
    private static final String MSG_ESPERA = "Esperando jugadores...";
    private static final String MSG_CONEXION = "Jugador conectado: %s";

    public static void main(String[] args) throws ServidorException {

        Logger logger = null;

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            logger = LogUtil.crear(LOG);

            LogUtil.escribir(logger, Level.INFO, String.format(MSG_INICIO, PUERTO));

            while (true) {
                Partida juego = new Partida();

                LogUtil.escribir(logger, Level.INFO, MSG_ESPERA);

                for (int i = 0; i < Partida.MAX_JUGADORES; i++) {
                    Socket socket = server.accept();
                    String nombre = Conexion.recibir(socket);

                    LogUtil.escribir(logger, Level.INFO, String.format(MSG_CONEXION, nombre));
                    juego.registrar(nombre, socket);
                }

                Thread hilo = new Thread(juego);
                hilo.start();
            }
        } catch (Exception e) {
            LogUtil.escribir(logger, Level.SEVERE, e.getMessage(), e);
            throw new ServidorException(e.getMessage(), e);
        }
    }
}