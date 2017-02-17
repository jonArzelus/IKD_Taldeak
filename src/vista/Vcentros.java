package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

import controlador.Cprincipal;
import modelo.Alumno;
import modelo.Centro;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Vcentros {

	private JFrame frmPanelDeAdministracin;
	private JTextField txtnombre;
	private static ArrayList<Alumno> listaAlumnos;
	private static ArrayList<Centro> listaCentros;
	private static DefaultListModel model;
	private JCheckBox chkanadir;
	private JCheckBox chkactualizar;
	private JCheckBox chkeliminar;

	/**
	 * Create the application.
	 */
	public Vcentros(ArrayList<Alumno> lista, ArrayList<Centro> lista1) {
		listaAlumnos = lista;
		listaCentros = lista1;
		initialize();
		frmPanelDeAdministracin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelDeAdministracin = new JFrame();
		frmPanelDeAdministracin.setTitle("Panel de administraci\u00F3n de centros");
		frmPanelDeAdministracin.setBounds(100, 100, 413, 288);
		frmPanelDeAdministracin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelDeAdministracin.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 213, 227);
		frmPanelDeAdministracin.getContentPane().add(scrollPane);
		
		JList list = new JList(getModel());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!list.isSelectionEmpty()) {
					txtnombre.setText(listaCentros.get(list.getSelectedIndex()).toString());
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!list.isSelectionEmpty()) {
					txtnombre.setText(listaCentros.get(list.getSelectedIndex()).toString());
				}
			}
		});*/
		scrollPane.setViewportView(list);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(233, 10, 157, 20);
		frmPanelDeAdministracin.getContentPane().add(txtnombre);
		txtnombre.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty()) {
				Centro x = (Centro) list.getSelectedValue();
				Cprincipal.deleteCentros(x.toString());
				listaCentros.remove(list.getSelectedIndex());
				actualizar();
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(265, 37, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnEliminar);
		
		chkeliminar = new JCheckBox("Eliminar");
		chkeliminar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chkeliminar.isSelected()) {
					chkactualizar.setSelected(false);
					chkanadir.setSelected(false);
					btnEliminar.setEnabled(true);
				} else {
					btnEliminar.setEnabled(false);
				}
			}
		});
		chkeliminar.setBounds(261, 67, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkeliminar);
		
		JButton btnanadir = new JButton("A\u00F1adir...");
		btnanadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnombre.getText().length()>0) {
					list.clearSelection();
					Cprincipal.addCentros(txtnombre.getText());
					//listaCentros.add(new Centro(txtnombre.getText()));
					actualizar();
				}
			}
		});
		btnanadir.setEnabled(false);
		btnanadir.setBounds(265, 93, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnanadir);
		
		chkanadir = new JCheckBox("Nuevo centro");
		chkanadir.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkanadir.isSelected()) {
					chkactualizar.setSelected(false);
					chkeliminar.setSelected(false);
					btnanadir.setEnabled(true);
				} else {
					btnanadir.setEnabled(false);
				}
			}
		});
		chkanadir.setBounds(261, 123, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkanadir);
		
		JButton btnactualizar = new JButton("Actualizar...");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cprincipal.updateCentros(txtnombre.getText(), listaCentros.get(list.getSelectedIndex()).toString());
				actualizar();
			}
		});
		btnactualizar.setEnabled(false);
		btnactualizar.setBounds(257, 149, 111, 23);
		frmPanelDeAdministracin.getContentPane().add(btnactualizar);
		
		chkactualizar = new JCheckBox("Actualizar");
		chkactualizar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkactualizar.isSelected()) {
					chkanadir.setSelected(false);
					chkeliminar.setSelected(false);
					btnactualizar.setEnabled(true);
				} else {
					btnactualizar.setEnabled(false);
				}
			}
		});
		chkactualizar.setBounds(261, 179, 97, 23);
		frmPanelDeAdministracin.getContentPane().add(chkactualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vprincipal a = new Vprincipal(listaAlumnos,listaCentros);
				frmPanelDeAdministracin.dispose();
			}
		});
		btnVolver.setBounds(298, 215, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnVolver);
	}
	
	private static DefaultListModel getModel() {
		model = new DefaultListModel();
		for(Centro x:listaCentros)
			model.addElement(x);
		//System.out.println(listaCentros.get(0).toString());
		return model;
	}
	
	private void actualizar() {
		Vcentros a =  new Vcentros(Cprincipal.getAlumnos(),Cprincipal.getCentros());
		this.frmPanelDeAdministracin.dispose();
	}
}
