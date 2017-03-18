package vista;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
//import javax.swing.SwingUtilities;

import controlador.Cprincipal;
import modelo.Alumno;
import modelo.Centro;
import modelo.Necesidad;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class Valumnos {

	private JFrame frmPanelDeAdministracin;
	private static ArrayList<Alumno> listaAlumnos;
	private static ArrayList<Centro> listaCentros;
	private static DefaultListModel model;
	private JTextField txtnombre;
	private JTextField txtcentro;
	private JTextField txtpreferencias;
	private JCheckBox chkeuskera;
	private JCheckBox chkeliminar;
	private JCheckBox chkactualizar;
	private JCheckBox chknuevo;
	private JComboBox combocentros;
	private JComboBox combogenero;

	/**
	 * Create the application.
	 * @param listaCentros 
	 */
	public Valumnos(ArrayList<Alumno> lista, ArrayList<Centro> lista1) {
		listaAlumnos=lista;
		listaCentros=lista1;
		initialize();
		frmPanelDeAdministracin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelDeAdministracin = new JFrame();
		frmPanelDeAdministracin.setTitle("Panel de administraci\u00F3n de alumnos");
		frmPanelDeAdministracin.setBounds(100, 100, 783, 511);
		frmPanelDeAdministracin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelDeAdministracin.getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vprincipal a = new Vprincipal(listaAlumnos,listaCentros);
				frmPanelDeAdministracin.dispose();
			}
		});
		btnVolver.setBounds(668, 438, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 428, 450);
		frmPanelDeAdministracin.getContentPane().add(scrollPane);
		
		JList list = new JList(getModel());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int ind = list.getSelectedIndex();
				txtnombre.setText(listaAlumnos.get(ind).getNombre());
				txtcentro.setText(listaAlumnos.get(ind).getCentro());
				combocentros.setSelectedItem(((Alumno)list.getSelectedValue()).getCentro());
				chkeuskera.setSelected(listaAlumnos.get(ind).getEuskera());
				/*String pref = "";
				for(int i:listaAlumnos.get(ind).getPreferencias()) {
					char a = (char) ((char)i+97);
					//System.out.println(a);
					pref.concat(Character.toString(a));
				}*/
				//txtpreferencias.setText(listaAlumnos.get(ind).getPreferencias().toString());
				//System.out.println(listaAlumnos.get(ind).getPreferenciasString());
				txtpreferencias.setText(listaAlumnos.get(ind).getPreferenciasString());
			}
		});

		scrollPane.setViewportView(list);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!list.isSelectionEmpty()) {
					Alumno x = (Alumno) list.getSelectedValue();
					Cprincipal.deleteAlumnos(x.getNombre());
					//listaAlumnos.remove(list.getSelectedIndex());
					actualizar();
				}
			}
		});
		btnEliminar.setBounds(448, 144, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnEliminar);
		
		JButton btnAnadir = new JButton("A\u00F1adir...");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnombre.getText().length()>0 && combocentros.getSelectedIndex()>-1) {
					Cprincipal.addAlumnos(txtnombre.getText(), combocentros.getSelectedItem().toString(), combogenero.getSelectedItem().toString(),chkeuskera.isSelected());
					actualizar();
				}
			}
		});
		btnAnadir.setEnabled(false);
		btnAnadir.setBounds(547, 144, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnAnadir);
		
		JButton btnActualizar = new JButton("Actualizar...");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnombre.getText().length()>0 && txtpreferencias.getText().length()==Cprincipal.getNumeroNecesidad()) {
					Cprincipal.updateAlumnos(txtnombre.getText(), txtpreferencias.getText());
					actualizar();
				}
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(648, 144, 109, 23);
		frmPanelDeAdministracin.getContentPane().add(btnActualizar);
		
		txtnombre = new JTextField();
		txtnombre.setEditable(false);
		txtnombre.setBounds(448, 29, 309, 20);
		frmPanelDeAdministracin.getContentPane().add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(448, 11, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblnombre);
		
		JLabel lblCentro = new JLabel("Centro");
		lblCentro.setBounds(448, 52, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblCentro);
		
		txtcentro = new JTextField();
		txtcentro.setEditable(false);
		txtcentro.setColumns(10);
		txtcentro.setBounds(448, 70, 173, 20);
		frmPanelDeAdministracin.getContentPane().add(txtcentro);
		
		chkeuskera = new JCheckBox("Euskera");
		chkeuskera.setEnabled(false);
		chkeuskera.setBounds(637, 69, 109, 23);
		frmPanelDeAdministracin.getContentPane().add(chkeuskera);
		
		txtpreferencias = new JTextField();
		txtpreferencias.setEditable(false);
		txtpreferencias.setColumns(10);
		txtpreferencias.setBounds(448, 113, 173, 20);
		frmPanelDeAdministracin.getContentPane().add(txtpreferencias);
		
		JLabel lblpreferencias = new JLabel("Preferencias");
		lblpreferencias.setBounds(448, 95, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblpreferencias);
		
		chknuevo = new JCheckBox("Nuevo alumno");
		chknuevo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chknuevo.isSelected()) {
					chkactualizar.setSelected(false);
					chkeliminar.setSelected(false);
					btnAnadir.setEnabled(true);
					combocentros.setVisible(true);
					txtcentro.setVisible(false);
					txtnombre.setEditable(true);
					//txtpreferencias.setEditable(true);
					chkeuskera.setEnabled(true);
					combogenero.setEnabled(true);
				} else {
					btnAnadir.setEnabled(false);
					combocentros.setVisible(false);
					txtcentro.setVisible(true);
					txtnombre.setEditable(false);
					//txtpreferencias.setEditable(false);
					chkeuskera.setEnabled(false);
					combogenero.setEnabled(false);
				}
			}
		});
		chknuevo.setBounds(547, 174, 109, 23);
		frmPanelDeAdministracin.getContentPane().add(chknuevo);
		
		chkeliminar = new JCheckBox("Eliminar");
		chkeliminar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkeliminar.isSelected()) {
					chkactualizar.setSelected(false);
					chknuevo.setSelected(false);
					btnEliminar.setEnabled(true);
				} else
					btnEliminar.setEnabled(false);
			}
		});
		chkeliminar.setBounds(444, 174, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkeliminar);
		
		chkactualizar = new JCheckBox("Actualizar");
		chkactualizar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkactualizar.isSelected()) {
					chkeliminar.setSelected(false);
					chknuevo.setSelected(false);
					btnActualizar.setEnabled(true);
					txtpreferencias.setEditable(true);
				} else {
					btnActualizar.setEnabled(false);
					txtpreferencias.setEditable(false);
				}
			}
		});
		chkactualizar.setBounds(658, 174, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkactualizar);
		
		combocentros = new JComboBox(listaCentros.toArray());
		combocentros.setBounds(448, 70, 173, 20);
		combocentros.setVisible(false);
		frmPanelDeAdministracin.getContentPane().add(combocentros);
		
		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(637, 95, 89, 14);
		frmPanelDeAdministracin.getContentPane().add(lblGnero);
		
		String[] gen = {"Hombre","Mujer"};
		combogenero = new JComboBox(gen);
		combogenero.setEnabled(false);
		combogenero.setBounds(637, 113, 120, 20);
		frmPanelDeAdministracin.getContentPane().add(combogenero);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(448, 204, 309, 223);
		frmPanelDeAdministracin.getContentPane().add(scrollPane_1);
		
		JList list_retos = new JList(getModelNecesidad());
		list_retos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_retos);
		
		JButton btnexportar = new JButton("Exportar todo a lista.txt");
		btnexportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Valumnos.exportarLista();
			}
		});
		btnexportar.setBounds(448, 438, 188, 23);
		frmPanelDeAdministracin.getContentPane().add(btnexportar);
	}
	
	private static DefaultListModel getModel() {
		model = new DefaultListModel();
		for(Alumno x:listaAlumnos)
			model.addElement(x);
		//System.out.println(listaAlumnos.get(0).toString());
		return model;
	}
	
	private static DefaultListModel getModelNecesidad() {
		model = new DefaultListModel();
		for(Necesidad x:Cprincipal.getNecesidad())
			model.addElement(x);
		//System.out.println(listaAlumnos.get(0).toString());
		return model;
	}
	
	private void actualizar() {
		Valumnos a =  new Valumnos(Cprincipal.getAlumnos(),Cprincipal.getCentros());
		this.frmPanelDeAdministracin.dispose();
	}
	
	public static void exportarLista() {
		//String fila;
		//String[] partes;
		try (BufferedWriter br = new BufferedWriter(new FileWriter("lista.txt"))) {
			for(int i=0;i<listaAlumnos.size();i++) {
				br.write(Integer.toString(i+1)+" - ");
				br.write(listaAlumnos.get(i).getNombre());
				br.write(" ("+listaAlumnos.get(i).getPreferenciasString()+")");
				br.newLine();
				for(int k=0;k<listaAlumnos.get(i).getPreferencias().length;k++) {
					br.write("    ");
					br.write(Integer.toString(k+1)+" - "+Cprincipal.getNecesidad().get(listaAlumnos.get(i).getPreferencias()[k]).toString());
					br.newLine();
				}
				br.newLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
