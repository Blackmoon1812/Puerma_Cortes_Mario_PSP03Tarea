/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor2;
 
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import servidor1.*;
 
/**
 *
 * @author mario
 */
public class ServidorHttp {
 
    public static void main(String[] args) throws IOException {
 
        final int PUERTO = 80;
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PUERTO), 0);
 
        httpServer.createContext("/saludar", new HttpHandlerSaludar());
        httpServer.createContext("/calculadora", new HttpHandlerCalculadora());
        
        httpServer.setExecutor(Executors.newCachedThreadPool());
 
        System.out.println("SERVIDOR HTTP\n-------------");
        System.out.printf("[%s] Servidor HTTP iniciado en el puerto %d\n",
                utilidades.Utilidades.getFechaHoraActualFormateada(), PUERTO);
        
        
 
        httpServer.start();
    }
 
}
