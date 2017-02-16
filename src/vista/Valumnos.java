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

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Valumnos {

	private JFrame frmPanelDeAdministracin;
	private static ArrayList<Alumno> listaAlumnos;
	private static DefaultListModel model;
	private JTextField txtnombre;
	private JTextField txtcentro;
	private JTextField txtpreferencias;
	private JCheckBox chkeuskera;
	private JCheckBox chkeliminar;
	private JCheckBox chkactualizar;
	private JCheckBox chknuevo;

	/**
	 * Create the application.
	 */
	public Valumnos(ArrayList<Alumno> lista) {
		listaAlumnos=lista;
		initialize();
		frmPanelDeAdministracin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelDeAdministracin = new JFrame();
		frmPanelDeAdministracin.setTitle("Panel de administraci\u00F3n de alumnos");
		frmPanelDeAdministracin.setBounds(100, 100, 662, 415);
		frmPanelDeAdministracin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelDeAdministracin.getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vprincipal a = new Vprincipal(listaAlumnos);
				frmPanelDeAdministracin.dispose();
			}
		});
		btnVolver.setBounds(547, 342, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 307, 354);
		frmPanelDeAdministracin.getContentPane().add(scrollPane);
		
		JList list = new JList(getModel());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ind = list.getSelectedIndex();
				txtnombre.setText(listaAlumnos.get(ind).getNombre());
				txtcentro.setText(listaAlumnos.get(ind).getCentro());
				chkeuskera.setSelected(listaAlumnos.get(ind).getEuskera());
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
					listaAlumnos.remove(list.getSelectedIndex());
					actualizar();
				}
			}
		});
		btnEliminar.setBounds(327, 146, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnEliminar);
		
		JButton btnAnadir = new JButton("A\u00F1adir...");
		btnAnadir.setEnabled(false);
		btnAnadir.setBounds(426, 146, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnAnadir);
		
		JButton btnActualizar = new JButton("Actualizar...");
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(527, 146, 109, 23);
		frmPanelDeAdministracin.getContentPane().add(btnActualizar);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(327, 31, 309, 20);
		frmPanelDeAdministracin.getContentPane().add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(327, 13, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblnombre);
		
		JLabel lblCentro = new JLabel("Centro");
		lblCentro.setBounds(327, 54, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblCentro);
		
		txtcentro = new JTextField();
		txtcentro.setColumns(10);
		txtcentro.setBounds(327, 72, 173, 20);
		frmPanelDeAdministracin.getContentPane().add(txtcentro);
		
		chkeuskera = new JCheckBox("Euskera");
		chkeuskera.setBounds(516, 71, 109, 23);
		frmPanelDeAdministracin.getContentPane().add(chkeuskera);
		
		txtpreferencias = new JTextField();
		txtpreferencias.setColumns(10);
		txtpreferencias.setBounds(327, 115, 309, 20);
		frmPanelDeAdministracin.getContentPane().add(txtpreferencias);
		
		JLabel lblpreferencias = new JLabel("Preferencias");
		lblpreferencias.setBounds(327, 97, 137, 14);
		frmPanelDeAdministracin.getContentPane().add(lblpreferencias);
		
		chknuevo = new JCheckBox("Nuevo alumno");
		chknuevo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chknuevo.isSelected()) {
					chkactualizar.setSelected(false);
					chkeliminar.setSelected(false);
					btnAnadir.setEnabled(true);
				} else
					btnAnadir.setEnabled(false);
			}
		});
		chknuevo.setBounds(426, 176, 109, 23);
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
		chkeliminar.setBounds(323, 176, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkeliminar);
		
		chkactualizar = new JCheckBox("Actualizar");
		chkactualizar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkactualizar.isSelected()) {
					chkeliminar.setSelected(false);
					chknuevo.setSelected(false);
					btnActualizar.setEnabled(true);
				} else
					btnActualizar.setEnabled(false);
			}
		});
		chkactualizar.setBounds(537, 176, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkactualizar);
	}
	
	private static DefaultListModel getModel() {
		model = new DefaultListModel();
		for(Alumno x:listaAlumnos)
			model.addElement(x);
		System.out.println(listaAlumnos.get(0).toString());
		return model;
	}
	
	private void actualizar() {
		Valumnos a =  new Valumnos(listaAlumnos);
		this.frmPanelDeAdministracin.dispose();
	}
}
