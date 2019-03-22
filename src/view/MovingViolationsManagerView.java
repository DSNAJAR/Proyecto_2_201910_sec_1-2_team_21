package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el nÃºmero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("--------------------Proyecto 1---------------------");
		System.out.println("----David Alejandro Ruiz & David Santiago Najar----");
		System.out.println("0. Cargar datos del cuatrimestre");
		System.out.println("1. Verificar que OBJECTID es en realidad un identificador único");
		System.out.println("2. Consultar infracciones por fecha/hora inicial y fecha/hora final");
		System.out.println("3. Dar FINEAMT promedio con y sin accidente por VIOLATIONCODE");
		System.out.println("4. Consultar infracciones por direccion entre fecha inicial y fecha final");

		
		System.out.println("5. Consultar los tipos de infracciones (VIOLATIONCODE) con su valor (FINEAMT) promedio en un rango dado");
		System.out.println("6. Consultar infracciones donde la cantidad pagada (TOTALPAID) esta en un rango dado. Se ordena por fecha de infracción");
		System.out.println("7. Consultar infracciones por hora inicial y hora final, ordenada ascendentemente por VIOLATIONDESC");
		System.out.println("8. Dado un tipo de infraccion (VIOLATIONCODE) informar el (FINEAMT) promedio y su desviacion estandar.");

		System.out.println("9. El numero de infracciones que ocurrieron en un rango de horas del dia. Se define el rango de horas por valores enteros en el rango [0, 24]");
		System.out.println("10. Grafica ASCII con el porcentaje de infracciones que tuvieron accidentes por hora del dia");
		System.out.println("11. La deuda (TOTALPAID â€“ FINEAMT - PENALTY1 â€“ PENALTY2) total por infracciones que se dieron en un rango de fechas.");
		System.out.println("12. Grafica ASCII con la deuda acumulada total por infracciones");

		
		System.out.println("13. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void printMovingViolationsReq2(IQueue<VOMovingViolations> resultados2) {
		for(VOMovingViolations v: resultados2) {
			System.out.println("ObjectID: " + v.getObjectId() + ", issued: " + v.getTicketIssueDate());
		}
	}
	
	public void printMovingViolationsReq4(IStack<VOMovingViolations> resultados4) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t STREETSEGID\t ADDRESS_ID");

		for(VOMovingViolations v: resultados4) {
			System.out.println( v.getObjectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getStreetSegId() + "\t" + v.getAdressId());
		}
	}
	
	public void printViolationCodesReq5(IQueue<VOViolationCode> violationCodes) {
		System.out.println("VIOLATIONCODE\t FINEAMT promedio");

		for(VOViolationCode v: violationCodes) {
			System.out.println(v.getViolationCode() + "\t" + v.getFINEAMTProm());
		}
	}
	
	public void printMovingViolationReq6(IStack<VOMovingViolations> resultados6) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t TOTALPAID");
		for(VOMovingViolations v: resultados6) {
			System.out.println( v.getObjectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getTotalPaid());
		}
	}
	
	public void printMovingViolationsReq7(IQueue<VOMovingViolations> resultados7) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t VIOLATIONDESC");
		for(VOMovingViolations v: resultados7) {
			System.out.println( v.getObjectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getViolationDescription());
		}
	}
	
	public void printMovingViolationsByHourReq10(String[] prints) {
		System.out.println("Porcentaje de infracciones que tuvieron accidentes por hora. 2018");
		System.out.println("Hora| % de accidentes");
		int i = 0;
		while(i < 24) {
			if(i < 10) {
				System.out.println("0" + i + " | " + prints[i]);
				i++;
			}
			else {
				System.out.println(i + " | " + prints[i]);
				i++;
			}
		}
		System.out.println("");
		System.out.println("Cada X representa 5%");
	}
	
	public void printTotalDebtbyMonthReq12(String[] prints) {
		System.out.println("Deuda acumulada por mes de infracciones. 2018");
		System.out.println("Mes| Dinero");
		System.out.println("01 | " + prints[0]);
		System.out.println("02 | " + prints[1]);
		System.out.println("03 | " + prints[2]);
		System.out.println("04 | " + prints[3]);
		System.out.println("");
		System.out.println("Cada X representa $1500 USD");
	}
	
}
