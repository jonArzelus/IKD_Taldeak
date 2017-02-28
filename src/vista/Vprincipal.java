package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import controlador.Cprincipal;
import modelo.Alumno;
import modelo.Centro;
import modelo.Mnagusia;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Vprincipal {

	private JFrame frame;
	//private static controlador.Cprincipal DB;
	public static ArrayList<Alumno> listaAlumnos;
	public static ArrayList<Centro> listaCentros;
	private JTextField txtarchivo;

	/**
	 * Launch the application. Rutina principal para lanzar la interfaz y comenzar a usar todo
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cprincipal.comenzarDB("datubasea");
					//se puede cargar un txt con el formato: nombre(izen abizenak),genero(Hombre/Mujer),centro(de la tabla centros),euskera(true/false)
					//Vprincipal.añadirAlumnosTXT("ikasleak.txt");
					Vprincipal window = new Vprincipal(Cprincipal.getAlumnos(),Cprincipal.getCentros());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vprincipal(ArrayList<Alumno> lista, ArrayList<Centro> lista1) {
		listaAlumnos = lista;
		listaCentros = lista1;
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
				Valumnos a =  new Valumnos(Cprincipal.getAlumnos(),Cprincipal.getCentros());
				frame.dispose();
				//a.frame.set
			}
		});
		btnAlumnos.setBounds(20, 182, 137, 23);
		frame.getContentPane().add(btnAlumnos);
		
		JButton btnCentros = new JButton("Centros");
		btnCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vcentros a =  new Vcentros(Cprincipal.getAlumnos(),Cprincipal.getCentros());
				frame.dispose();
			}
		});
		btnCentros.setBounds(20, 216, 137, 23);
		frame.getContentPane().add(btnCentros);
		
		JButton btnRetos = new JButton("Retos");
		btnRetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vnecesidad a = new Vnecesidad(Cprincipal.getNecesidad());
				frame.dispose();
			}
		});
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
		
		JLabel lblNcentros = new JLabel(Integer.toString(listaCentros.size()));
		lblNcentros.setBounds(251, 125, 34, 14);
		frame.getContentPane().add(lblNcentros);
		
		JLabel lblNretos = new JLabel(Integer.toString(Cprincipal.getNumeroNecesidad()));
		lblNretos.setBounds(251, 150, 34, 14);
		frame.getContentPane().add(lblNretos);
		
		JButton btnreparto = new JButton("\u00A1Realizar reparto!");
		btnreparto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//modelo.Mnagusia reparto = new modelo.Mnagusia();
				Mnagusia.realizarReparto(Cprincipal.getAlumnos(), Cprincipal.getCentros(), Cprincipal.getNecesidad());
			}
		});
		btnreparto.setBounds(295, 100, 188, 50);
		frame.getContentPane().add(btnreparto);
		
		txtarchivo = new JTextField();
		txtarchivo.setBounds(180, 217, 196, 20);
		frame.getContentPane().add(txtarchivo);
		txtarchivo.setColumns(10);
		
		JButton btnarchivo = new JButton("Cargar archivo");
		btnarchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtarchivo.getText().isEmpty()) {
					Vprincipal.añadirAlumnosTXT(txtarchivo.getText());
					Vprincipal a = new Vprincipal(Cprincipal.getAlumnos(), Cprincipal.getCentros());
					frame.dispose();
				}
			}
		});
		btnarchivo.setBounds(180, 250, 122, 23);
		frame.getContentPane().add(btnarchivo);
		
		JLabel lblNewLabel_1 = new JLabel("Cargar archivo .txt con el siguiente nombre:");
		lblNewLabel_1.setBounds(180, 186, 328, 28);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	public static void añadirAlumnosTXT(String nombre) {
		String fila;
		String[] partes;
		try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
		    while ((fila = br.readLine()) != null) {
		    	partes=fila.split(",");
				Cprincipal.addAlumnos(partes[0], partes[2], Boolean.valueOf(partes[3]), partes[1]);
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
