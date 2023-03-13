import javax.swing.*;
import java.util.Scanner;

public class Prueba extends JFrame {
    private ImageIcon logo;
    private JLabel dibujo;

    public Prueba(){

    }

    public static void main(String[] args) {
        PalabraSecreta listado = new PalabraSecreta();
        Scanner nt = new Scanner(System.in);
        String respuesta = "";
        do {
            System.out.println("Quieres incorporar palabras? (s/n) : ");
            respuesta = nt.next();
            if(respuesta.equals("s")){
                System.out.println("introduce tu palabra: ");
                respuesta = nt.next();
                listado.introducirPalabra(respuesta);
            }else{
                System.out.println("Ten un buen dia");
            }
        }while(!respuesta.equals("n"));
    }

}
