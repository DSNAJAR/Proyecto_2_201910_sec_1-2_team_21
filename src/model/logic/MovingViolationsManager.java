package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import model.data_structures.IQueue;
import model.data_structures.LinearProbingHT;
import model.data_structures.MaxPQ;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.data_structures.SeparateChainingHT;
import model.vo.EstadisticaInfracciones;
import model.vo.EstadisticasCargaInfracciones;
import model.vo.InfraccionesFecha;
import model.vo.InfraccionesFechaHora;
import model.vo.InfraccionesFranjaHoraria;
import model.vo.InfraccionesFranjaHorariaViolationCode;
import model.vo.InfraccionesLocalizacion;
import model.vo.InfraccionesViolationCode;
import model.vo.VOMovingViolations;

public class MovingViolationsManager {
	
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
	//TODO Definir atributos necesarios
	
	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private IQueue<VOMovingViolations> movingViolationsQueue;		
		
	/**
	 * Metodo constructor
	 */
	public MovingViolationsManager()
	{
		//TODO inicializar los atributos
		movingViolationsQueue = new Queue<VOMovingViolations>();
	}
	
	/**
	 * Cargar las infracciones de un semestre de 2018
	 * @param numeroSemestre numero del semestre a cargar (1 o 2)
	 * @return objeto con el resultado de la carga de las infracciones
	 * @throws IOException 
	 */
	public EstadisticasCargaInfracciones loadMovingViolations(int numeroSemestre) throws IOException {
		// TODO Realizar la carga de infracciones del semestre
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
		int[] infraccionesxMesSemestre = new int[6];
		Object[] x = null;
		if(numeroSemestre == 1) {
			for(int i = 0; i < 6; i++) {
				fileName = archivos[i];
				loadArchivo(fileName);
				//infraccionesxMesSemestre[i] = (int) x[0];
			}
		}
		if(numeroSemestre == 2) {
			for(int i = 6; i < 12; i++) {
				fileName = archivos[i];
				loadArchivo(fileName);
				//infraccionesxMesSemestre[i - 6] = (int) x[0];
			}
		}
		
		int suma = 0;
		for(int i = 0; i < infraccionesxMesSemestre.length; i++) suma += infraccionesxMesSemestre[i];
		
		return new EstadisticasCargaInfracciones(suma, infraccionesxMesSemestre, (double) x[1], (double) x[2], (double) x[3], (double) x[4]);
	}
	
	//Método auxiliar
	
