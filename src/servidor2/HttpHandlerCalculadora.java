/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor2;

import servidor1.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilidades.Utilidades;

/**
 *
 * @author mario
 */
public class HttpHandlerCalculadora implements HttpHandler {

    private static final int HTTP_OK_STATUS = 200;

    @Override
    public void handle(HttpExchange intercambio) throws IOException {
        //Variable para recoger la consulta
        URI uri = intercambio.getRequestURI();
        System.out.printf("[%s] Atendiendo a peticion: %s\n", utilidades.Utilidades.getFechaHoraActualFormateada(), uri);

        //Guardamos la respuesta de nuestro método creaRespuesta() obtenido al pasarle consulta de cliente
        String response = creaRespuesta(uri.getQuery());

        System.out.printf("[%s] Respuesta a la peticion: %s -> %s\n", utilidades.Utilidades.getFechaHoraActualFormateada(), uri, response);

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

        //creamos String de retorno según cumpla o no las condiciones
        //para ello hemos creado un método comprobador.
        if (compruebaExpresion(query)) {

            //Formato --> op1=xxx&op2=yyy&ope=zzz
            String mensajeCliente = query;

            String[] subcadenas = mensajeCliente.split("&");

            String op1 = "";
            String op2 = "";
            String operacion = "";
            int resultado;

            for (String subcadena : subcadenas) {
                String[] partes = subcadena.split("=");

                switch (partes[0]) {
                    case "op1":
                        op1 = partes[1];
                        break;
                    case "op2":
                        op2 = partes[1];
                        break;
                    case "ope":
                        operacion = partes[1];
                        break;
                    default:
                        break;
                }
            }
            if (null != operacion) {
                switch (operacion) {
                    case "suma":
                        resultado = parseInt(op1) + parseInt(op2);
                        return op1 + " + " + op2 + " = " + resultado;
                    case "resta":
                        resultado = parseInt(op1) - parseInt(op2);
                        return op1 + " - " + op2 + " = " + resultado;
                    case "multiplica":
                        resultado = parseInt(op1) * parseInt(op2);
                        return op1 + " * " + op2 + " = " + resultado;
                    case "divide":
                        resultado = parseInt(op1) / parseInt(op2);
                        return op1 + " / " + op2 + " = " + resultado;
                    default:
                        break;
                }
            }

        }

        return "Peticion no valida";

    }

    /**
     *
     * @param query consulta recibida por cliente
     * @return devuelve si es corrrecta la expresion
     */
    public boolean compruebaExpresion(String query) {
        String lineaDeTexto = query;
        Pattern pattern = Pattern.compile("op1=\\w+&op2=\\w+&ope=\\w+");
        Matcher matcher = pattern.matcher(lineaDeTexto);

        return matcher.matches();
    }

}
