/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.ClienteURLConnection to edit this template
 */
package PSP03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author mario
 */
public class ClienteURLConnection {

    public static void main(String[] args) {// throws MalformedURLException, IOException {
        /*Scanner lectura = new Scanner(System.in);
        System.out.println("Ingrese una direccion web:\n");
        String web = lectura.nextLine();*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese una direccion web:\n");

        try {
            String web = br.readLine(); // Lee web como un String
            if (web.isEmpty()) {
                System.exit(0);
            } else {
                //crea objeto url
                URL url = new URL(web);
                //obtiene una conexión al recurso URL
                URLConnection conexion = url.openConnection();
                //se conecta pudiendo interactuar con parámetros
                conexion.connect();
                //obtiene el tipo de contenido
                String contentType = conexion.getContentType();
                System.out.println("Encabezados destacados:\n* Content-Type: "
                        + contentType);
            }
        //Capturamos los posibles errores   
        } catch (MalformedURLException m) {
            System.out.println("Error URL Mal formada, " + m);

        } catch (IOException i) {
            System.out.println("Error IOException, " + i);
        }
    }
}
