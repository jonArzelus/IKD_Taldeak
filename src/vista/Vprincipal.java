package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import controlador.Cprincipal;
import modelo.Alumno;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Vprincipal {

	private JFrame frame;
	private static controlador.Cprincipal DB;
	public static ArrayList<Alumno> listaAlumnos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB = new controlador.Cprincipal("datubasea");
					//for(int i=0;i<50;i++)
					Cprincipal.addAlumnos("Jon Arzelus", "Informatika", true);
					Cprincipal.addAlumnos("Jon Arzeluz", "Informatika", false);
					Vprincipal window = new Vprincipal(Cprincipal.getAlumnos());
					//Vprincipal.listaAlumnos = Cprincipal.getAlumnos();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vprincipal(ArrayList<Alumno> lista) {
		listaAlumnos = lista;
		initialize();
		frame.setVisible(true);
		try {
		  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
		  System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 324);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vprincipal.class.getResource("/vista/ikdgaztelogo.png")));
		lblNewLabel.setBounds(10, 11, 160, 160);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAlumnos = new JButton("Alumnos");
		btnAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Valumnos a =  new Valumnos(listaAlumnos);
				frame.dispose();
				//a.frame.set
			}
		});
		btnAlumnos.setBounds(20, 182, 137, 23);
		frame.getContentPane().add(btnAlumnos);
		
		JButton btnCentros = new JButton("Centros");
		btnCentros.setBounds(20, 216, 137, 23);
		frame.getContentPane().add(btnCentros);
		
		JButton btnRetos = new JButton("Retos");
		btnRetos.setBounds(20, 250, 137, 23);
		frame.getContentPane().add(btnRetos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(419, 250, 89, 23);
		frame.getContentPane().add(btnSalir);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(180, 11, 328, 78);
		frame.getContentPane().add(lblDescripcion);
		
		JLabel lblAlumnos = new JLabel("Alumnos:");
		lblAlumnos.setBounds(180, 100, 70, 14);
		frame.getContentPane().add(lblAlumnos);
		
		JLabel lblCentros = new JLabel("Centros:");
		lblCentros.setBounds(180, 125, 70, 14);
		frame.getContentPane().add(lblCentros);
		
		JLabel lblRetos = new JLabel("Retos:");
		lblRetos.setBounds(180, 150, 70, 14);
		frame.getContentPane().add(lblRetos);
		
		JLabel lblNalumnos = new JLabel(Integer.toString(listaAlumnos.size()));
		lblNalumnos.setBounds(251, 100, 34, 14);
		frame.getContentPane().add(lblNalumnos);
		
		JLabel lblNcentros = new JLabel("");
		lblNcentros.setBounds(251, 125, 34, 14);
		frame.getContentPane().add(lblNcentros);
		
		JLabel lblNretos = new JLabel("");
		lblNretos.setBounds(251, 150, 34, 14);
		frame.getContentPane().add(lblNretos);
	}
}
