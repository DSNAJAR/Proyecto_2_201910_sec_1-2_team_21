package model.util;

public class Sort {
	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarQuickSort( Comparable[ ] datos, int lo, int hi ) {

		// TODO implementar el algoritmo QuickSort
		int mas = datos.length;
		int menos = mas - (mas-1);
		
		if(mas <= menos) return;
		int j = particion(datos);
		
		ordenarQuickSort(datos, menos, j-1);
		ordenarQuickSort(datos, j+1, mas);
		
	}
	
	/**
	 * Método auxiliar para Quicksort
	 * @retrun indicador de la partición
	 */
	public static int particion( Comparable[ ] datos ){
		int ult = datos.length +1;
		int prim = ult -(ult - 1);
		
		Comparable a = datos[prim];
		
		while(true){
			while(less(datos[++prim], a))
				if(prim == ult) break;
			
			while(less(a, datos[--ult]))
				if(ult == prim) break;
			
			if(prim >= ult) break;
			
			exchange(datos, prim, ult);
		}
		
		exchange(datos, prim, ult);
		
		return ult;
	}
	
	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * @param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(Comparable v, Comparable w)
	{
		// TODO implementar
		return (v.compareTo(w) < 0);
	}
	
	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param datos contenedor de datos
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	private static void exchange( Comparable[] datos, int i, int j)
	{
		// TODO implementar
		Comparable swap = datos[i];
		datos[i] = datos[j];
		datos[j] = swap;
	}

}
