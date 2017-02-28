package modelo;


public class Nagusia {
	
	//OBJEKTUAK DEKLARATU
	/*
	static Necesidad once = new Necesidad(0, "Once");
	static Necesidad saretuz = new Necesidad(1, "Saretuz"); //EUSKARAZ
	static Necesidad miramon = new Necesidad(2, "Miramon"); //EUSKARAZ
	static Necesidad bilbo = new Necesidad(3, "Bilbo");
	static Necesidad emaus = new Necesidad(4, "Emaus");
	static Necesidad albaola = new Necesidad(5, "Albaola");
	static Necesidad kalapie = new Necesidad(0, "Kalapie");
	static Necesidad sanMarcos = new Necesidad(0, "SanMarcos");
	static Necesidad ehu = new Necesidad(0, "EHU"); //EUSKARAZ
	
	static Necesidad[] retos = {once, saretuz, miramon, bilbo, emaus, albaola, kalapie, sanMarcos, ehu};
	
	public static void main(String [] args) {
		
		Centro informatika = new Centro("Informatika");
		Centro zuzenbidea = new Centro("Zuzenbidea");
		Centro arkitektura = new Centro("Arkitektura");
		Centro pedagogia = new Centro("Pedagogia");
		Centro politeknika = new Centro("Politeknika"); //Oraingon zehaztu gabe
		Centro erizaintza = new Centro("Erizanitza");
		Centro gizarteHezkuntza = new Centro("Gizarte Hezkuntza");
		Centro psikologia = new Centro("Psikologia");
		Centro magisteritza = new Centro("Magisteritza");
		Centro enpresa = new Centro("Enpresa");
		
		//PSIKOLOGIAKO IKASLEAK
		
		Alumno a01 = new Alumno("Saioa Yeregui", "Psikologia", true);
		Alumno a02 = new Alumno("Maider Zalba", "Psikologia", true);
		Alumno a03 = new Alumno("Amaia Ulacia Arana", "Psikologia", true);
		Alumno a04 = new Alumno("Paula Villapun Perez", "Psikologia", true);
		Alumno a05 = new Alumno("Haizea Perez de Arriluzea", "Psikologia", true);
		Alumno a06 = new Alumno("Maria Dominguez Solaguren", "Psikologia", true);
		Alumno a07 = new Alumno("Iris Torrego Fernandez", "Psikologia", true);;
		Alumno a08 = new Alumno("Irati Mañeru Unsuain", "Psikologia", true);
		Alumno a09 = new Alumno("Ane Cabero Roteta", "Psikologia", true);
		Alumno a10 = new Alumno("Ainhoa Leon Arrieta", "Psikologia", true);
		Alumno a11 = new Alumno("Maitane Pancorbo Martinez", "Psikologia", true);
		Alumno a12 = new Alumno("Amaiur Olarza", "Psikologia", true);
		Alumno a13 = new Alumno("Beatriz Guesalana Macazaga", "Psikologia", true);
		Alumno a14 = new Alumno("Javier San Sebastian", "Psikologia", true);
		Alumno a15 = new Alumno("Nahikari Diosdado Bua", "Psikologia", true);
		Alumno a16 = new Alumno("Txomin Cenarruzabeitia Echeverria", "Psikologia", true);
		Alumno a17 = new Alumno("Julen Cruz Quiñones", "Psikologia", true);
		Alumno a18 = new Alumno("Lorea Barkaiztegi Beguiristain", "Psikologia", true);
		
		//INFORMATIKAKO IKASLEAK
		
		Alumno a19 = new Alumno("Ruben Crispin de la Cruz", "Informatika", false);
		Alumno a20 = new Alumno("Silvia Garcia Garcia", "Informatika", false);
		Alumno a21 = new Alumno("Marina Acosta Ruiz de Loizaga", "Informatika", false);
		Alumno a22 = new Alumno("Joseba Carral del Castillo", "Informatika", false);
		Alumno a23 = new Alumno("Enya Goñi Maganto", "Informatika", false);
		Alumno a24 = new Alumno("Alain Collazo", "Informatika", false);
		Alumno a25 = new Alumno("Jon Arzelus Rodriguez", "Informatika", true);
		Alumno a26 = new Alumno("Iñaki Cortajarena Azpiroz", "Informatika", true);
		Alumno a27 = new Alumno("Josu Salinas Colina", "Informatika", true);
		Alumno a28 = new Alumno("Oihan Arroyo Arrizabalaga", "Informatika", true);
		Alumno a29 = new Alumno("Aitor Legarburu Rodriguez", "Informatika", true);
		Alumno a30 = new Alumno("Aitzol Elu Etxano", "Informatika", true);
		Alumno a31 = new Alumno("Urtzi Otamendi Etxabe", "Informatika", true);
		Alumno a32 = new Alumno("Julen Aristondo", "Informatika", true);
		Alumno a33 = new Alumno("Aitor Saiz Telleria", "Informatika", true);
		
		//ENPRESAKO IKASLEAK
		
		Alumno a34 = new Alumno("Monika Aizpitarte Garmendia", "Enpresa", true);
		Alumno a35 = new Alumno("Ander Alcedo Odriozola", "Enpresa", true);
		Alumno a36 = new Alumno("Maialen Arambarri Illarramendi", "Enpresa", true);
		Alumno a37 = new Alumno("Ainhoa Ayarza Irizar", "Enpresa", true);
		Alumno a38 = new Alumno("Vanesa Benitez Bermejo", "Enpresa", true);
		Alumno a39 = new Alumno("Paula Andrea Cordoba Puente", "Enpresa", true);
		Alumno a40 = new Alumno("Jon Flores Elizegui", "Enpresa", true);
		Alumno a41 = new Alumno("Libe Galdos Urbizu", "Enpresa", true);
		Alumno a42 = new Alumno("Xabier Mikelarena Urdangarin", "Enpresa", true);
		Alumno a43 = new Alumno("Ane Urquia Ormazabal", "Enpresa", true);
		
		//PEDAGOGIAKO IKASLEAK
		
		Alumno a44 = new Alumno("Danae Gonzalez Garcia", "Pedagogia", true);
		Alumno a45 = new Alumno("Vanesa Lancha Villamayor", "Pedagogia", true);
		Alumno a46 = new Alumno("Naiara Sarasua Rodriguez", "Pedagogia", true);
		Alumno a47 = new Alumno("Estitxu Uranga Gastaka", "Pedagogia", true);
		Alumno a48 = new Alumno("Ane Lecunberri Pagola", "Pedagogia", true);
		Alumno a49 = new Alumno("Irune Ibarretxe Garcia", "Pedagogia", true);
		Alumno a50 = new Alumno("Ane Lizarralde Zubimendi", "Pedagogia", true);
		Alumno a51 = new Alumno("Cristina Rubio de Blas", "Pedagogia", false);
		Alumno a52 = new Alumno("Arrate Larreina Maestre", "Pedagogia", false);
		
		//MAGISTERITZAKO IKASLEAK
		
		Alumno a53 = new Alumno("Itsaso Oteiza", "Magisteritza", true);
		Alumno a54 = new Alumno("Eneka Gorrotxategi Goikoetxea", "Magisteritza", true);
		Alumno a55 = new Alumno("Libe Alvarez Elola", "Magisteritza", true);
		Alumno a56 = new Alumno("Maialen Arrieta Sarratea", "Magisteritza", true);
		Alumno a57 = new Alumno("Urtzi Murguia", "Magisteritza", true);
		Alumno a58 = new Alumno("Jokin Valderrey Ostolaza", "Magisteritza", true);
		
		//ZUZENBIDEKO IKASLEAK
		
		Alumno a59 = new Alumno("Leize Lor Arcos Garijo", "Zuzenbidea", true);
		Alumno a60 = new Alumno("Maria Perez Garrido", "Zuzenbidea", true);
		Alumno a61 = new Alumno("Irati Amondarain Labayen", "Zuzenbidea", true);
		Alumno a62 = new Alumno("Garazi Ortiz de Mendibil Jausoro", "Zuzenbidea", true);
		Alumno a63 = new Alumno("Eukene Uli Unsain", "Zuzenbidea", true);
		
		//GIZARTE HEZKUNTZAKO IKASLEAK
		
		Alumno a64 = new Alumno("Ainhoa Azkue Ibarguren", "Gizarte Hezkuntza", true);
		Alumno a65 = new Alumno("Joana Mendez Gallastegi", "Gizarte Hezkuntza", true);
		Alumno a66 = new Alumno("Maider Altzibar Zenarruzabeitia", "Gizarte Hezkuntza", true);
		Alumno a67 = new Alumno("Lierni Hernandez Oiarbide", "Gizarte Hezkuntza", true);
		
		//ARKITEKTURAKO IKASLEAK
		
		Alumno a68 = new Alumno("Eneko Guerra Rodriguez", "Arkitektura", true);
		
		//ERIZAINTZAKO IKASLEAK
		
		Alumno a69 = new Alumno("Idoia Zurutuza", "Erizaintza", false);
		
		Alumno[] alumnos ={a01,a02,a03,a04,a05,a06,a07,a08,a09,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,
						   a32,a33,a34,a35,a36,a37,a38,a39,a40,a41,a42,a43,a44,a45,a46,a47,a48,a49,a50,a51,a52,a53,a54,a55,a56,a57,a58,a59,a60,a61,a62,
						   a63,a64,a65,a66,a67,a68,a69};
		
		Centro[] centros ={informatika,zuzenbidea,pedagogia,politeknika,gizarteHezkuntza,psikologia,magisteritza,enpresa};
		
		for(int i=0;i<alumnos.length;i++)
		{
			if(alumnos[i].getCentro().equals("Informatika"))
				informatika.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Zuzenbidea"))
				zuzenbidea.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Pedagogia"))
				pedagogia.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Politeknika"))
				politeknika.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Gizarte Hezkuntza"))
				gizarteHezkuntza.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Psikologia"))
				psikologia.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Magisteritza"))
				magisteritza.anadirAlumno(alumnos[i]);
			if(alumnos[i].getCentro().equals("Enpresa"))
				enpresa.anadirAlumno(alumnos[i]);
		}
		
		informatika.calcularExito();
		zuzenbidea.calcularExito();
		pedagogia.calcularExito();
		politeknika.calcularExito();
		gizarteHezkuntza.calcularExito();
		psikologia.calcularExito();
		magisteritza.calcularExito();
		enpresa.calcularExito();
		//erizaintza.calcularExito();
		//arkitektura.calcularExito();

		int pref = a68.preferido(1);
		retos[pref].anadirAlumno(a68);
		
		pref = a69.preferido(1);
		retos[pref].anadirAlumno(a69);
		
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
	
	*/
}
