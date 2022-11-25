package org.generation.italy.eventi;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Event e = null;
		try {
			
			e = new Event("Evento 1", "2022/12/14", 100);
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		System.out.println(e);
	}
}
