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

public class Cprincipal {

	private static Connection DBconexion;

	public Cprincipal(String nombre) {// es el nombre del archivo *.db
		boolean existe = false;
		try {
			File file = new File(nombre + ".db");
			if (file.exists()) {
				System.out.println("Base de datos encontrada, abriendo...");
				existe = true;
			} else {
				System.out.println("Base de datos no encontrada, creando...");
				existe = false;
			}
			Class.forName("org.sqlite.JDBC");
			Cprincipal.DBconexion = DriverManager.getConnection("jdbc:sqlite:" + nombre + ".db"); // crea
																									// la
																									// base
																									// de
																									// datos
			DBconexion.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if (!existe)
			Cprincipal.initialize();
		System.out.println("Base de datos abierta exitosamente");
	}

	public static void initialize() {
		Statement stmt = null;
		String sql = null;
		try {
			// crea la tabla necesidad
			stmt = DBconexion.createStatement();
			sql = "CREATE TABLE NECESIDADES(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "NOMBRE TEXT NOT NULL, " + "IDIOMA TEXT NOT NULL," + "INDICE INT NOT NULL" + ");";
			stmt.executeUpdate(sql);
			// crea la tabla centros
			sql = "CREATE TABLE CENTROS(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "NOMBRE TEXT UNIQUE NOT NULL"
					+ ");";
			stmt.executeUpdate(sql);
			// crea la tabla alumnos
			sql = "CREATE TABLE ALUMNOS(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NOMBRE TEXT UNIQUE NOT NULL,"
					+ "CENTRO TEXT NOT NULL," + "EUSKERA STRING NOT NULL," + "PREFERENCIAS TEXT" + ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	/*public static String getNalumnos() {
		Statement stmt = null;
		ResultSet rs = null;
		int id = -1;
		try {
			stmt = DBconexion.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM ALUMNOS;");
			id = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//System.exit(0);
		}
		return Integer.toString(id);
	}*/

	/**Añadir alumnos a la DB*/
	public static void addAlumnos(String nombre, String centro, boolean euskera) {
		Statement stmt = null;
		try {
			stmt = DBconexion.createStatement();
			String sql = "INSERT INTO ALUMNOS(NOMBRE,CENTRO,EUSKERA) " + "VALUES ('" + nombre + "','" + centro + "','"
					+ Boolean.toString(euskera) + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//System.exit(0);
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
			//System.exit(0);
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
	      //System.exit(0);
	    }
	}

	/**Conseguir la lista de alumnos de la DB*/
	public static ArrayList<Alumno> getAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
	    Statement stmt = null;
	    try {
	      stmt = DBconexion.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ALUMNOS;" );
	      while ( rs.next() ) {
	    	  //System.out.println(rs.getString(4));
	    	  lista.add(new Alumno(rs.getString(2),rs.getString(3),Boolean.valueOf(rs.getString(4))));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
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
			//System.exit(0);
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
			//System.exit(0);
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
	      //System.exit(0);
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
	    	  //System.out.println(rs.getString(2));
	    	  lista.add(new Centro(rs.getString(2)));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		return lista;
	}
	
	/**Ordenador de Alumnos*/
	public static class AlumnoComparator implements Comparator<Alumno> {
	    public int compare(Alumno a1, Alumno a2) {
	        return a1.getNombre().compareTo(a2.getNombre());
	    }
	}
}
