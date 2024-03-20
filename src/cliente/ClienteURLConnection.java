/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.ClienteURLConnection to edit this template
 */
package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
//import java.net.URLConnection;

/**
 *
 * @author mario
 */
public class ClienteURLConnection {

    public static void main(String[] args) throws MalformedURLException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese una direccion web:");
        String web = br.readLine(); // Lee web como un String
        System.out.println("\nCliente HTTP de MPC\n\nConectandose a la URL " + web);
        try {
            if (web.isEmpty()) {
                System.exit(0);
            } else {
                //crea objeto url
                URL url = new URL(web);
                //obtiene una conexión al recurso URL
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                //se conecta pudiendo interactuar con parámetros
                conexion.connect();

                //Observamos el objeto que devuelve getHeaderFields y lo guardamos
                //en tipo de variable de mismas características
                Map<String, List<String>> mapaCabecera = conexion.getHeaderFields();

                //Mostramos por pantalla. 
                for (String clave : mapaCabecera.keySet()) {
                    List<String> valores = mapaCabecera.get(clave);
                    if (clave == null) {
                        System.out.printf("Respuesta: ");
                        for (String valor : valores) {
                            System.out.println(valor + "\n");
                        }
                    } else {
                        System.out.printf(clave + ":");
                        for (String valor : valores) {
                            System.out.println(valor);
                        }
                    }
                }

                if (conexion.getContentType().contains("text/html")) {
                    //Variables para crear archivo y escribir
                    File f = new File("./salida.html");
                    FileWriter fw = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(fw);

                    System.out.println("\nContenido HTML guardado en salida.html.");
                    //variable para guardar datos
                    String linea = "";

                    br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

                    while ((linea = br.readLine()) != null) {
                        bw.write(linea);

                    }

                    //Cerramos flujos
                    bw.close();
                    br.close();
                    fw.close();

                } else {
                    System.out.println("\nContenido de tipo no HTML.");
                }
            }

            //Capturamos los posibles errores   
        } catch (MalformedURLException m) {
            System.out.println("Error. URL no valida, " + m.getMessage());

        } catch (IOException i) {
            System.out.println("Error de E/S: " + i.getMessage());
        }
    }
}
