package controller;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.xml.internal.ws.util.StringUtils;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import model.data_structures.IQueue;
import model.logic.ManejoFechaHora;
import model.logic.MovingViolationsManager;
import model.vo.EstadisticasCargaInfracciones;
import model.vo.InfraccionesFecha;
import model.vo.InfraccionesFranjaHoraria;
import model.vo.InfraccionesFranjaHorariaViolationCode;
import model.vo.InfraccionesLocalizacion;
import model.vo.VOMovingViolations;
import sun.reflect.generics.tree.VoidDescriptor;
import view.MovingViolationsManagerView;

/**
 * Esta clase representa el controlador de los datos
 */
public class Controller {
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	/**
	 * Es la referencia al view
	 */
	private MovingViolationsManagerView view;
		
	// Componente modelo (logica de la aplicacion)
	private MovingViolationsManager model;
	
	/**
	 * Semestre del cual se subiran los datos - 1(Enero - Junio) o 2(Julio - Diciembtre) 
	 */
	public int semestre;
	
	//-----------------------------------------------------------------------------------------------
	// Constructores
	// ----------------------------------------------------------------------------------------------
	
	/**
	 * Construye un nuevo controlador con una lista doblemente encadenada.
	 * <b>post:</b> se construyo  un nuevo controlador con una lista doblemente encadenada
	 * La lista doblemente encadenada esta vac�a <br>
	 */
	public Controller() {
		view = new MovingViolationsManagerView();
		model = new MovingViolationsManager();
	}
	
	/**
	 * Lee la opcion que el usuario escoja para el desarrollo del programa
	 * @throws Exception 
	 */
	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 * @throws IOException 
	 */
	public void run() throws IOException{

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();

		while(!fin){
			view.printMenu();

			int option = sc.nextInt();

			switch(option){

			case 0:
				view.printMessage("Ingrese semestre a cargar (1 o 2)");
				int semestre = sc.nextInt();
				EstadisticasCargaInfracciones resumenCarga = model.loadMovingViolations(semestre);
				
				//TODO Mostrar resultado de tipo EstadisticasCargaInfracciones con: 
				//     total de infracciones cargadas, numero de infracciones cargadas por mes y zona Minimax (Xmin, Ymin) y (Xmax, Ymax)
				view.printResumenLoadMovingViolations(resumenCarga);
				break;

			case 1:
				view.printMessage("1A. Consultar las N franjas horarias con mas infracciones que desea ver. Ingresar valor de N: ");
				int numeroFranjas = sc.nextInt();

				//TODO Completar para la invocaci�n del metodo 1A
				IQueue<InfraccionesFranjaHoraria> resultados = model.rankingNFranjas(numeroFranjas);
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesFranjaHoraria
				view.printReq1A(resultados);
				break;

			case 2:
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				double xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				double ycoord = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 2A
				InfraccionesLocalizacion resultado = model.consultarPorLocalizacionHash(xcoord, ycoord);

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				view.printReq2A(resultado);
				break;

			case 3:
				view.printMessage("Ingrese la fecha inicial del rango. Formato a�o-mes-dia (ej. 2008-06-21)");
				String fechaInicialStr = sc.next();
				LocalDate fechaInicial = ManejoFechaHora.convertirFecha_LD( fechaInicialStr );

				view.printMessage("Ingrese la fecha final del rango. Formato a�o-mes-dia (ej. 2008-06-30)");
				String fechaFinalStr = sc.next();
				LocalDate fechaFinal = ManejoFechaHora.convertirFecha_LD( fechaFinalStr );

				//TODO Completar para la invocacion del metodo 3A
				IQueue<InfraccionesFecha> ans = model.consultarInfraccionesPorRangoFechas(fechaInicial, fechaFinal);

				//TODO Mostrar resultado de tipo Cola de InfraccionesFecha
				view.printReq3A(ans);
				break;


			case 4:
				view.printMessage("1B. Consultar los N Tipos con mas infracciones. Ingrese el valor de N: ");
				int numeroTipos = sc.nextInt();

				//TODO Completar para la invocaci�n del metodo 1B				
				//model.rankingNViolationCodes(int N)
				
				//TODO Mostrar resultado de tipo Cola con N InfraccionesViolationCode
				//view.printReq1B( ... )
				break;

			case 5:						
				view.printMessage("Ingrese la coordenada en X de la localizacion geografica (Ej. 1234,56): ");
				xcoord = sc.nextDouble();
				view.printMessage("Ingrese la coordenada en Y de la localizacion geografica (Ej. 5678,23): ");
				ycoord = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 2B
				//model.consultarPorLocalizacionArbol(double xCoord, double yCoord)

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 
				//view.printReq2B( ... )
				break;

			case 6:
				view.printMessage("Ingrese la cantidad minima de dinero que deben acumular las infracciones en sus rangos de fecha  (Ej. 1234,56)");
				double cantidadMinima = sc.nextDouble();

				view.printMessage("Ingrese la cantidad maxima de dinero que deben acumular las infracciones en sus rangos de fecha (Ej. 5678,23)");
				double cantidadMaxima = sc.nextDouble();

				//TODO Completar para la invocaci�n del metodo 3B
				//model.consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)

				//TODO Mostrar resultado de tipo Cola con InfraccionesFechaHora 
				//view.printReq3B( ... )
				break;

			case 7:
				view.printMessage("1C. Consultar las infracciones con Address_Id. Ingresar el valor de Address_Id: ");
				int addressID = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaci�n del metodo 1C
				InfraccionesLocalizacion res = model.consultarPorAddressId(addressID);

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 1C: " + duration + " milisegundos");

				//TODO Mostrar resultado de tipo InfraccionesLocalizacion 	
				view.printReq1C(res);
				break;

			case 8:
				view.printMessage("Ingrese la hora inicial del rango. Formato HH:MM:SS (ej. 09:30:00)");
				String horaInicialStr = sc.next();
				LocalTime horaInicial = ManejoFechaHora.convertirHora_LT(horaInicialStr);

				view.printMessage("Ingrese la hora final del rango. Formato HH:MM:SS (ej. 16:00:00)");
				String horaFinalStr = sc.next();
				LocalTime horaFinal = ManejoFechaHora.convertirHora_LT(horaFinalStr);

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 2C
				InfraccionesFranjaHorariaViolationCode resp = model.consultarPorRangoHoras(horaInicial, horaFinal);

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 2C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo InfraccionesFranjaHorarioViolationCode
				view.printReq2C(resp);
				break;

			case 9:
				view.printMessage("Consultar las N localizaciones geograficas con mas infracciones. Ingrese el valor de N: ");
				int numeroLocalizaciones = sc.nextInt();

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocaci�n del metodo 3C
				//model.rankingNLocalizaciones(int N)

				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 3C: " + duration + " milisegundos");
				//TODO Mostrar resultado de tipo Cola con InfraccionesLocalizacion
				//view.printReq3C( ... )
				break;

			case 10:

				System.out.println("Grafica ASCII con la informacion de las infracciones por ViolationCode");

				startTime = System.currentTimeMillis();
				//TODO Completar para la invocacion del metodo 4C
				//model.ordenarCodigosPorNumeroInfracciones()

				//TODO Mostrar grafica a partir del resultado del metodo anterior
				//view.printReq4C( ... )
				endTime = System.currentTimeMillis();

				duration = endTime - startTime;
				view.printMessage("Tiempo requerimiento 4C: " + duration + " milisegundos");
				break;

			case 11:	
				fin = true;
				sc.close();
				break;
			}
		}
	}
}
