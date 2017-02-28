package modelo;

import controlador.Cprincipal;

public class Necesidad {

	Alumno [] alumnos;
	int nAlumnos;
	int indice;
	String nombre;
	boolean euskeraz;
	
	public Necesidad(int i, String nombre, boolean euskeraz) {
		this.alumnos = new Alumno [Cprincipal.getNumeroAlumnos()/Cprincipal.getNumeroNecesidad()+1];
		nAlumnos=0;
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
		alumnos[this.nAlumnos]=a;
		this.nAlumnos++;
	}
	
	public void imprimir ()
	{
		System.out.println(this.indice+"    "+this.nombre);
		for(int i=0;i<nAlumnos;i++)
		{
			System.out.println("\t" + (i+1)+ " " + this.alumnos[i].nombre + " " + this.alumnos[i].preferencias[indice]+" "+this.alumnos[i].euskera);
		}
	}
}
