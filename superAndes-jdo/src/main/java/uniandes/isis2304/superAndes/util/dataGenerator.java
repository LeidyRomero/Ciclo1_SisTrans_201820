package uniandes.isis2304.superAndes.util;

import java.io.*;
import java.util.Random;

public class dataGenerator {
	public static final String PATH = "./data/";
	
	
	public static void generarSucursales(){
		
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(PATH+"sucursales.sql"));
			
			String direccion = "Calle ";
			String ciudad = "Ciudad ";
			String sucursal = "Sucursal ";
			Random r = new Random();
			for (int i = 1; i <= 15000; i++) {
				int num = r.nextInt(980);
				ps.println("INSERT INTO A_SUCURSAL VALUES ("+num+20+",'"+direccion+i+"','"+ciudad+i+"','"+sucursal+i+"')");
			}
			System.out.println("Fin :D");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generarSucursales();
	}

}
