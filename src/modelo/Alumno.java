package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import controlador.Cprincipal;

public class Alumno {

	String nombre;
	int [] preferencias;
	String centro;
	boolean euskera;
	String genero;
	
	public String toString() {
		if(this.euskera)
			return this.nombre+", "+this.centro+", "+"Euskera, "+genero+", "+this.getPreferenciasString();
		else
			return this.nombre+", "+this.centro+", "+"Castellano, "+genero+", "+this.getPreferenciasString();
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean getEuskera() {
		return euskera;
	}
	public String getCentro() {
		return centro;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int[] getPreferencias() {
		return preferencias;
	}
	/**Devuelve un string con las preferencias del alumno*/
	public String getPreferenciasString(){
		String pref = "";
		int x;
		//System.out.println();
		for(int i=0;i<this.preferencias.length;i++) {
			x=this.preferencias[i];
			x+=97;
			pref=pref.concat(Character.toString ((char) x));
			//System.out.print(x);
			//System.out.println((char) x);
		}
		return pref;
	}
	public void setPreferencias(int[] preferencias) {
		this.preferencias = preferencias;
	}
	/*public Alumno(String nombre, int a, int b, int c, int d, int e, int f, int g, int h, int i, String centro, boolean euskera, String genero) {
		this.nombre = nombre;
		this.preferencias= new int[9];
		this.preferencias[0]=a;
		this.preferencias[1]=b;
		this.preferencias[2]=c;
		this.preferencias[3]=d;
		this.preferencias[4]=e;
		this.preferencias[5]=f;
		this.preferencias[6]=g;
		this.preferencias[7]=h;
		this.preferencias[8]=i;
		this.centro=centro;
		this.euskera=euskera;
		this.genero=genero; //nuevo
	}*/
	
	/**Nuevo metodo para crear alumnos con preferencias aleatorias*/
	public Alumno(String nombre, String centro, boolean euskera, String genero) {
		this.nombre = nombre;
		this.centro=centro;
		this.euskera=euskera;
		this.genero=genero;
		ArrayList<Integer> pref = new ArrayList<Integer>();
		for(int i=0;i<Cprincipal.getNumeroNecesidad();i++)
			pref.add(i);
		Collections.shuffle(pref);
		int[] pref2 = new int[pref.size()];
		for(int i=0;i<pref.size();i++) {
			pref2[i]=pref.get(i);
			System.out.println(pref2[i]);
		}
		this.preferencias = pref2;
	}
	
	/**Nuevo metodo para crear alumnos con las preferencias*/
	public Alumno(String nombre, String preferencias, String centro, boolean euskera, String genero) {
		this.nombre = nombre;
		this.centro=centro;
		this.euskera=euskera;
		this.genero=genero;
		int x;
		int[] pref = new int[Cprincipal.getNumeroNecesidad()];
		for(int i=0;i<pref.length;i++) {
			x=(int)preferencias.charAt(i);
			x-=97;
			pref[i]=x;
		}
		this.preferencias = pref;
	}
	
	public void ajustarAlumno(){
		if(!euskera)
			this.preferencias[3]=9999;
	}
	
	public int preferido(int a){
		for(int i=0;i<Cprincipal.getNumeroNecesidad();i++)
		{
			if(this.preferencias[i]==a)
				return i;
		}
		return -1;
	}
	
}
