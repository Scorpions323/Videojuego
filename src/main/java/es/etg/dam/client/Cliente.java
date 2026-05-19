package es.etg.dam.client;

import es.etg.dam.exception.ClienteException;
import es.etg.dam.common.Conexion;
import es.etg.dam.server.Servidor;
import es.etg.dam.util.LogUtil;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;

public class Cliente {

    private static final int NOMBRE = 0;
    private static final String LOG = "cliente.log";

    public static void main(String[] args) throws ClienteException {

        Logger logger = null;

        try (Socket socket = new Socket(Servidor.HOST, Servidor.PUERTO)) {
            logger = LogUtil.crear(LOG);

            String nombre = args[NOMBRE];
            Conexion.enviar(nombre, socket);

            String resultado = Conexion.recibir(socket);
            System.out.println(resultado);

        } catch (Exception e) {
            LogUtil.escribir(logger, Level.SEVERE, e.getMessage(), e);
            throw new ClienteException(e.getMessage(), e);
        }
    }
}