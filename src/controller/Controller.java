package controller;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.xml.internal.ws.util.StringUtils;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import model.data_structures.DoubleLinkedList;
import model.data_structures.Nodo;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import sun.reflect.generics.tree.VoidDescriptor;
import view.MovingViolationsManagerView;

/**
 * Esta clase representa el controlador de los datos
 */
public class Controller {
	
	//---------------------------------------------------------------------------------------------------
	// Constantes
	// --------------------------------------------------------------------------------------------------

	/**
	 * Constante que representa los datos de las infracciones realizadas en Enero
	 */
	public static final String DATOS_MES_1 = "./data/Moving_Violations_Issued_in_January_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Febrero
	 */
	public static final String DATOS_MES_2 = "./data/Moving_Violations_Issued_in_February_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Marzo
	 */
	public static final String DATOS_MES_3 = "./data/Moving_Violations_Issued_in_March_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Abril
	 */
	public static final String DATOS_MES_4 = "./data/Moving_Violations_Issued_in_April_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Mayo
	 */
	public static final String DATOS_MES_5 = "./data/Moving_Violations_Issued_in_May_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Junio
	 */
	public static final String DATOS_MES_6= "./data/Moving_Violations_Issued_in_June_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Julio
	 */
	public static final String DATOS_MES_7 = "./data/Moving_Violations_Issued_in_July_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Agosto
	 */
	public static final String DATOS_MES_8 = "./data/Moving_Violations_Issued_in_August_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Septiembre
	 */
	public static final String DATOS_MES_9 = "./data/Moving_Violations_Issued_in_September_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Octubre
	 */
	public static final String DATOS_MES_10 = "./data/Moving_Violations_Issued_in_Octomber_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Noviembre
	 */
	public static final String DATOS_MES_11 = "./data/Moving_Violations_Issued_in_November_2018.csv";
	
	/**
	 * Constante que representa los datos de las infracciones realizadas en Diciembre
	 */
	public static final String DATOS_MES_12 = "./data/Moving_Violations_Issued_in_December_2018.csv";
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Es el formato que se usara para las fechas
	 */
	//private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	/**
	 * Es la referencia al view
	 */
	private MovingViolationsManagerView view;
	
	/**
	 * Lista doble donde se van a cargar los datos de los archivos
	 */
	private DoubleLinkedList<VOMovingViolations> movingViolationsList;
	
	/**
	 * Cautrimestre del cual se subiran los datos - 1(Enero - Abril), 2(Mayo - Agosto) o 3(Septiembre - Diciembre)
	 */
	public int cuatrimestre;
	
	//-----------------------------------------------------------------------------------------------
	// Constructores
	// ----------------------------------------------------------------------------------------------
	
	/**
	 * Construye un nuevo controlador con una pila y una cola.
	 * <b>post:</b> se construyo  un nuevo controlador con una cola y una pila
	 * La pila esta vacía <br>
	 * La cola esta vacía <br> 
	 */
	public Controller() {
		view = new MovingViolationsManagerView();
		
		//TODO, inicializar la pila y la cola
		movingViolationsList = new DoubleLinkedList<VOMovingViolations>();
	}
	
	/**
	 * Lee la opcion que el usuario escoja para el desarrollo del programa
	 * @throws Exception 
	 */
	public void run() throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		
		while(!fin)
		{
			view.printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 0:
					view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
					int numeroCuatrimestre = sc.nextInt();
					if(movingViolationsList.estaVacia()){
						controller.loadMovingViolations(1);
					}
				
				case 1:
					break;
					
				case 2:
					break;
					
				case 3:
					break;
						
					
				case 4:
					break;
					
				case 5:
					break;
				
				case 6:
					break;
					
				case 7:
					break;
					
				case 8:
					break;
					
				case 9:
					break;
				
				case 10:
					break;
					
				case 11:
					break;
				
				case 12:
					break;
					
				case 13:	
					fin=true;
					sc.close();
					break;
			}
		}

	}

	
	/**
	 * Carga los datos segun el cuatrimestre escogido por el usuario
	 * @param pCuatrimestre Numero de cuatrimestre escogido por el usuario. pCuatrimestre = 1 | pCuatrimestre = 2 | pCuatrimestre = 3
	 * @throws Exception si no pudo cargar los datos.
	 */
	public void loadMovingViolations(int pCuatrimestre) throws Exception{
		// TODO
		String[] archivos = new String[12];
		for(int i = 0; i < 12; i++){
			if(i == 0){archivos[i] = DATOS_MES_1;}
			else if(i==1){archivos[i] = DATOS_MES_2;}
			else if(i==2){archivos[i] = DATOS_MES_3;}
			else if(i==3){archivos[i] = DATOS_MES_4;}
			else if(i==4){archivos[i] = DATOS_MES_5;}
			else if(i==5){archivos[i] = DATOS_MES_6;}
			else if(i==6){archivos[i] = DATOS_MES_7;}
			else if(i==7){archivos[i] = DATOS_MES_8;}
			else if(i==8){archivos[i] = DATOS_MES_9;}
			else if(i==9){archivos[i] = DATOS_MES_10;}
			else if(i==10){archivos[i] = DATOS_MES_11;}
			else if(i==11){archivos[i] = DATOS_MES_12;}
		}
		
		String fileName = null;
		
		if(pCuatrimestre == 1) {
			for(int i = 0; i < 4; i++) {
				fileName = archivos[i];
				loadArchivo(fileName);
			}
		}
		if(pCuatrimestre == 2) {
			for(int i = 4; i < 8; i++) {
				fileName = archivos[i];
				loadArchivo(fileName);
			}
		}
		if(pCuatrimestre == 3) {
			for(int i = 8; i < 11; i++) {
				fileName = archivos[i];
				loadArchivo(fileName);
			}
		}
	}
	

	public void loadArchivo(String pFileName) {
		File file = new File(pFileName);
		try {
			Scanner inputStream = new Scanner(file);
			String data = inputStream.next();
			while(inputStream.hasNext()) {
				data = inputStream.next();
				String[] values = data.split(",");
				movingViolationsList.agregar(new VOMovingViolations(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), values[7], Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), values[12], Integer.parseInt(values[13]), values[14], values[15], values[16], Integer.parseInt(values[17])));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------
	// Métodos
	//------------------------------------------------------------------------------------------
	
	//Parte A 
	
	
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyy-MM-dd"));
	}

	
	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd"+" T "+"kk:mm:ss"));
	}
}
