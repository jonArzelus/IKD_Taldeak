package vista;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import modelo.Alumno;
import modelo.Necesidad;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import controlador.Cprincipal;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vnecesidad {

	private JFrame frmPanelDeAdministracin;
	private static ArrayList<Necesidad> listaNecesidad;
	private static DefaultListModel<Necesidad> model;

	/**
	 * Create the application.
	 */
	public Vnecesidad(ArrayList<Necesidad> lista) {
		listaNecesidad=lista;
		initialize();
		frmPanelDeAdministracin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelDeAdministracin = new JFrame();
		frmPanelDeAdministracin.setTitle("Panel de administraci\u00F3n de necesidades");
		frmPanelDeAdministracin.setBounds(100, 100, 450, 300);
		frmPanelDeAdministracin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelDeAdministracin.getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vprincipal a = new Vprincipal(Cprincipal.getAlumnos(), Cprincipal.getCentros());
				frmPanelDeAdministracin.dispose();
			}
		});
		btnVolver.setBounds(335, 227, 89, 23);
		frmPanelDeAdministracin.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 232, 239);
		frmPanelDeAdministracin.getContentPane().add(scrollPane);
		
		JList list = new JList(getModel());
		scrollPane.setViewportView(list);
	}
	
	private static DefaultListModel getModel() {
		model = new DefaultListModel();
		for(Necesidad x:listaNecesidad)
			model.addElement(x);
		//System.out.println(listaAlumnos.get(0).toString());
		return model;
	}
}
