/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor1;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import utilidades.Utilidades;

/**
 *
 * @author mario
 */
public class ServidorHttp {

    public static void main(String[] args) throws IOException {

        final int PUERTO = 8000;
        HttpServer httpd = HttpServer.create(new InetSocketAddress(PUERTO), 0);
        httpd.createContext("/saludar", new HttpHandlerSaludar());

        System.out.println("SERVIDOR HTTP\n-------------");
        System.out.printf("[%s] Servidor HTTP iniciado en el puerto %d\n",
                utilidades.Utilidades.getFechaHoraActualFormateada(), PUERTO);

        httpd.start();
    }

}
