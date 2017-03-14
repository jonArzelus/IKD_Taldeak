package modelo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import vista.JTextAreaOutputStream;

public class Mprincipal {
	
	public static void realizarReparto(ArrayList<Alumno> alumnosLista, ArrayList<Centro> centrosLista, ArrayList<Necesidad> necesidadLista, Double pref, Double genero, Double grado) {
		//crear una consola para el output
				JTextArea textArea = new JTextArea (25, 80);

		        textArea.setEditable (false);

		        JFrame frame = new JFrame ("stdout");
		        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		        Container contentPane = frame.getContentPane ();
		        contentPane.setLayout (new BorderLayout ());
		        contentPane.add (
		            new JScrollPane (
		                textArea, 
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
		            BorderLayout.CENTER);
		        frame.pack ();
		        frame.setVisible (true);

		        JTextAreaOutputStream out = new JTextAreaOutputStream (textArea);
		        System.setOut (new PrintStream (out));
		        //fin de la consola
		        
		        //calcular el tamaño de los grupos
		        //int tamaño = 
		        System.out.println("Preferencias: "+pref+"\nGenero: "+genero+"\nGrado: "+grado);
	}

}
