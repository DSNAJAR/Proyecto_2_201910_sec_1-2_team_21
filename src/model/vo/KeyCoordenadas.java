package model.vo;

public class KeyCoordenadas implements Comparable<KeyCoordenadas>
{
	
		String key;
		String x;
		String y;
		double code;
		
		public KeyCoordenadas(String pX, String pY,  String pCode)
		{
			code = Double.parseDouble(pCode);
			x = pX;
			y = pY;
			key = "(" + x + "," +y + ")" + code;
		}
		
		public int hashCode()
		{
			return (int)code;
		}
		public String toString()
		{
			return key + x + y;
		}

		@Override
		public int compareTo(KeyCoordenadas o) {
			// TODO Auto-generated method stub
			return 0;
		}

	
	

}
