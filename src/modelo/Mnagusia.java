package modelo;

import java.util.ArrayList;
import java.util.Arrays;

import controlador.Cprincipal;

public class Mnagusia {

	public Mnagusia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void realizarReparto(ArrayList<Alumno> alumnosLista, ArrayList<Centro> centrosLista) {
		
		Alumno[] alumnos = (Alumno[]) Arrays.copyOf(alumnosLista.toArray(), alumnosLista.size(),Alumno[].class);
		Centro[] centros = (Centro[]) Arrays.copyOf(centrosLista.toArray(), centrosLista.size(),Centro[].class);
		
		Necesidad once = new Necesidad(0, "Once");
		Necesidad saretuz = new Necesidad(1, "Saretuz"); //EUSKARAZ
		Necesidad miramon = new Necesidad(2, "Miramon"); //EUSKARAZ
		Necesidad bilbo = new Necesidad(3, "Bilbo");
		Necesidad emaus = new Necesidad(4, "Emaus");
		Necesidad albaola = new Necesidad(5, "Albaola");
		Necesidad kalapie = new Necesidad(0, "Kalapie");
		Necesidad sanMarcos = new Necesidad(0, "SanMarcos");
		Necesidad ehu = new Necesidad(0, "EHU"); //EUSKARAZ
		
		Necesidad[] retos = {once, saretuz, miramon, bilbo, emaus, albaola, kalapie, sanMarcos, ehu};
		
		for(int i=0;i<alumnos.length;i++)
		{
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
		int alumnosMetidos = 2;
		int n8 = 0;
		
		//HORA DE CICLAR!!!
		
		
		while(nLoop < 100){
			
			for(int i=0; i<8; i++){
				
				int pop = centros[i].retoMenosPopular();
				int max = 11;
				int alum = 0;
				
				
				for(int j = 0; j < centros[i].nAlumnos; j++){
						System.out.println(centros[i].getAlumnos()[j].nombre);
						if(centros[i].getAlumnos()[j].getPreferencias()[pop] < max){
							max = centros[i].getAlumnos()[j].getPreferencias()[pop];
							alum = j;
						}
				}
				
				
				if((retos[pop].nAlumnos < 8) && (n8 < 6) && (centros[i].getAlumnos()[alum] != null)){
					retos[pop].anadirAlumno(centros[i].getAlumnos()[alum]);
					if(retos[pop].nAlumnos == 8){
						n8++;
					}
					for(int j=alum; j<centros[i].nAlumnos; j++){
						centros[i].alumnos[j] = centros[i].alumnos[j+1];
					}
					centros[i].nAlumnos = centros[i].nAlumnos-1;
					alumnosMetidos++;
				}
				
				centros[i].exito[pop] = 0 - (10-centros[i].exito[pop]);
				if(alumnosMetidos == 69){
					break;
				}
				
			}
			nLoop++;
			System.out.println(alumnosMetidos);
		}
		
		for(int i=0; i<8; i++){
			for(int j = 0; j < centros[i].nAlumnos; j++){
				for(int k=1; k < 10; k++){
					int pref2 = centros[i].getAlumnos()[j].preferido(k);
					if(retos[pref2].nAlumnos < 8){
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
