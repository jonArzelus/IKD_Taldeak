package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Alumno {

	String nombre;
	int [] preferencias;
	String centro;
	boolean euskera;
	
	public String toString() {
		if(this.euskera)
			return this.nombre+", "+this.centro+", "+"Euskera";
		else
			return this.nombre+", "+this.centro+", "+"Gaztelera";
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
	public void setPreferencias(int[] preferencias) {
		this.preferencias = preferencias;
	}
	public Alumno(String nombre, int a, int b, int c, int d, int e, int f, int g, int h, int i, String centro, boolean euskera) {
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
	}
	
	public Alumno(String nombre, String centro, boolean euskera) {
		this.nombre = nombre;
		this.centro=centro;
		this.euskera=euskera;
		ArrayList<Integer> pref = new ArrayList<Integer>();
		pref.add(1);
		pref.add(2);
		pref.add(3);
		pref.add(4);
		pref.add(5);
		pref.add(6);
		pref.add(7);
		pref.add(8);
		pref.add(9);
		Collections.shuffle(pref);
		int[] pref2 = {pref.get(0),pref.get(1),pref.get(2),pref.get(3),pref.get(4),pref.get(5),pref.get(6),pref.get(7),pref.get(8)};
		this.preferencias = pref2;
		/*Random  rnd = new Random();
		Vector <Integer> aux = new Vector<Integer>();
		aux.add(1);
		aux.add(2);
		aux.add(3);
		aux.add(4);
		aux.add(5);
		aux.add(6);
		aux.add(7);
		aux.add(8);
		this.nombre = nombre;
		this.preferencias= new int[9];
		int a = rnd.nextInt() % 9;
		this.preferencias[0]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 8;
		this.preferencias[1]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 7;
		this.preferencias[2]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 6;
		this.preferencias[3]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 5;
		this.preferencias[4]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 4;
		this.preferencias[5]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 3;
		this.preferencias[6]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		a = rnd.nextInt() % 2;
		this.preferencias[7]=aux.get(Math.abs(a));
		aux.remove(Math.abs(a));
		this.preferencias[8]=aux.get(0);
		this.centro=centro;
		this.euskera=euskera;*/
	}
	
	public void ajustarAlumno(){
		if(!euskera)
			this.preferencias[3]=9999;
	}
	
	public int preferido(int a){
		for(int i=0;i<9;i++)
		{
			if(this.preferencias[i]==a)
				return i;
		}
		return -1;
	}
	
}