	public Object[] loadArchivo(String pFileName) throws IOException {
		BufferedReader br = null;
		String line = " ";
		FileReader file = new FileReader(pFileName);
		int numeroInfracciones = 0;
		double minX = 0.0;
		double minY = 0.0;
		double maxX = 0.0;
		double maxY = 0.0;
		
		try {
			br = new BufferedReader(file);
			line = br.readLine();
			while((line = br.readLine()) != null) {
				String[] datos = line.split(",");				
				
				int objectId = 0;
				if(!datos[0].isEmpty()) objectId = Integer.parseInt(datos[0]);
				
				String location = datos[2];
				
				int addresId = 0;
				if(!datos[3].isEmpty()) addresId = Integer.parseInt(datos[3]);
				
				int streetSegId = 0;
				if(!datos[4].isEmpty()) streetSegId = Integer.parseInt(datos[4]);
				
				double xCoord = Double.parseDouble(datos[5]);
				double yCoord = Double.parseDouble(datos[6]);
				String ticketType = datos[7];
			    int fineAMT = Integer.parseInt(datos[8]);
			    double totalPaid = Double.parseDouble(datos[9]);
			    
			    int penal1 = 0;
			    if(!datos[10].isEmpty()) penal1 = Integer.parseInt(datos[10]); 

			    int penal2 = 0;
			    if(!datos[11].isEmpty()) penal2 = Integer.parseInt(datos[11]);
			    
				String accidentIndicator = datos[12];
				int agencyId = 0;
				String ticketIssueDate = datos[13];
				String violationCode = datos[14];
				String violationDesc = datos[15];
				
				
			    numeroInfracciones++;
			    movingViolationsQueue.enqueue(new VOMovingViolations(objectId, location, addresId, streetSegId, xCoord, yCoord, ticketType, fineAMT, totalPaid, penal1, penal2, accidentIndicator, ticketIssueDate, violationCode, violationDesc));
			    if(Double.compare(maxX, xCoord) < 0) maxX = xCoord;
			    if(Double.compare(maxY, yCoord) < 0) maxY = yCoord;
			    if(minX == 0.0) minX = xCoord; else if(Double.compare(minX, xCoord) > 0) minX = xCoord;
			    if(minY == 0.0) minY = yCoord; else if(Double.compare(minY, yCoord) > 0) minY = yCoord;
			}
			
			br.close();
			System.out.println(numeroInfracciones);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			throw new IOException("Error al leer el archivo");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Object[] x = {numeroInfracciones, minX, minY, maxX, maxY};
		return x;
	}

	/**
	  * Requerimiento 1A: Obtener el ranking de las N franjas horarias
	  * que tengan más infracciones. 
	  * @param int N: Número de franjas horarias que tienen más infracciones
	  * @return Cola con objetos InfraccionesFranjaHoraria
	  */
	public IQueue<InfraccionesFranjaHoraria> rankingNFranjas(int N)
	{
		// TODO completar
		IQueue<VOMovingViolations> auxQueue = movingViolationsQueue;
		IQueue<InfraccionesFranjaHoraria> resultado = new Queue<InfraccionesFranjaHoraria>();
		InfraccionesFranjaHoraria[] infraccionesFranja = new InfraccionesFranjaHoraria[24];
		VOMovingViolations movingViolation = null;
		
		for(int i = 0; i < infraccionesFranja.length; i++) {
			LocalTime hInicial = ManejoFechaHora.convertirHora_LT(i + ":00:00");
			LocalTime hFinal = ManejoFechaHora.convertirHora_LT(i + ":59:59");
			
			IQueue<VOMovingViolations> lista = new Queue<VOMovingViolations>();
			
			while((movingViolation = auxQueue.dequeue()) != null) {
				String[] dateTime = movingViolation.getTicketIssueDate().split("T");
				String[] time = dateTime[1].split(".");
				LocalTime horaMV = ManejoFechaHora.convertirHora_LT(time[0]);
				
				if(horaMV.compareTo(hInicial) >= 0 && horaMV.compareTo(hFinal) <= 0) {
					lista.enqueue(movingViolation);
				}
			}
			auxQueue = movingViolationsQueue;
			infraccionesFranja[i] = new InfraccionesFranjaHoraria(hInicial, hFinal, lista);
		}

		MaxPQ<Integer, InfraccionesFranjaHoraria> x = new MaxPQ<Integer, InfraccionesFranjaHoraria>(N);
		for(int i = 0; i < infraccionesFranja.length; i++) x.agregar(infraccionesFranja[i], infraccionesFranja[i].getTotalInfracciones());;		
		
		InfraccionesFranjaHoraria i = null;
		while((i = x.getMax()) != null) resultado.enqueue(i); 
		return resultado;
	}
	
	/**
	  * Requerimiento 2A: Consultar  las  infracciones  por
	  * Localización  Geográfica  (Xcoord, Ycoord) en Tabla Hash.
	  * @param  double xCoord : Coordenada X de la localizacion de la infracción
	  *			double yCoord : Coordenada Y de la localizacion de la infracción
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorLocalizacionHash(double xCoord, double yCoord)
	{
		// TODO completar
		class KeyCoordenadas implements Comparable<KeyCoordenadas> {
			String xCoord;
			String yCoord;
			
			public KeyCoordenadas(Double pXCoord, Double pYCoord)
			{
				xCoord = pXCoord.toString();
				yCoord = pYCoord.toString();
				
			}
			
			public String toString()
			{
				return xCoord + yCoord;
			}

			@Override
			public int compareTo(KeyCoordenadas o) {
				// TODO Auto-generated method stub
				if(!this.xCoord.equals(o.xCoord)) {
					if(Double.parseDouble(xCoord) < Double.parseDouble(o.xCoord)) return -1;
					else if(Double.parseDouble(xCoord) > Double.parseDouble(o.xCoord)) return 1;
				}
				else {
					if(Double.parseDouble(yCoord) < Double.parseDouble(o.yCoord)) return -1;
					else if(Double.parseDouble(yCoord) == Double.parseDouble(o.yCoord)) return 0;
					else if(Double.parseDouble(yCoord) > Double.parseDouble(o.yCoord)) return 1;
				}
				return 0;
			}
		}
		
		IQueue<VOMovingViolations> queue = movingViolationsQueue;
		VOMovingViolations x = null;
		LinearProbingHT<Double, VOMovingViolations> ht = new LinearProbingHT<Double, VOMovingViolations>(movingViolationsQueue.size());
		
		
		Iterator<VOMovingViolations> it = movingViolationsQueue.iterator(); 
		while(it.hasNext()) {
			x = it.next();
			KeyCoordenadas key = new KeyCoordenadas(it.next().getXCoord(), x.getYCoord());
			if(!ht.contains(key.xCoord)) {
				ht.put(key.xCoord, x);
			}
			else {
				ht.put(key.yCoord, x);
			}
		}
		
		IQueue<VOMovingViolations> lista = new Queue<VOMovingViolations>();
		Iterator<Double> itKeys = ht.iterator();
		InfraccionesLocalizacion iL = null;
		double xcoord = 0, ycoord = 0;
		String locat = null;
		int address = 0;
		int street = 0;
		double llave;
		while(itKeys.hasNext()) {
			llave = itKeys.next();
			x = ht.get(llave);
			
			if(x.getXCoord() == xCoord && x.getYCoord() == yCoord) {
				lista.enqueue(x);
				xcoord = xCoord;
				ycoord = yCoord;
				locat = x.getLocation();
				address = x.getAddressId();
				street = x.getStreetSegId();
			}
		}
		
		iL = new InfraccionesLocalizacion(xcoord, ycoord, locat, address, street, lista);
		
		return iL;
	}
	
	/**
	  * Requerimiento 3A: Buscar las infracciones por rango de fechas
	  * @param  LocalDate fechaInicial: Fecha inicial del rango de búsqueda
	  * 		LocalDate fechaFinal: Fecha final del rango de búsqueda
	  * @return Cola con objetos InfraccionesFecha
	  */
	public IQueue<InfraccionesFecha> consultarInfraccionesPorRangoFechas(LocalDate fechaInicial, LocalDate fechaFinal)
	{
		// TODO completar
		Iterator<VOMovingViolations> it = movingViolationsQueue.iterator();
		LocalDate fecha = null;
		String[] issueDate = null;
		
		RedBlackBST<ChronoLocalDate, VOMovingViolations> arbol = new RedBlackBST<ChronoLocalDate, VOMovingViolations>();
		while(it.hasNext()) {
			VOMovingViolations x = it.next();
			issueDate = x.getTicketIssueDate().split("T");
			fecha = ManejoFechaHora.convertirFecha_LD(issueDate[0]);
			arbol.put(fecha, x);
		}
		
		IQueue<InfraccionesFecha> resultado = new Queue<InfraccionesFecha>();
		VOMovingViolations aux = null;
		Iterator<ChronoLocalDate> itRB = arbol.keys();
		IQueue<VOMovingViolations> lista = null;
		InfraccionesFecha infrcFecha = null;
		Iterator<InfraccionesFecha> infrcIt = resultado.iterator();
		
		while(itRB.hasNext()) {
			ChronoLocalDate date = itRB.next();
			aux = arbol.get(date);
			if(date.isAfter(fechaFinal) && date.isBefore(fechaInicial) || date.isEqual(fechaInicial) || date.isEqual(fechaFinal)) {
				if(resultado.isEmpty()) {
					lista = new Queue<VOMovingViolations>();
					lista.enqueue(aux);
					infrcFecha = new InfraccionesFecha(lista, (LocalDate) date);
					resultado.enqueue(infrcFecha);
				}
				else{
					while(infrcIt.hasNext()) {
						InfraccionesFecha temp = infrcIt.next();
						VOMovingViolations mv = temp.getListaInfracciones().iterator().next();
						while(temp != null && mv != null) {
							issueDate = mv.getTicketIssueDate().split("T");
							fecha = ManejoFechaHora.convertirFecha_LD(issueDate[0]);
							if(fecha.equals(date)) {
								temp.getListaInfracciones().enqueue(aux);
							}
							else {
								infrcFecha = new InfraccionesFecha(lista, (LocalDate) date);
								resultado.enqueue(infrcFecha);
							}
						}
					}
				}
			}
		}
		return resultado;		
	}
	
	/**
	  * Requerimiento 1B: Obtener  el  ranking  de  las  N  tipos  de  infracción
	  * (ViolationCode)  que  tengan  más infracciones.
	  * @param  int N: Numero de los tipos de ViolationCode con más infracciones.
	  * @return Cola con objetos InfraccionesViolationCode con top N infracciones
	  */
	public IQueue<InfraccionesViolationCode> rankingNViolationCodes(int N)
	{
		// TODO completar
		return null;		
	}

	
	/**
	  * Requerimiento 2B: Consultar las  infracciones  por  
	  * Localización  Geográfica  (Xcoord, Ycoord) en Arbol.
	  * @param  double xCoord : Coordenada X de la localizacion de la infracción
	  *			double yCoord : Coordenada Y de la localizacion de la infracción
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorLocalizacionArbol(double xCoord, double yCoord)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 3B: Buscar las franjas de fecha-hora donde se tiene un valor acumulado
	  * de infracciones en un rango dado [US$ valor inicial, US$ valor final]. 
	  * @param  double valorInicial: Valor mínimo acumulado de las infracciones
	  * 		double valorFinal: Valor máximo acumulado de las infracciones.
	  * @return Cola con objetos InfraccionesFechaHora
	  */
	public IQueue<InfraccionesFechaHora> consultarFranjasAcumuladoEnRango(double valorInicial, double valorFinal)
	{
		// TODO completar
		return null;		
	}
	
	/**
	  * Requerimiento 1C: Obtener  la información de  una  addressId dada
	  * @param  int addressID: Localización de la consulta.
	  * @return Objeto InfraccionesLocalizacion
	  */
	public InfraccionesLocalizacion consultarPorAddressId(int addressID)
	{
		// TODO completar
		SeparateChainingHT<Integer, VOMovingViolations> ht = new SeparateChainingHT<Integer, VOMovingViolations>();
		Iterator<VOMovingViolations> it = movingViolationsQueue.iterator();
		VOMovingViolations mv = null;
		
		while(it.hasNext()) {
			mv = it.next();
			ht.put(mv.getAddressId(), mv);
		}
		
		IQueue<VOMovingViolations> lista = new Queue<VOMovingViolations>();
		InfraccionesLocalizacion infrcLocalizacion = null;
		while((mv = (VOMovingViolations) ht.get(addressID)) != null) {
			lista.enqueue(mv);
			infrcLocalizacion = new InfraccionesLocalizacion(mv.getXCoord(), mv.getYCoord(), mv.getLocation(), mv.getAddressId(), mv.getStreetSegId(), lista);
		}		
		
		return infrcLocalizacion;		
	}
	
	/**
	  * Requerimiento 2C: Obtener  las infracciones  en  un  rango de
	  * horas  [HH:MM:SS  inicial,HH:MM:SS  final]
	  * @param  LocalTime horaInicial: Hora  inicial del rango de búsqueda
	  * 		LocalTime horaFinal: Hora final del rango de búsqueda
	  * @return Objeto InfraccionesFranjaHorariaViolationCode
	  */
	public InfraccionesFranjaHorariaViolationCode consultarPorRangoHoras(LocalTime horaInicial, LocalTime horaFinal)
	{
		// TODO completar
		RedBlackBST<LocalTime, VOMovingViolations> bst = new RedBlackBST<LocalTime, VOMovingViolations>();
		Iterator<VOMovingViolations> it = movingViolationsQueue.iterator();
		VOMovingViolations x = null;
		InfraccionesFranjaHorariaViolationCode iL = null;
		
		while(it.hasNext()) {
			x = it.next();
			String[] issueDate = x.getTicketIssueDate().split("T");
			String[] time = issueDate[1].split(".");
			LocalTime hora = ManejoFechaHora.convertirHora_LT(time[0]);
			
			bst.put(hora, x);
		}
		
		Iterator<LocalTime> keys = bst.keys();
		
		while(keys.hasNext()) {
			
		}
		return iL;	
	}
	
	/**
	  * Requerimiento 3C: Obtener  el  ranking  de  las  N localizaciones geográficas
	  * (Xcoord,  Ycoord)  con  la mayor  cantidad  de  infracciones.
	  * @param  int N: Numero de las localizaciones con mayor número de infracciones
	  * @return Cola de objetos InfraccionesLocalizacion
	  */
	public IQueue<InfraccionesLocalizacion> rankingNLocalizaciones(int N)
	{
		IQueue<InfraccionesLocalizacion> resultado = new Queue<InfraccionesLocalizacion>();
		SeparateChainingHT<Integer, InfraccionesLocalizacion> sHT = new SeparateChainingHT<Integer, InfraccionesLocalizacion>();
		Iterator<VOMovingViolations> it = movingViolationsQueue.iterator();
		VOMovingViolations x = null;
		InfraccionesLocalizacion iL = null;
		IQueue<VOMovingViolations> lista = new Queue<VOMovingViolations>();
		
		boolean ya = false;
		
		while(it.hasNext()) {
			x = it.next();
			
			if(sHT.size() == 0) {
				sHT.put(key, iL);
			}
			else {
				
			}
			for(x = it.next(); ya == false; x = it.next()) {
				
			}
		}
		return resultado;
	}
	
	/**
	  * Requerimiento 4C: Obtener la  información  de  los códigos (ViolationCode) ordenados por su numero de infracciones.
	  * @return Contenedora de objetos InfraccionesViolationCode.
	  // TODO Definir la estructura Contenedora
	  */
	public Contenedora<InfraccionesViolationCode> ordenarCodigosPorNumeroInfracciones()
	{
		// TODO completar
		// TODO Definir la Estructura Contenedora
		return null;
	}


}
