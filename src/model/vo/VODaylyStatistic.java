package model.vo;

/**
 * Esta clase representa el comparador
 */
public class VODaylyStatistic implements Comparable<VODaylyStatistic>{
	
	//------------------------------------------------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------------------------------------------------
	
	/**
	 * Fecha del día
	 */
	private String fechaDelDia;
	
	/**
	 * Numero de accidentes en el día
	 */
	private int numeroAccidentes;
	
	/**
	 * Numero de infracciones en el día
	 */
	private int numeroInfracciones;
	
	/**
	 * Cantidad a pagar por la infracción
	 */
	private int sumaFINEAMT;
	
	//------------------------------------------------------------------------------------
	// Constructor
	//------------------------------------------------------------------------------------
	
	/**
	 * Asigna valores a los atributos de esta clase. <br>
	 * <b>post:</b> los atributos estan inicializados.
	 * @param pFechaDelDia Fecha del día.
	 * @param pNumeroAccidente Numero de accidentes en el día.
	 * @param pNumeroInfracciones Numero de infracciones cometidas en el día.
	 * @param pSumaFINEMAT Cantidad a pagar por la infracción.
	 */
	public VODaylyStatistic(String pFechaDelDia, int pNumeroAccidente, int pNumeroInfracciones, int pSumaFINEMAT) {
		fechaDelDia = pFechaDelDia;
		numeroAccidentes = pNumeroAccidente;
		numeroInfracciones = pNumeroInfracciones;
		sumaFINEAMT = pSumaFINEMAT;
	}
	
	//-------------------------------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------------------------------
	
	/**
	 * Retorna la fecha del día
	 * @return fechaDelDia Fecha del día.
	 */
	public String getFechaDelDia() {
		return fechaDelDia;
	}
	
	/**
	 * Retorna el numero de accidentes en el día
	 * @return numeroAccidentes Numero de accidentes en el día
	 */
	public int getNumeroAccidentes() {
		return numeroAccidentes;
	}
	
	/**
	 * Retorna el numero de infracciones en el día
	 * @return numeroInfracciones Numero de infracciones en el día 
	 */
	public int getNumeroInfracciones() {
		return numeroInfracciones;
	}
	
	/**
	 * Retorna la cantidad a pagar por la infracción
	 * @return sumaFINEAMT Cantidad a pagar por la infracción
	 */
	public int getsumaFINEAMT() {
		return sumaFINEAMT;
	}
	
	@Override
	public int compareTo(VODaylyStatistic arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
