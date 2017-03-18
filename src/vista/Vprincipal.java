package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import controlador.Cprincipal;
import modelo.Alumno;
import modelo.Centro;
import modelo.Mnagusia;
import modelo.Mprincipal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Vprincipal {

	private JFrame frame;
	//private static controlador.Cprincipal DB;
	public static ArrayList<Alumno> listaAlumnos;
	public static ArrayList<Centro> listaCentros;
	private JTextField txtarchivo;
	private JTextField txtpreferencias;
	private JTextField txtgrado;
	private JTextField txtgenero;
	private final File f = new File(Vprincipal.class.getProtectionDomain().getCodeSource().getLocation().getPath());

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
		frame.setBounds(100, 100, 605, 324);
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
		btnSalir.setBounds(490, 251, 89, 23);
		frame.getContentPane().add(btnSalir);
		
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
				//Mnagusia.realizarReparto(Cprincipal.getAlumnos(), Cprincipal.getCentros(), Cprincipal.getNecesidad());
				try {
					if(!txtpreferencias.getText().equals("")&&!txtgrado.getText().equals("")&&!txtgenero.getText().equals("")&&(Double.valueOf(txtpreferencias.getText())+Double.valueOf(txtgenero.getText())+Double.valueOf(txtgrado.getText()))==100)
						Mprincipal.realizarReparto(Cprincipal.getAlumnos(), Cprincipal.getCentros(), Cprincipal.getNecesidad(), Double.valueOf(txtpreferencias.getText()), Double.valueOf(txtgenero.getText()), Double.valueOf(txtgrado.getText()));
				} catch (NumberFormatException e) {
					Verror a = new Verror("Para realizar el reparto la suma de los tres campos tiene que dar 100.0 y sólo se admiten carácteres numéricos no negativos","error");
				}
			}
		});
		btnreparto.setBounds(433, 133, 146, 23);
		frame.getContentPane().add(btnreparto);
		
		txtarchivo = new JTextField();
		txtarchivo.setText("Ikasleak.txt");
		txtarchivo.setBounds(180, 217, 300, 20);
		frame.getContentPane().add(txtarchivo);
		txtarchivo.setColumns(10);
		
		JButton btnarchivo = new JButton("Cargar archivo");
		btnarchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtarchivo.getText().isEmpty()) {
					boolean az;
					az = Vprincipal.añadirAlumnosTXT(txtarchivo.getText());
					Verror a;
					Vprincipal x;
					if(!az) //si no ha terminado bien el proceso enseña un frame de error
						 a = new Verror("No se ha encontrado el fichero '"+txtarchivo.getText()+"' en el directorio '"+f.getAbsolutePath()+"' o bien ha habido algún error al leerlo. Hay que respetar el formato de la plantilla","error");
					else {
						x = new Vprincipal(Cprincipal.getAlumnos(), Cprincipal.getCentros());
						frame.dispose();
					}
				}
			}
		});
		btnarchivo.setBounds(180, 250, 122, 23);
		frame.getContentPane().add(btnarchivo);
		
		JLabel lblNewLabel_1 = new JLabel("Cargar archivo .txt con el siguiente nombre:");
		lblNewLabel_1.setBounds(180, 186, 328, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblGrado = new JLabel("G\u00E9nero");
		lblGrado.setBounds(490, 101, 89, 14);
		frame.getContentPane().add(lblGrado);
		
		JLabel lblPreferencias = new JLabel("Preferencias");
		lblPreferencias.setBounds(490, 51, 89, 14);
		frame.getContentPane().add(lblPreferencias);
		
		JLabel label_1 = new JLabel("Grado");
		label_1.setBounds(490, 76, 89, 14);
		frame.getContentPane().add(label_1);
		
		txtpreferencias = new JTextField();
		txtpreferencias.setText("100");
		txtpreferencias.setBounds(433, 48, 47, 20);
		frame.getContentPane().add(txtpreferencias);
		txtpreferencias.setColumns(10);
		
		txtgrado = new JTextField();
		txtgrado.setText("0");
		txtgrado.setColumns(10);
		txtgrado.setBounds(433, 73, 47, 20);
		frame.getContentPane().add(txtgrado);
		
		txtgenero = new JTextField();
		txtgenero.setText("0");
		txtgenero.setColumns(10);
		txtgenero.setBounds(433, 98, 47, 20);
		frame.getContentPane().add(txtgenero);
		
		JButton btnplantilla = new JButton("Crear Plantilla");
		btnplantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				    PrintWriter writer = new PrintWriter("ikasleak.txt", "UTF-8"); //para evitar problemas de igualdad, ikasleak.txt
				    writer.println("# FORMATO DEL FICHERO");
				    writer.println("#<nombe y apellidos>,<sexo(Hombre/Mujer)>,<centro(de la lista de centros disponibles)>,<euskera(true/false)>");
				    writer.println("#EJEMPLO:");
				    writer.println("Jon Arzelus Rodiguez,Informatika,Hombre,true");
				    writer.println("#La línea de arriba representa a un estudiante de Informática (se supone que el centro está añadido y tiene el nombre 'Informatika' en el programa) que sabe euskera y que es hombre.");
				    writer.println("#");
				    writer.println("#Las líneas que comienzan con # son comentarios (por lo tanto, 'Jon Arzelus Rodiguez' será añadido a la lista de alumnos si no se elimina)");
				    writer.println("#");
				    writer.println("#CENTROS DISPONIBLES: ");
				    for(Centro c:Cprincipal.getCentros())
				    	writer.println("#	"+c.getNombre());
				    writer.close();
				    Verror a = new Verror("Se ha creado el archivo 'Ikasleak.txt' en el directorio del ejecutable, '"+f.getAbsolutePath()+"' y en él está el esquema general del archivo y un input de ejemplo. Se recomienda usar un editor con reemplazo automático para facilitar la tarea.", "info");
				} catch (IOException e) {
				   Verror x = new Verror("Error desconocido (putada) al crear el archivo de plantilla","error"); //programando como es debido
				}
			}
		});
		btnplantilla.setBounds(312, 250, 168, 23);
		frame.getContentPane().add(btnplantilla);
	}
	
	public static boolean añadirAlumnosTXT(String nombre) {
		String fila;
		String[] partes;
		try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
		    while ((fila = br.readLine()) != null) {
		    	partes=fila.split(",");
		    	if(!(partes[0].charAt(0)=='#')) //si es una linea con comentarios no lo lee
		    		Cprincipal.addAlumnos(partes[0], partes[1], partes[2], Boolean.valueOf(partes[3]));
		    }
		    return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
