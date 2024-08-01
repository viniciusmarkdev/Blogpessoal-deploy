
public class FormatadorCepExemplo {
	
	public static void main(String[] args) {
		
		
		String cep = "0119109";
		try {
			String cepFormatado = formatarCep(cep);
			System.out.println(cepFormatado);
		} catch (CepInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	static String formatarCep(String cep) throws CepInvalidoException{
        if(cep.length() != 8)
          throw new CepInvalidoException();
        
          //simulando um cep formatado
          return "23.765-064";
    }
	

}
