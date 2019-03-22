package main;

import controller.Controller;
/**
 * Esta es la clase pricipal del controlador
 */
public class MVC {
	
	/**
	 * Ejecuta el controller
	 * @param args Parametros de la ejecución. No son necesarios.
	 * @throws Exception si se presenta algun error durante la ejecución.
	 */
	public static void main(String[] args) throws Exception {
		Controller controler = new Controller();
		controler.run();
	}

}
