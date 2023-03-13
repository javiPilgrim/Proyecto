import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Formulario extends JFrame implements ActionListener {

    private JLabel label1, label2, label3, oculta, intentos, nIntentos, perdiste, coleccionPalabras, reloj;
    private JButton boton1, reiniciar, salir;
    private JTextField respuesta;
    private String palabraelegida;
    private String palabras = "";
    private String sonido = "src/music/Creation.wav";
    private String tiemposegundos = Menu.tiempoSegundos;
    InputStream in;
    AudioStream audio;
    public Timer tiempo;
    private int cuenta = 10;


    PalabraSecreta secreto = new PalabraSecreta();
    Oportunidades op = new Oportunidades();
    int vidas = op.getOportunidades();
    int exito = 0;


    public Formulario() throws Exception {

        setLayout(null);
        setTitle("Lingo");
        getContentPane().setBackground(new Color(113, 190, 221));



        // sonar cancion.
        in = new FileInputStream(sonido);
        audio = new AudioStream(in);
        AudioPlayer.player.start(audio);

        // label de Bienvenida.
        label1 = new JLabel("BIENVENIDO A LINGO: " + Menu.nombreJugador);
        label1.setBounds(240, -180, 400, 400);
        label1.setFont(new Font("Serif", Font.PLAIN, 30));
        label1.setForeground(Color.BLUE);
        add(label1);

        // label titulo de palabra secreta.
        label2 = new JLabel("Palabra Secreta:");
        label2.setBounds(40, -80, 400, 400);
        label2.setFont(new Font("Serif", Font.PLAIN, 30));
        label2.setForeground(Color.BLACK);
        add(label2);

        // label titulo de respuesta del jugador
        label3 = new JLabel("Tu Palabra:");
        label3.setBounds(40, -20, 400, 400);
        label3.setFont(new Font("Serif", Font.PLAIN, 30));
        label3.setForeground(Color.BLACK);
        add(label3);

        // label titulo de intentos
        intentos = new JLabel("Intentos:");
        intentos.setBounds(40, 70, 400, 400);
        intentos.setFont(new Font("Serif", Font.PLAIN, 30));
        intentos.setForeground(Color.BLACK);
        add(intentos);

        // label del numero de intentos
        nIntentos = new JLabel("5");
        nIntentos.setBounds(260, 250, 100, 50);
        nIntentos.setFont(new Font("Serif", Font.PLAIN, 50));
        nIntentos.setForeground(Color.YELLOW);
        add(nIntentos);

        // label de palabra oculta
        oculta = new JLabel("*****");
        oculta.setBounds(340, -70, 400, 400);
        oculta.setFont(new Font("Serif", Font.PLAIN, 50));
        oculta.setForeground(Color.BLACK);
        add(oculta);

        // Txtfield que recoge la respuesa del usuario
        respuesta = new JTextField();
        respuesta.setBounds(260, 165, 200, 50);
        respuesta.setFont(new Font("Serif", Font.PLAIN, 50));
        respuesta.setForeground(Color.RED);
        add(respuesta);

        // label que da si has ganado o perdido
        perdiste = new JLabel("");
        perdiste.setBounds(40, 125, 800, 400);
        perdiste.setFont(new Font("Serif", Font.PLAIN, 30));
        perdiste.setForeground(Color.BLACK);
        add(perdiste);

        // listado de palabras que introduce el usuario
        coleccionPalabras = new JLabel("");
        coleccionPalabras.setBounds(40, 165, 800, 400);
        coleccionPalabras.setFont(new Font("Serif", Font.PLAIN, 20));
        coleccionPalabras.setForeground(Color.BLACK);
        add(coleccionPalabras);

        // Boton de comprobar la palabra
        boton1 = new JButton("Comprueba");
        boton1.setBounds(550, 155, 200, 50);
        add(boton1);
        boton1.addActionListener(this);

        // Boton de Reiniciar
        reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(650, 355, 200, 50);
        add(reiniciar);
        reiniciar.addActionListener(this);

        // Boton de Salir
        salir = new JButton("Salir");
        salir.setBounds(350, 355, 200, 50);
        add(salir);
        salir.addActionListener(this);

        // label de la cuenta atrás
        reloj = new JLabel(tiemposegundos);
        reloj.setBounds(700, -20, 400, 200);
        reloj.setFont(new Font("Serif", Font.PLAIN, 60));
        reloj.setForeground(Color.BLACK);
        add(reloj);

        // contador de tiempo para la cuenta atras
        tiempo = new Timer(1000, null);
        tiempo.start();

        tiempo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operacionQueHaraTimer();
            }
        });

    }

    // metodo que compara la palabra oculta con la introducida
    public String comparar() {
        String devolucion = "";
        for (int i = 0; i < secreto.getPalabraOculta().length(); i++) {
            if (secreto.getPalabraOculta().charAt(i) == palabraelegida.charAt(i)) {
                devolucion += secreto.getPalabraOculta().charAt(i);
            } else {
                devolucion += "*";
            }
        }
        if (palabraelegida.equals(secreto.getPalabraOculta())) {
            exito = 1;
        }
        return devolucion;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            if (exito == 0 && vidas != 0) {
                if(respuesta.getText().length() > 5 || respuesta.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"La palabra debe tener 5 letras.");
                    respuesta.setText("");
                }else {
                    palabraelegida = respuesta.getText().toLowerCase();
                    comparar();
                    oculta.setText(comparar());
                    vidas--;
                    nIntentos.setText(String.valueOf(vidas));
                    respuesta.setText("");
                    palabras += ", " + palabraelegida;
                    coleccionPalabras.setText(palabras);
                }
            }
            if (exito == 1) {
                respuesta.setText("Victoria");
                perdiste.setText("VICTORIA!! HAS DESTAPADO LA PALABRA OCULTA!!");
                tiempo.stop();
                AudioPlayer.player.stop(audio);
            } else if (vidas == 0) {
                respuesta.setText("Perdiste");
                perdiste.setText("HAS PERDIDO!! LA PALABRA SECRETA ERA: " + secreto.getPalabraOculta());
                tiempo.stop();
                AudioPlayer.player.stop(audio);
            }

        }

        if (e.getSource() == reiniciar) {
            vidas = 5;
            exito = 0;
            reloj.setText(tiemposegundos);
            tiempo.start();
            secreto.getNewPalabraOculta();
            respuesta.setText("");
            oculta.setText("*****");
            nIntentos.setText(String.valueOf(vidas));
            perdiste.setText("");
            palabras = "";
            coleccionPalabras.setText("");
            sonarCancion();

        }

        if (e.getSource() == salir) {
            System.exit(0);
        }


    }
    // metodo para la cuenta atras
    private void operacionQueHaraTimer() {
        cuenta = Integer.parseInt(reloj.getText());
        cuenta--;
        reloj.setText(String.valueOf(cuenta));
        if (cuenta == 0) {
            tiempo.stop();
            reloj.setText("Tiempo");
            respuesta.setText("Perdiste");
            perdiste.setText("HAS PERDIDO!! LA PALABRA SECRETA ERA: " + secreto.getPalabraOculta());
        }
    }

    private void sonarCancion(){
        try {
            in = new FileInputStream(sonido);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            audio = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioPlayer.player.start(audio);
    }


}
