package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Verror {

	private static JFrame frame;

	/**
	 * Create the application.
	 */
	public Verror(String mensaje) {
		initialize(mensaje);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mensaje) {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 159);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnAceptar.setBounds(173, 86, 89, 23);
		frame.getContentPane().add(btnAceptar);
		
		JTextPane txterror = new JTextPane();
		txterror.setEditable(false);
		txterror.setBounds(10, 11, 252, 64);
		frame.getContentPane().add(txterror);
		
		txterror.setText(mensaje);
	}
}
