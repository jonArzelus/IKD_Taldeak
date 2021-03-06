package modelo;

import java.util.ArrayList;

import controlador.Cprincipal;

public class Necesidad {

	//Alumno [] alumnos;
	//int nAlumnos;
	ArrayList<Alumno> alumnos;
	int indice;
	String nombre;
	boolean euskeraz;
	
	public Necesidad(int i, String nombre, boolean euskeraz) {
		//this.alumnos = new Alumno [Cprincipal.getNumeroAlumnos()/Cprincipal.getNumeroNecesidad()+1];
		//nAlumnos=0;
		this.alumnos=new ArrayList<Alumno>();
		indice = i;
		this.nombre = nombre;
		this.euskeraz=euskeraz;
	}
	
	public String toString() {
		if(this.euskeraz)
			return this.nombre+", Euskeraz >> "+Character.toString((char) (this.indice+97));
		else
			return this.nombre+", Gazteleraz >> "+Character.toString((char) (this.indice+97));
	}
	
	public void anadirAlumno (Alumno a){
		//alumnos[this.nAlumnos]=a;
		//this.nAlumnos++;
		alumnos.add(a);
	}
	
	public void imprimir()
	{
		ArrayList<Integer> pref = new ArrayList<Integer>();
		System.out.println(this.indice+"    "+this.nombre);
		for(int i=0;i<alumnos.size();i++) {
			System.out.print("	[");
			for(int p: this.alumnos.get(i).preferencias) {
				System.out.print(p+",");
				pref.add(p);
			}
			System.out.println("]");
			System.out.println("\t" + (i+1)+ "- " + this.alumnos.get(i).nombre + ", <" + pref.indexOf(this.indice)+">, euskera: "+this.alumnos.get(i).euskera);
			pref.clear();
		}
	}
}
