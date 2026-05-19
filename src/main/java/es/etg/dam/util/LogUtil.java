package es.etg.dam.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {

    private static final String NOMBRE = "Logger";

    public static Logger crear(String ficheroLog) throws IOException {
        Logger logger = Logger.getLogger(NOMBRE);

        if (logger.getHandlers().length == 0) {
            FileHandler fh = new FileHandler(ficheroLog, true);
            SimpleFormatter formato = new SimpleFormatter();

            logger.addHandler(fh);
            fh.setFormatter(formato);

            logger.setUseParentHandlers(false);
        }
        return logger;
    }

    public static void escribir(Logger logger, Level nivel, String mensaje) {
        logger.log(nivel, mensaje);
    }

    public static void escribir(Logger logger, Level nivel, String mensaje, Throwable e) {
        logger.log(nivel, mensaje, e);
    }
}