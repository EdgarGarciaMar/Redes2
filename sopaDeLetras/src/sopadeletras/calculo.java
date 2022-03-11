/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sopadeletras;
import java.util.Scanner;
/**
 *
 * @author edgargarcia
 */
public class calculo {
    public static void main(String[] args) {
        int columna=0;
        int fila=0;
        int ubicacion=0;
         Scanner entrada= new Scanner(System.in);
        while (true){
            System.out.println("columna:");
            columna =entrada.nextByte();
            System.out.println("fila");
            fila =entrada.nextByte();
            ubicacion=16*fila+columna;
            System.out.println("Resultado:"+ubicacion);
        }
    }
}
