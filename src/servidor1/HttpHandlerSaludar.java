/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilidades.Utilidades;

/**
 *
 * @author mario
 */
public class HttpHandlerSaludar implements HttpHandler {

    private static final int HTTP_OK_STATUS = 200;

    @Override
    public void handle(HttpExchange intercambio) throws IOException {
        //Variable para recoger la consulta
        URI uri = intercambio.getRequestURI();
        System.out.printf("[%s] Atendiendo a petición: %s\n", utilidades.Utilidades.getFechaHoraActualFormateada(), uri);

        //Guardamos la respuesta de nuestro método creaRespuesta() obtenido al pasarle consulta de cliente
        String response = creaRespuesta(uri.getQuery());

        System.out.printf("[%s] Respuesta a la petición: %s -> %s\n", utilidades.Utilidades.getFechaHoraActualFormateada(), uri, response);

        //Respondemos con cabecera
        intercambio.sendResponseHeaders(HTTP_OK_STATUS, response.getBytes().length);

        //Preparamos flujo para dar respuesta al cliente
        OutputStream os = intercambio.getResponseBody();
        os.write(response.getBytes());
        //cerramos flujo
        os.close();

    }

    /**
     *
     * @param query Consulta recibida por cliente
     * @return respuesta en formato String
     */
    private String creaRespuesta(String query) {

        //creamos dos String de retorno según cumpla o no las condiciones
        //para ello hemos creado un método comprobador.
        if (compruebaExpresion(query)) {

            //Formato = nombre=xxx&apellido=xxx
            String mensajeCliente = query;

            String[] subcadenas = mensajeCliente.split("&");

            String nombre = "";
            String apellido = "";

            for (String subcadena : subcadenas) {
                String[] partes = subcadena.split("=");

                if (partes[0].equals("nombre")) {
                    nombre = partes[1];
                } else if (partes[0].equals("apellido")) {
                    apellido = partes[1];
                }
            }
            return "Hola " + nombre + " " + apellido;
        }

        return "Hola persona no identificada";

    }

    /**
     *
     * @param query consulta recibida por cliente
     * @return devuelve si es corrrecta la expresion
     */
    public boolean compruebaExpresion(String query) {
        String lineaDeTexto = query;
        Pattern pattern = Pattern.compile("nombre=\\w+&apellido=\\w+");
        Matcher matcher = pattern.matcher(lineaDeTexto);

        return matcher.matches();
    }

}
