package model.vo;

/**
 * Esta clase representa el comparador dado un codigo de infracción y un FINEAMT promedio
 */
public class VOViolationCode implements Comparable<VOViolationCode> {
	
	//-----------------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------------
	
	/**
	 * Codigo de la infracción.
	 */
	private String violationCode;
	
	/**
	 * Cantidad promedio a pagar por la infracción.
	 */
	private int promedioFINEAMT;
	
	//------------------------------------------------------------------------
	// Constructor
	//------------------------------------------------------------------------
	
	/**
	 * Inicializa el comparador
	 * <b>post:</b> los atributos estan inicializados
	 * @param pViolationCode Codigo de la infracción.
	 * @param pPromedioFINEAMT Valor del FINEAMT promedio.
	 */
	public VOViolationCode(String pViolationCode, int pPromedioFINEAMT) {
		// TODO Auto-generated constructor stub
		violationCode = pViolationCode;
		promedioFINEAMT = pPromedioFINEAMT;
	}
	
	//-------------------------------------------------------------------------
	// Métodos
	//-------------------------------------------------------------------------
	
	/**
	 * Retorna el codigo de la infracción.
	 * @return Código de la infracción
	 */
	public String getViolationCode () {
		return violationCode;
	}
	
	/**
	 * Retorna el FINEAMT promedio
	 * @return FINEAMT promedio 
	 */
	public int getFINEAMTProm () {
		return promedioFINEAMT;
	}

	@Override
	public int compareTo(VOViolationCode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
