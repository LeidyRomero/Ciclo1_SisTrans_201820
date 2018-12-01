package uniandes.isis2304.superAndes.util;

import java.io.*;
import java.util.Random;

public class dataGenerator {
	public static final String PATH = "./data/";


	public static void generarSucursales(){

		String[] ciudades = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta", "Ibagué", "Soacha", "Bucaramanga", "Villavicencio", "Santa Marta", "Valledupar", "Bello", "Pereira", "Montería", "Pasto", "Buenaventura", "Manizales", "Neiva", "Palmira", "Armenia", "Riohacha", "Sincelejo", "Popayán", "Itagüí", "Floridablanca", "Envigado", "Tuluá", "Tumaco", "Dosquebradas" };
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(PATH+"sucursales.csv"));

			String direccion = "CLL ";
			String ciudad = "";
			String sucursal = "Sucursal ";
			Random r = new Random();
			ps.println("TAMANIO, DIRECCIONSUCURSAL, CIUDAD, NOMBRESUCURSAL");
			for (int i = 1; i <= 50000; i++) {
				
				ciudad = ciudades[i % ciudades.length];
				int num = r.nextInt(980);
				ps.println(num+20+","+direccion+i%306+" #"+i%80+"-"+i+","+ciudad+","+sucursal+i);
			}
			System.out.println("Fin :D");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public static void generarClientes(){
		
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(PATH+"clientes.csv"));

			String nombre = "";
			String correo = "";
			Random r = new Random();
			ps.println("CORREO, NOMBRECONSUMIDOR, PUNTOS");
			for (int i = 1; i <= 50000; i++) {
				
				if(i%7 == 0)
				{
					nombre = "Empresa"+i;
					correo = "empresa"+i+"@empresa.com";
				}
				else
				{
					nombre ="Persona"+i;
					correo = "persona"+i+"@persona.com";
				}
				
				int num = r.nextInt(100000);
				ps.println(correo+","+nombre+","+num);
			}
			System.out.println("Fin :D");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public static void generarProveedor(){
		
		String[] calificaciones = {"Good", "Bad", "Appalling", "Excellent", "Regular"};
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(PATH+"clientes.csv"));

			String nombre = "";
			String calificacion = "";
			int num = 1000000000;
			ps.println("NITPROVEEDOR, NOMBREPROVEEDOR, CALIFICACIONPROVEEDOR");
			for (int i = 1; i <= 50000; i++) {
				
				calificacion = calificaciones[i%5];
				ps.println(num+","+nombre+","+calificacion);
			}
			System.out.println("Fin :D");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generarClientes();
	}
}