package controlador;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import modelo.Alumno;
import modelo.Centro;
import modelo.Necesidad;

public class Cprincipal {

	private static Connection DBconexion;
	
	public static void comenzarDB(String nombre) {// es el nombre del archivo *.db
		//boolean existe = false;
		try {
			File file = new File(nombre + ".db");
			if (file.exists()) {
				System.out.println("Base de datos encontrada, abriendo...");
				Cprincipal.DBconexion = DriverManager.getConnection("jdbc:sqlite:" + nombre + ".db");
				DBconexion.setAutoCommit(true);
				//existe = true;
			} else {
				System.out.println("Base de datos no encontrada, creando...");
				//existe = false;
				Class.forName("org.sqlite.JDBC");
				//crea la base de datos
				Cprincipal.DBconexion = DriverManager.getConnection("jdbc:sqlite:" + nombre + ".db");
				DBconexion.setAutoCommit(true);
				Cprincipal.initialize();
				//Añade los centros por defecto - SI SE CAMBIA EL ORDEN CAMBIAR EN Mnagusia TAMBIEN
				Cprincipal.addCentros("Psikologia");
				Cprincipal.addCentros("Ingeniaritza");
				Cprincipal.addCentros("Antropologia");
				Cprincipal.addCentros("Arkitektura");
				Cprincipal.addCentros("Informatika");
				Cprincipal.addCentros("Magisteritza");
				Cprincipal.addCentros("Gizarte Hezkuntza");
				Cprincipal.addCentros("Kimika");
				Cprincipal.addCentros("ADE");
				Cprincipal.addCentros("Pedagogia");
				//Añade retos para las pruebas (retos por defectu). Si se modifica el numero hay que reiniciar el programa y meter de nuevo las preferencias
				Cprincipal.addNecesidad("Atari", 0, false);
				Cprincipal.addNecesidad("Zaharrean", 1, false);
				Cprincipal.addNecesidad("Medicus Mundi", 2, false);
				Cprincipal.addNecesidad("Parke Teknologikoa", 3, false);
				Cprincipal.addNecesidad("Aguas de Añarbe", 4, false);
				Cprincipal.addNecesidad("Ostadar", 5, false);
				Cprincipal.addNecesidad("Donostia Sustapena", 6, false);
				Cprincipal.addNecesidad("EHU Iraunkortasuna", 7, false);
				Cprincipal.addNecesidad("Gureak", 8, false);
				Cprincipal.addNecesidad("Goiener", 9, false);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Base de datos abierta exitosamente");
	}

	public static void initialize() {
		Statement stmt = null;
		String sql = null;
		try {
			// crea la tabla necesidad
			stmt = DBconexion.createStatement();
			sql = "CREATE TABLE NECESIDADES(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "NOMBRE TEXT UNIQUE NOT NULL, " + "INDICE INT NOT NULL," + "EUSKERAZ STRING NOT NULL);";
			stmt.executeUpdate(sql);
			// crea la tabla centros
			sql = "CREATE TABLE CENTROS(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "NOMBRE TEXT UNIQUE NOT NULL"
					+ ");";
			stmt.executeUpdate(sql);
			// crea la tabla alumnos
			sql = "CREATE TABLE ALUMNOS(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NOMBRE TEXT UNIQUE NOT NULL,"
					+ "CENTRO TEXT NOT NULL," + "EUSKERA STRING NOT NULL," + "PREFERENCIAS TEXT," + "GENERO STRING NOT NULL);";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**Añadir alumnos a la DB*/
	public static void addAlumnos(String nombre, String centro, String genero, boolean euskera) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			if(Cprincipal.getCentros().toString().contains(centro)) { //TODO mirar si existe el centro
				String sql = "INSERT INTO ALUMNOS(NOMBRE,CENTRO,EUSKERA,GENERO) " + "VALUES ('" + nombre + "','" + centro + "','"
					+ Boolean.toString(euskera) + "','"+genero+"');";
			stmt.executeUpdate(sql);
			} else {
				System.err.println("El centro especificado ("+centro+") no existe");
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**Eliminar alumnos de la DB*/
	public static void deleteAlumnos(String nombre) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "DELETE from ALUMNOS where NOMBRE='" + nombre + "';";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**Actualizad datos de alumnos de la DB*/
	public static void updateAlumnos(String nombre, String preferencias) {
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      String sql = "UPDATE ALUMNOS set PREFERENCIAS='"+preferencias+"' where NOMBRE='"+nombre+"';";
	      stmt.executeUpdate(sql);
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	/**Conseguir la lista de alumnos de la DB*/
	public static ArrayList<Alumno> getAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ALUMNOS;" );
	      while ( rs.next()) {
	    	  if((!(rs.getString(5)==null)) && rs.getString(5).length()==Cprincipal.getNumeroNecesidad()) {
	    		  System.out.println(rs.getString(5));
	    		  lista.add(new Alumno(rs.getString(2), rs.getString(5), rs.getString(3), Boolean.valueOf(rs.getString(4)),rs.getString(5)));
	    	  } else {
		    	  lista.add(new Alumno(rs.getString(2),rs.getString(3),Boolean.valueOf(rs.getString(4)),rs.getString(6)));	  
	    	  }

	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    Collections.sort(lista, new AlumnoComparator());
		return lista;
	}

	/**Añadir centros a la DB*/
	public static void addCentros(String nombre) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "INSERT INTO CENTROS(NOMBRE) " + "VALUES ('" + nombre + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**Eliminar centros de la DB*/
	public static void deleteCentros(String nombre) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "DELETE from CENTROS where NOMBRE='" + nombre + "';";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**Actualizar centros de la DB
	 * @param viejo */
	public static void updateCentros(String nombre, String viejo) {
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      String sql = "UPDATE CENTROS set NOMBRE='"+nombre+"' where NOMBRE='"+viejo+"';";
	      stmt.executeUpdate(sql);
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	/**Conseguir la lista de centros de la DB*/
	public static ArrayList<Centro> getCentros() {
		ArrayList<Centro> lista = new ArrayList<Centro>();
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM CENTROS;" );
	      while ( rs.next() ) {
	    	  lista.add(new Centro(rs.getString(2)));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return lista;
	}
	
	/**Añadir necesidades a la DB*/
	public static void addNecesidad(String nombre, int indice, boolean euskeraz) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "INSERT INTO NECESIDADES(NOMBRE,INDICE,EUSKERAZ) " + "VALUES ('" + nombre + "',"+indice+",'" + Boolean.toString(euskeraz)+"');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**Eliminar necesidades de la DB*/
	public static void deleteNecesidad(String nombre) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "DELETE from NECESIDADES where NOMBRE='" + nombre + "';";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**Actualizar necesidades de la DB
	 * @param viejo */ 
	public static void updateNecesidad(String nombre, int indice, boolean euskeraz) {//TODO
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      String sql = "UPDATE NECESIDADES set (INDICE="+indice+", EUSKERAZ='"+Boolean.toString(euskeraz)+"') where NOMBRE='"+nombre+"';";
	      stmt.executeUpdate(sql);
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	/**Conseguir la lista de necesidades de la DB*/
	public static ArrayList<Necesidad> getNecesidad() {
		ArrayList<Necesidad> lista = new ArrayList<Necesidad>();
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM NECESIDADES;" );
	      while ( rs.next() ) {
	    	  lista.add(new Necesidad(rs.getInt(3),rs.getString(2),Boolean.valueOf(rs.getString(4))));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return lista;
	}
	
	/**Conseguir el numero de necesidades*/
	public static int getNumeroNecesidad() {
		Statement stmt = null;
		int n=0;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM NECESIDADES;" );
	      while(rs.next())
	    	  n=rs.getRow();
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return n;
	}
	
	/**Conseguir el numero de centros*/
	public static int getNumeroCentros() {
		Statement stmt = null;
		int n=0;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM CENTROS;" );
	      while(rs.next())
	    	  n=rs.getRow();
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return n;
	}
	
	/**Conseguir el numero de alumnos*/
	public static int getNumeroAlumnos() {
		Statement stmt = null;
		int n=0;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ALUMNOS;" );
	      while(rs.next())
	    	  n=rs.getRow();
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return n;
	}
	
	/**Ordenador de Alumnos*/
	public static class AlumnoComparator implements Comparator<Alumno> {
	    public int compare(Alumno a1, Alumno a2) {
	        return a1.getNombre().compareTo(a2.getNombre());
	    }
	}
}
