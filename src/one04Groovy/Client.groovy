package one04Groovy

import java.util.regex.Pattern

class Client {

	Integer id;
	String name;
	String cpf;
	String email;
	String senha;
	Boolean isAdmin;
	
	public void setCpf(String cpf) {
		String regex = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d"
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		if(cpf.matches(pattern)){
			this.cpf = cpf;
		} else {
		    throw new IllegalArgumentException("CPF Inválido");	
		}
		
	}
}
