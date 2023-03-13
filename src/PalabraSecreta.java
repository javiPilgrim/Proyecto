import java.util.ArrayList;
import java.util.List;

public class PalabraSecreta {
private String palabraOculta;
private List palabras;

    public PalabraSecreta() {
        String[] listado ={"coche", "parte", "batir", "perro","cerdo","serio","arduo","genio","preso","clero","sabor", "comer", "poder", "cielo", "cacho", "nieve", "lejos", "canto", "libro", "libre", "guapo", "papel", "corto","callo","gallo","tanto","tonto","cuero","cinco","santo","santa","verde"};
        palabras = new ArrayList();
        for(int i=0; i < listado.length; i++){
            palabras.add(listado[i]);
        }
        int r = (int) (Math.random()*palabras.size());
        palabraOculta = palabras.get(r).toString();
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

    public void introducirPalabra(String palabra){
        if(palabras.contains(palabra)){
            System.out.println("La palabra ya existe");
        }else{
            palabras.add(palabra);
            System.out.println("Se introdujo la palabra "+ palabra);
        }
    }
}
