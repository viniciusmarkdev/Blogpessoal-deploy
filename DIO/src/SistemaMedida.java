import java.util.Scanner;

public class SistemaMedida {
	public static void main(String[] args) {
		
		
		
		String sigla;
		
		Scanner scanner = new Scanner(System.in);
		
		sigla = scanner.next();
		
		
		switch(sigla) {
		
		case "P" :{
			System.out.println("PEQUENO");
			break;
		}
		case "M" :{
			System.out.println("MEDIA");
			break;
		}
		case "G" :{
			System.out.println("GRANDE");
			break;
		}
		default :
			
			System.out.println("Indefinido");
			break;
		
		
	}

}
	
}
