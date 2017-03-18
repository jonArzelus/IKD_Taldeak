package modelo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

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
		        
		        //conseguir los datos básicos sobre los retos
		        int tgrupos=(alumnosLista.size()/necesidadLista.size())+1; //el tamaño maximo de los grupos
		        int tgrados=centrosLista.size(); //la cantidad de grados
		        int tmujer; //la cantidad de mujeres en el modulo
		        int thombre; //la cantidad de hombres en el grado
		        
		        //crear un array para calcular los coeficientes de los retos por cada alumno
		        ArrayList<ArrayList<Double>> lista = new ArrayList<ArrayList<Double>>(alumnosLista.size());
		        for(int i=0;i<alumnosLista.size();i++) {
		        	lista.add(new ArrayList<Double>(necesidadLista.size()));
		        	for(int k=0;k<lista.get(i).size();k++)
		        		lista.get(i).set(k,new Double(0.0));
		        }
		        
		        /**EL REPARTO*/
		        
		        Collections.shuffle(alumnosLista); //poner un factor aleatorio en el reparto
		        
		        for(int x=0;x<alumnosLista.size();x++) {
			        //calcular los coeficientes para todos los retos y todos los alumnos
			        Double coeficiente=0.0;
			        for(int i=0;i<alumnosLista.size();i++) {
			        	for(int k=0;k<necesidadLista.size();k++) {
			        		coeficiente=((10.0/necesidadLista.size())*(alumnosLista.get(i).getPreferencias()[k]+1))*pref;
			        		lista.get(i).set(k, coeficiente);
			        	}
			        }
			        
			        //meter un alumno en cada reto
			        int max; //el alumno optimo para el reto actual (i)
			        for(int i=0;i<necesidadLista.size();i++) {
			        	max=0;
			        	for(int k=0;k<lista.size();k++) {
			        		if(Double.valueOf(lista.get(k).get(i).toString())>Double.valueOf(lista.get(max).get(i).toString()))
			        				max=k;
			        	}
			        	necesidadLista.get(i).anadirAlumno(alumnosLista.get(max));
			        	alumnosLista.remove(max);
			        	lista.remove(max);
			        }
		        }
		        System.out.println(lista.toString());
		        
	}

}
