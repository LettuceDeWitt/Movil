import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Ventana3 extends JFrame implements ActionListener {

    JTextField txtEdad;
    JButton btnEvaluar;
    JPanel panel01;

    public Ventana3() {
        setTitle("Mayor de edad");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtEdad = new JTextField(10);
        btnEvaluar = new JButton("Evaluar");

        btnEvaluar.addActionListener(this);

        panel01 = new JPanel();

        panel01.add(new JLabel("Ingresa tu edad:"));
        panel01.add(txtEdad);
        panel01.add(btnEvaluar);

        add(panel01);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ventana3();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int edad = Integer.parseInt(txtEdad.getText());

        if (edad >= 18) {
            JOptionPane.showMessageDialog(this, "Eres mayor de edad");
        } else {
            JOptionPane.showMessageDialog(this, "Eres menor de edad");
        }
    }
}