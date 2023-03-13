import javax.swing.*;
import java.awt.*;

public class CuentaAtras extends JFrame {
    public Timer tiempo;
    private JLabel reloj;
    private int cuenta = 10;

    public CuentaAtras(){
        tiempo = new Timer(1000,null);
        tiempo.start();

        tiempo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operacionQueHaraTimer();
            }
        });

        setBounds(0, 0, 920, 540);
        setVisible(true);
        setLocationRelativeTo(null);

        reloj = new JLabel("10");
        reloj.setBounds(440, 150, 400,200);
        reloj.setFont(new Font("Serif", Font.PLAIN, 60));
        reloj.setForeground(Color.BLACK);
        add(reloj);

}

    private void operacionQueHaraTimer() {
        cuenta = Integer.parseInt(reloj.getText());
        cuenta--;
        reloj.setText(String.valueOf(cuenta));
        if(cuenta==0){
            tiempo.stop();
            reloj.setText("Fin del tiempo");
        }
    }


    public static void main(String[] args) {
        CuentaAtras reloj = new CuentaAtras();
    }
}