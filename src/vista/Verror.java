package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Verror {

	private static JFrame frame;

	/**
	 * Create the application.
	 */
	public Verror(String mensaje, String type) {
		initialize(mensaje, type);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mensaje, String type) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Verror.frame.dispose();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				Verror.frame.dispose();
			}
		});
		frame.setBounds(100, 100, 380, 159);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnAceptar.setBounds(265, 86, 89, 23);
		frame.getContentPane().add(btnAceptar);
		
		JTextPane txterror = new JTextPane();
		txterror.setEditable(false);
		txterror.setBounds(10, 11, 236, 98);
		frame.getContentPane().add(txterror);
		
		txterror.setText(mensaje);
		
		JLabel lblNewLabel = new JLabel("");
		if(type.equals("error"))
			lblNewLabel.setIcon(new ImageIcon(Verror.class.getResource("/vista/error.png")));
		else if(type.equals("info"))
			lblNewLabel.setIcon(new ImageIcon(Verror.class.getResource("/vista/info.png")));
		lblNewLabel.setBounds(265, 11, 90, 65);
		frame.getContentPane().add(lblNewLabel);
	}
}
