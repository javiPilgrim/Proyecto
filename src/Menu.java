import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;


public class Menu extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu menuOpciones, menuAcercaDe, menuTemporizador;
    private JMenuItem minuto, minutomedio, dosminutos;
    public static String nombreJugador = "";
    private ImageIcon logo;
    private JLabel instrucciones;
    private JLabel instrucciones2;
    private JLabel instrucciones3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel dibujo;
    private JButton AñadirNombre;
    private JButton Jugar;
    private JButton comprobar;
    private JTextField npalabra;
    private PalabraSecreta oculta = new PalabraSecreta();
    private String nombre = "";
    private String sound = "src/music/waterfall.wav";
    InputStream in;
    AudioStream audio;
    public static String tiempoSegundos;

    public Menu() throws Exception{
        setLayout(null);
        setTitle("Menu de Bienvenida");
        getContentPane().setBackground(new Color(255,174,201));
        in = new FileInputStream(sound);
        audio = new AudioStream(in);
        AudioPlayer.player.start(audio);

        logo = new ImageIcon("src/imagenes/Lingo.png");
        dibujo = new JLabel(logo);
        dibujo.setBounds(30,290,300,200);
        add(dibujo);

        mb = new JMenuBar();
        mb.setBackground(new Color(255,0,0));
        setJMenuBar(mb);

        menuOpciones = new JMenu("Opciones");
        menuOpciones.setBackground(new Color(255,0,0));
        menuOpciones.setFont(new Font("Andale Mono",1,14));
        menuOpciones.setForeground(new Color(255,255,255));
        mb.add(menuOpciones);

        menuTemporizador = new JMenu("Temporizador");
        menuTemporizador.setFont(new Font("Andale Mono", 1, 14));
        menuTemporizador.setForeground(new Color(255, 0, 0));
        menuOpciones.add(menuTemporizador);

        minuto = new JMenuItem("1 minuto");
        minuto.setFont(new Font("Andale Mono", 1, 14));
        minuto.setForeground(new Color(255, 0, 0));
        menuTemporizador.add(minuto);
        minuto.addActionListener(this);

        minutomedio = new JMenuItem("minuto y medio");
        minutomedio.setFont(new Font("Andale Mono", 1, 14));
        minutomedio.setForeground(new Color(255, 0, 0));
        menuTemporizador.add(minutomedio);
        minutomedio.addActionListener(this);

        dosminutos = new JMenuItem("2 minutos");
        dosminutos.setFont(new Font("Andale Mono", 1, 14));
        dosminutos.setForeground(new Color(255, 0, 0));
        menuTemporizador.add(dosminutos);
        dosminutos.addActionListener(this);



        instrucciones = new JLabel("Este juego consiste en adivinar la palabra de cinco letras escondida.");
        instrucciones.setBounds(50, -100, 680, 400);
        instrucciones.setFont(new Font("Serif", Font.PLAIN, 24));
        instrucciones.setForeground(Color.BLACK);
        add(instrucciones);

        instrucciones2 = new JLabel("Introduce palabras hasta adivinar cual es la palabra oculta.");
        instrucciones2.setBounds(50, -70, 680, 400);
        instrucciones2.setFont(new Font("Serif", Font.PLAIN, 24));
        instrucciones2.setForeground(Color.BLACK);
        add(instrucciones2);

        instrucciones3 = new JLabel("Pero, ojo, solo tienes 5 intentos.");
        instrucciones3.setBounds(50, -40, 680, 400);
        instrucciones3.setFont(new Font("Serif", Font.PLAIN, 24));
        instrucciones3.setForeground(Color.BLACK);
        add(instrucciones3);

        label1 = new JLabel("MENU");
        label1.setBounds(350, -180, 400, 400);
        label1.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setForeground(Color.BLUE);
        add(label1);


        label2 = new JLabel("");
        label2.setBounds(40, -120, 400, 400);
        label2.setFont(new Font("Serif", Font.PLAIN, 30));
        label2.setForeground(Color.BLACK);
        add(label2);

        label3 = new JLabel("");
        label3.setBounds(40, 60, 600, 400);
        label3.setFont(new Font("Serif", Font.PLAIN, 30));
        label3.setForeground(Color.BLACK);
        add(label3);

        npalabra = new JTextField();
        npalabra.setVisible(false);
        npalabra.setBounds(260,165,200,50);
        npalabra.setFont(new Font("Serif", Font.PLAIN, 50));
        npalabra.setForeground(Color.RED);
        add(npalabra);



        Jugar = new JButton("Jugar");
        Jugar.setBounds(650, 355, 200, 50);
        Jugar.setEnabled(false);
        add(Jugar);
        Jugar.addActionListener(this);

        AñadirNombre = new JButton("Añadir tu nombre");
        AñadirNombre.setBounds(350, 355, 200, 50);
        add(AñadirNombre);
        AñadirNombre.addActionListener(this);

        comprobar = new JButton("comprobar");
        comprobar.setBounds(550,155,200,50);
        comprobar.setVisible(false);
        add(comprobar);
        comprobar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Jugar) {
            AudioPlayer.player.stop(audio);
            setVisible(false);
            Formulario principal = null;
            try {
                principal = new Formulario();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            principal.setBounds(0, 0, 920, 540);
            principal.setVisible(true);
            principal.setLocationRelativeTo(null);



        }
        if (e.getSource() == AñadirNombre) {
            label2.setText("Introduce tu nombre: ");
            npalabra.setVisible(true);
            comprobar.setVisible(true);
            instrucciones.setVisible(false);
            instrucciones2.setVisible(false);
            instrucciones3.setVisible(false);

        }
        if (e.getSource() == comprobar) {
            nombreJugador = npalabra.getText().trim().toUpperCase();
            if(nombreJugador.equals("")){
                JOptionPane.showMessageDialog(null,"Debes escribir un nombre");
            }
            else {
                label3.setText("Bienvenido " + nombreJugador + ". Pulsa ahora Jugar");
                Jugar.setEnabled(true);
            }
        }

        if(e.getSource() == minuto){
            tiempoSegundos = "75";
        }
        if(e.getSource() == minuto){
            tiempoSegundos = "90";
        }
        if(e.getSource() == dosminutos){
            tiempoSegundos = "120";
        }
    }


}