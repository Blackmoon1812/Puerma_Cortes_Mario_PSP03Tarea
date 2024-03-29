/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mario
 */
public class Utilidades  {

    public static boolean esPrimo(long numero) {
        boolean primo= true;
        long candidatoDivisor= 2;
        while (candidatoDivisor < numero && primo) {
            try {
                Thread.sleep (0, 2);  // Para ralentizar el proceso
            } catch (InterruptedException ex) {
                
            }
            if (numero % candidatoDivisor == 0) {
                primo= false;
            } else
                candidatoDivisor++;                       
        }        
        return primo;
    }
    
    public static String getFechaHoraActualFormateada() {
        String patronFormato= "dd/MM/yyyy HH:mm:ss:SSS";
        DateTimeFormatter formato= DateTimeFormatter.ofPattern(patronFormato);
        LocalDateTime ahora= LocalDateTime.now();
        return ahora.format(formato);
    }
    
// Este es un método más eficiente y rápido para el test de primalidad
// Pero para estos ejercicios necesitamos precisamente algo más lento    
/*    boolean esPrimo(long numero) {
        boolean primo= true;
        long candidatoDivisor= 3;
        if (numero % 2 == 0) {
            primo= false;
        }
        while (candidatoDivisor < (int) Math.sqrt(numero) && !primo) {
            if (numero % candidatoDivisor == 0)
                primo= false;
            else
                candidatoDivisor +=2;                       
        }        
        return primo;
    }
*/    
    
}
