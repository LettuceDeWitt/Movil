import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Gato01 extends JFrame implements ActionListener {

    JButton botones[] = new JButton[9];
    boolean turnoX;
    Font fuente = new Font("Arial", Font.BOLD, 50);

    public Gato01() {
        setSize(400, 400);
        setTitle("Juego Gato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            add(botones[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String letra;

        if (turnoX) {
            letra = "X";
            turnoX = false;
        } else {
            letra = "O";
            turnoX = true;
        }

        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                botones[i].setText(letra);
                botones[i].setEnabled(false);
                verificarGanador(); // 👈 validación
            }
        }
    }

    //  Verificar ganador
    public void verificarGanador() {
        String[][] tablero = new String[3][3];

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = botones[k].getText();
                k++;
            }
        }

        // Filas
        for (int i = 0; i < 3; i++) {
            if (!tablero[i][0].equals("") &&
                    tablero[i][0].equals(tablero[i][1]) &&
                    tablero[i][1].equals(tablero[i][2])) {
                ganador(tablero[i][0]);
            }
        }

        // Columnas
        for (int j = 0; j < 3; j++) {
            if (!tablero[0][j].equals("") &&
                    tablero[0][j].equals(tablero[1][j]) &&
                    tablero[1][j].equals(tablero[2][j])) {
                ganador(tablero[0][j]);
            }
        }

        // Diagonal principal
        if (!tablero[0][0].equals("") &&
                tablero[0][0].equals(tablero[1][1]) &&
                tablero[1][1].equals(tablero[2][2])) {
            ganador(tablero[0][0]);
        }

        // Diagonal secundaria
        if (!tablero[0][2].equals("") &&
                tablero[0][2].equals(tablero[1][1]) &&
                tablero[1][1].equals(tablero[2][0])) {
            ganador(tablero[0][2]);
        }
    }

    //  Mostrar ganador
    public void ganador(String letra) {
        JOptionPane.showMessageDialog(this, "Ganó: " + letra);

        // Desactivar todos los botones
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }
}