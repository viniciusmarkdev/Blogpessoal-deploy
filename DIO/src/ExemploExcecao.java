import java.text.NumberFormat;
import java.text.ParseException;

public class ExemploExcecao {

	
	public static void main(String[] args) {
		
		Number valor = Double.valueOf("a1.75");
		System.out.println(valor);
		
		try {
			Number valor1 = NumberFormat.getInstance().parse("a1.75");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Execões customizadas 
		
		
		
		
		
	}
}
