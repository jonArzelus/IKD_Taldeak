package modelo;

public class Centro {

	Alumno [] alumnos;
	int [] exito;
	int nAlumnos;
	String nombre;

	public Centro(String n) {
		this.nombre = n;
		this.alumnos = new Alumno [20];
		this.exito = new int [9];
		nAlumnos=0;
	}
	
	public String toString() {
		return nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Alumno[] getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}
	public int[] getExito() {
		return exito;
	}
	public void setExito(int[] exito) {
		this.exito = exito;
	}
	public void anadirAlumno (Alumno a)
	{
		this.alumnos[nAlumnos]=a;
		this.nAlumnos++;
	}

	public void calcularExito (){
		int [] aux=new int [9];
		int i,k=0;
		int min=99999;
		//todo a 0
		for (i=0;i<9;i++)
			aux[i]=0;
		//suma
		for (i=0; i<nAlumnos;i++)
			for (int j=0;j<9;j++)
				aux[j]=aux[j]+this.alumnos[i].preferencias[j];
		//se simplifica todo a 1,2,3,4,5, es ximplex
		for (int w =0;w<9;w++)
		{
			for (i=0;i<9;i++)
			{
				if(aux[i]<min)
				{
					min=aux[i];
					k=i;
				}
			}
		this.exito[k]=w+1;
		aux[k]=9999;
		min=9999;
		}
		
	}
	void eliminarAlumno(int alumno)
	{
		if(alumno ==-1)
		{
			//main.imprimirRetos();
			this.imprimir();
		}
		Alumno a = this.alumnos[alumno];
		for(int j=alumno; j<this.nAlumnos-1;j++)
		{
			this.alumnos[j]=this.alumnos[j+1];
		}
		this.nAlumnos--;
	}
	//Comprueba si hay algún 1, luego algún 2...
	public int mejorReto(int reto)
	{
		int i = 0;
		int j = 1;
		for(j=1;j<=9;j++)
			for(i=0; i<this.nAlumnos;i++)
			{
				if(this.alumnos[i].preferencias[reto]==j && (this.alumnos[i].euskera || reto!=3))
					return i;
			}
		return -1;
	}
	
	
	public void imprimir (){
		if(this.nAlumnos>0){
		System.out.println(this.nombre + "\t\t Euskera \t Albaola \t Gureak \t MGI \t\t EGK \t\t Kalapie \t Lasalle \t Elkartu \t EHU");
		for (int i=0;i<this.nAlumnos;i++)
		{
			System.out.print(this.alumnos[i].nombre + "\t\t "+this.alumnos[i].euskera);
			for (int j=0; j<9; j++)
				System.out.print("\t\t   " + this.alumnos[i].preferencias[j]);
			System.out.println();
		}
		System.out.println();
		}
	}
	
	public int retoMenosPopular(){
		int reto = 0;
		int max = 0;
		
		for(int i=0;i<9;i++)
		{
			if(this.exito[i]>max)
			{
				max=this.exito[i];
				reto=i;
			}
		}
		return reto;
		
	}
}
