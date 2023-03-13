import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


public class PalabraSecreta {
private String palabraOculta;
private List palabras;
private File archivo;
private FileWriter escritor;


    public PalabraSecreta() {
        archivo = new File("src/textos/palabras.txt");
        Scanner lector = null;
        try {
            lector = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        palabras = new ArrayList<>();
        while (lector.hasNext()) {
            String palabra = lector.next();
            palabras.add(palabra);
        }
        lector.close();
        int r = (int) (Math.random()*palabras.size());
        palabraOculta = palabras.get(r).toString();
    }

    public void imprimeTexto(){
        palabras.forEach(palabra-> System.out.println(palabra));
    }
    public String cuentaPalabras(){
        String cuenta = "";
        cuenta = String.valueOf(palabras.size());
        return cuenta;
    }

    public void incorporaPalabra(){
        String palabra="";
        while (!(palabra.equals("s") || palabra.equals("S"))) {
            try {
                escritor = new FileWriter(archivo, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner nt = new Scanner(System.in);
            System.out.println("Escribe la palabra para incorporarla o pulsa s para salir: ");
            palabra = nt.next().toLowerCase(Locale.ROOT);
            if (palabras.contains(palabra)) {
                System.out.println("la palabra ya existe");
            } else {
                if (palabra.length() == 5) {
                    System.out.println("La palabra es nueva y se ha incluido");

                    try {
                        escritor.write(" " + palabra);
                        escritor.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("la palabra no es valida");
                }
            }

        }
    }


    public String getPalabraOculta() {
        return palabraOculta;
    }
    public String getNewPalabraOculta(){
        int r = (int) (Math.random()*palabras.size());
        palabraOculta = palabras.get(r).toString();
        return palabraOculta;
    }

    public void setPalabraOculta(String palabraOculta) {
        this.palabraOculta = palabraOculta;
    }

    public static void main(String[] args) {
        PalabraSecreta texto = new PalabraSecreta();
        texto.imprimeTexto();
        System.out.println("La lista tiene " + texto.cuentaPalabras() + " palabras");
        texto.incorporaPalabra();


    }

}
