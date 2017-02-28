package modelo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controlador.Cprincipal;
import vista.JTextAreaOutputStream;

public class Mnagusia {
	
	public static void realizarReparto(ArrayList<Alumno> alumnosLista, ArrayList<Centro> centrosLista, ArrayList<Necesidad> necesidadLista) {
		
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
		
		Alumno[] alumnos = (Alumno[]) Arrays.copyOf(alumnosLista.toArray(), alumnosLista.size(),Alumno[].class);
		Centro[] centros = (Centro[]) Arrays.copyOf(centrosLista.toArray(), centrosLista.size(),Centro[].class);
		Necesidad[] retos = (Necesidad[]) Arrays.copyOf(necesidadLista.toArray(), necesidadLista.size(),Necesidad[].class);
		
		/*Necesidad once = new Necesidad(0, "Once", false);
		Necesidad saretuz = new Necesidad(1, "Saretuz", true); //EUSKARAZ
		Necesidad miramon = new Necesidad(2, "Miramon", true); //EUSKARAZ
		Necesidad bilbo = new Necesidad(3, "Bilbo", false);
		Necesidad emaus = new Necesidad(4, "Emaus", false);
		Necesidad albaola = new Necesidad(5, "Albaola", false);
		Necesidad kalapie = new Necesidad(0, "Kalapie", false);
		Necesidad sanMarcos = new Necesidad(0, "SanMarcos", false);
		Necesidad ehu = new Necesidad(0, "EHU", true); //EUSKARAZ
		
		Necesidad[] retos = {once, saretuz, miramon, bilbo, emaus, albaola, kalapie, sanMarcos, ehu};*/
		
		for(int i=0;i<alumnos.length;i++) {
			if(alumnos[i].getCentro().equals(centros[0].getNombre())) //Informatika
				centros[0].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[1].getNombre())) //Zuzenbidea
				centros[1].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[2].getNombre())) //Arkitektura
				centros[2].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[3].getNombre())) //Pedagogia
				centros[3].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[4].getNombre())) //Politeknika
				centros[4].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[5].getNombre())) //Erizanitza
				centros[5].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[6].getNombre())) //Gizarte Hezkuntza
				centros[6].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[7].getNombre())) //Psikologia
				centros[7].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[8].getNombre())) //Magisteritza
				centros[8].anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals(centros[9].getNombre())) //Enpresa
				centros[9].anadirAlumno(alumnos[i]);
		}
		
		for(Centro c: centros)
			c.calcularExito();
		
		boolean amaitua = false;
		int nLoop = 1;
		int alumnosMetidos = 0;
		int n8 = 0; //numero de retos con gente
		
		//HORA DE CICLAR!!!
		
		
		while(nLoop < 100) {
			
			for(int i=0; i<centros.length; i++) { //una vez por centro
				
				int pop = centros[i].retoMenosPopular(); //indice del reto menos popular
				int min = retos.length+1; //valor mayor al numero de retos
				int alum = 0; //variable para escoger el alumno para el reto
				
				//buscar el alumno a meter del centro en el reto pop
				for(int j = 0; j < centros[i].nAlumnos; j++) {
						System.out.println(centros[i].getAlumnos()[j].nombre);
						if(centros[i].getAlumnos()[j].getPreferencias()[pop] < min) {
							min = centros[i].getAlumnos()[j].getPreferencias()[pop];
							alum = j;
						}
				}
				
				if((retos[pop].nAlumnos < alumnos.length/retos.length) && (n8 < 6) && (centros[i].getAlumnos()[alum] != null)) {//begiratu zenbakiak
					retos[pop].anadirAlumno(centros[i].getAlumnos()[alum]);
					if(retos[pop].nAlumnos >= alumnos.length/retos.length){
						n8++;
					}
					for(int j=alum; j<centros[i].nAlumnos; j++) { //elimina el alumno del array y corre el array
						centros[i].alumnos[j] = centros[i].alumnos[j+1];
					}
					centros[i].nAlumnos = centros[i].nAlumnos-1;
					alumnosMetidos++;
				}
				
				centros[i].exito[pop] = 0 - ((retos.length+1)-centros[i].exito[pop]); //asigna no popularidad negativa, es decir, hace el reto popular para no repetirlo
				if(alumnosMetidos == alumnos.length){
					break;
				}
				
			}
			nLoop++;
			System.out.println(alumnosMetidos);
		}
		
		for(int i=0; i<centros.length; i++){
			for(int j = 0; j < centros[i].nAlumnos; j++){
				for(int k=0; k < retos.length; k++){
					int pref2 = centros[i].getAlumnos()[j].preferido(k);
					if(retos[pref2].nAlumnos < alumnos.length/retos.length){
						retos[pref2].anadirAlumno(centros[i].getAlumnos()[j]);
						alumnosMetidos++;
						break;
					}
				}
			}
		}
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(alumnosMetidos + "-----------------------------");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		for(int i=0; i<9; i++){
			System.out.println(retos[i].nombre);
			System.out.println("");
			for(int j = 0; j<retos[i].nAlumnos; j++){
				if(retos[i].alumnos[j] != null){
					System.out.println(retos[i].alumnos[j].nombre + "  " + retos[i].alumnos[j].getCentro() + "  " + retos[i].alumnos[j].getPreferencias()[i]);
				}else{System.out.println("error");}
			}
			System.out.println("");
			System.out.println("");
		}
		
	}
}
